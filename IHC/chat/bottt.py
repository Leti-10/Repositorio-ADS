import httpx
import sqlite3
from mcp.server.fastmcp import FastMCP
from datetime import datetime

# --- Inicializa MCP ---
mcp = FastMCP("botinho_clinica_ai")

# --- Configuração Banco SQLite ---
DB_PATH = "clinica.db" # Nome do banco alterado para clareza

def init_db():
    """Inicializa o banco de dados com a tabela de agendamentos."""
    conn = sqlite3.connect(DB_PATH)
    cur = conn.cursor()
    # Tabela alterada de 'notes' para 'appointments' com campos relevantes
    cur.execute("""
        CREATE TABLE IF NOT EXISTS appointments (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            patient_name TEXT NOT NULL,
            doctor_name TEXT NOT NULL,
            exam_type TEXT NOT NULL,
            appointment_datetime TEXT NOT NULL UNIQUE 
            -- UNIQUE na data/hora para ajudar a evitar duplicação simples
        )
    """)
    conn.commit()
    conn.close()

init_db()

# --- Tool: Chat com Ollama (permanece a mesma) ---
@mcp.tool()
async def chat_with_ollama(prompt: str, model: str = "qwen3:1.7b") -> str:
    """Enviar um prompt para o Ollama e receber a resposta."""
    async with httpx.AsyncClient() as client:
        try:
            response = await client.post(
                "http://localhost:11434/api/generate",
                json={"model": model, "prompt": prompt, "stream": False},
                timeout=600000 # Timeout aumentado um pouco
            )
            response.raise_for_status() # Garante que erros HTTP sejam capturados
            data = response.json()
            return data.get("response", "Erro: nenhuma resposta do modelo")
        except httpx.RequestError as e:
            return f"Erro de conexão ao tentar falar com Ollama: {str(e)}"
        except Exception as e:
            return f"Ocorreu um erro inesperado: {str(e)}"

# --- Tool: Agendar Consulta ---
@mcp.tool()
async def schedule_appointment(patient_name: str, doctor_name: str, exam_type: str, appointment_datetime: str) -> str:
    """
    Agenda uma consulta para um paciente com um médico específico em uma data e hora.
    O formato da data e hora deve ser 'YYYY-MM-DD HH:MM:SS'.
    """
    try:
        # Valida o formato da data e hora
        datetime.strptime(appointment_datetime, '%Y-%m-%d %H:%M:%S')
    except ValueError:
        return "Erro: Formato de data e hora inválido. Use 'YYYY-MM-DD HH:MM:SS'."

    conn = sqlite3.connect(DB_PATH)
    cur = conn.cursor()

    # VERIFICAÇÃO DE CONFLITO: Checa se o médico já tem uma consulta no mesmo horário
    cur.execute(
        "SELECT id FROM appointments WHERE doctor_name = ? AND appointment_datetime = ?",
        (doctor_name, appointment_datetime)
    )
    if cur.fetchone():
        conn.close()
        return f"Erro: Dr(a). {doctor_name} já possui uma consulta agendada para {appointment_datetime}. Por favor, escolha outro horário."

    # Se não houver conflito, insere a nova consulta
    cur.execute(
        "INSERT INTO appointments (patient_name, doctor_name, exam_type, appointment_datetime) VALUES (?, ?, ?, ?)",
        (patient_name, doctor_name, exam_type, appointment_datetime)
    )
    conn.commit()
    conn.close()
    
    return f"✅ Sucesso! Consulta agendada para {patient_name} com Dr(a). {doctor_name} sobre '{exam_type}' no dia {appointment_datetime}."

# --- Tool: Listar Consultas ---
@mcp.tool()
async def list_appointments() -> str:
    """Lista todas as consultas agendadas."""
    conn = sqlite3.connect(DB_PATH)
    cur = conn.cursor()
    cur.execute("SELECT id, patient_name, doctor_name, exam_type, appointment_datetime FROM appointments ORDER BY appointment_datetime ASC")
    rows = cur.fetchall()
    conn.close()

    if not rows:
        return "Nenhuma consulta agendada encontrada."
    
    formatted_rows = [
        f"ID {r[0]}: {r[4]} - {r[3]} com Dr(a). {r[2]} para o paciente {r[1]}" 
        for r in rows
    ]
    return "📅 Lista de Consultas Agendadas:\n" + "\n".join(formatted_rows)

# --- Tool: Obter Instruções de Pré-Exame ---
@mcp.tool()
async def get_pre_exam_instructions(exam_type: str) -> str:
    """Fornece as instruções de preparo para um tipo específico de exame usando a IA."""
    prompt = f"Aja como um assistente de clínica. Forneça instruções claras e simples de preparo para um paciente que fará um exame de '{exam_type}'. Inclua coisas como jejum, roupas a vestir, e o que trazer."
    
    # Reutiliza a ferramenta de chat com a IA para gerar a resposta
    return await chat_with_ollama(prompt)

# --- Entry point ---
if __name__ == "__main__":
    print("🤖 Assistente de Clínica AI iniciado. Diga o que você precisa...")
    mcp.run(transport="stdio")