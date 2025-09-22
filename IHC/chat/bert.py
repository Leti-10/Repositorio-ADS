import httpx
import sqlite3
from mcp.server.fastmcp import FastMCP
from datetime import datetime

# --- Inicializa MCP ---
mcp = FastMCP("botinho_ai")

# --- Configuração Banco SQLite ---
DB_PATH = "meubanco.db"

def init_db():
    conn = sqlite3.connect(DB_PATH)
    cur = conn.cursor()
    cur.execute("""
        CREATE TABLE IF NOT EXISTS notes (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            title TEXT,
            content TEXT
        )
    """)
    conn.commit()
    conn.close()

init_db()

# --- Tool: Chat com Ollama ---
@mcp.tool()
async def chat_with_ollama(prompt: str, model: str = "qwen3:1.7b") -> str:
    """Enviar um prompt para o Ollama e receber a resposta."""
    print(f"[{datetime.now().strftime('%H:%M:%S')}] 💬 Iniciando chamada ao Ollama...")
    
    start_time = datetime.now()
    async with httpx.AsyncClient() as client:
        try:
            response = await client.post(
                "http://localhost:11434/api/generate",
                json={"model": model, "prompt": prompt, "stream": False},
                timeout=120.0 
            )
            response.raise_for_status()
            data = response.json()
            
            end_time = datetime.now()
            duration = (end_time - start_time).total_seconds()
            print(f"[{datetime.now().strftime('%H:%M:%S')}] ✅ Chamada ao Ollama concluída em {duration:.2f} segundos.")
            
            return data.get("response", "Erro: nenhuma resposta do modelo")
        except Exception as e:
            return f"Erro ao conectar com Ollama: {str(e)}"

# --- Tool: Adicionar nota ---
@mcp.tool()
async def add_note(title: str, content: str) -> str:
    """Adiciona uma nota ao banco SQLite."""
    conn = sqlite3.connect(DB_PATH)
    cur = conn.cursor()
    cur.execute("INSERT INTO notes (title, content) VALUES (?, ?)", (title, content))
    conn.commit()
    conn.close()
    return f"Nota '{title}' adicionada!"

# --- Tool: Listar notas ---
@mcp.tool()
async def list_notes() -> str:
    """Lista todas as notas do banco."""
    conn = sqlite3.connect(DB_PATH)
    cur = conn.cursor()
    cur.execute("SELECT id, title, content FROM notes")
    rows = cur.fetchall()
    conn.close()

    if not rows:
        return "Nenhuma nota encontrada."
    
    return "\n".join([f"{r[0]} - {r[1]}: {r[2]}" for r in rows])

# --- Tool: Resumir notas com IA ---
@mcp.tool()
async def summarize_notes() -> str:
    """Resume todas as notas do banco usando a IA (Ollama)."""
    conn = sqlite3.connect(DB_PATH)
    cur = conn.cursor()
    cur.execute("SELECT content FROM notes")
    notes = [r[0] for r in cur.fetchall()]
    conn.close()

    if not notes:
        return "Nenhuma nota para resumir."

    prompt = "Resuma essas anotações de forma clara e concisa:\n\n" + "\n".join(notes)
    return await chat_with_ollama(prompt)

# --- Entry point ---
if __name__ == "__main__":
    mcp.run(transport="stdio", tool_timeout=300)


