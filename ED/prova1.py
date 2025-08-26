# 1) Escreva uma função chamada maior_menor_que(lista, limite) que receba uma
# lista de números inteiros e um número limite. A função deve retornar o maior
# número da lista que seja menor que o limite. Se todos os elementos forem
# maiores ou iguais ao limite, retorne None. Não use sort ou max. Exemplo:
# maior_menor_que([10, 20, 30, 40], 35) → 30 e maior_menor_que([50, 60, 70], 40)
# → None.

def maior_menor_que(lista, limite):
    cont = None
    for x in lista:
        if x < limite:
            if cont is None or x > cont:
                cont = x
    return cont

print(maior_menor_que([1,2,3,56,78,92], 58))

# 2) Escreva uma função gato_atrapalha_sono(miando, hora, feriado) que recebe
# três argumentos: miando, que é True quando o gato está miando e False caso
# contrário; hora, um inteiro de 0 a 23 representando a hora do dia; e feriado,
# que é True se for feriado e False caso contrário. Considere que em dias normais
# o aluno dorme entre 21h e 5h, e em feriados entre 23h e 8h. A função deve
# retornar True se o gato estiver miando durante o horário de sono, atrapalhando
# o descanso do aluno. Note que o gato miar exatamente na hora de acordar não
# atrapalha.

def gato_atrapalha_sono(miando, hora, feriado):
    sono = hora >=21 or hora<5
    if feriado:
        sono = hora >=23 or sono <8
    if sono and miando:
        return "O gato está atrapalhando"
    return "O gato não está atrapalhando"

print(gato_atrapalha_sono(True, 22, False))

# 3) Escreva um programa que leia uma palavra por vez, digitada pelo usuário, até
# que ele digite "sair". Armazene as palavras em uma lista. Em seguida, imprima
# todas as palavras com mais de 5 letras e que contenham ao menos uma letra da
# palavra “python”. Suponha que todas as palavras estejam em letras minúsculas.

palavras = []
palavra = input("Digite uma palavra: ").lower()
while palavra != 'sair':
    palavras.append(palavra)
    palavra = input("Digite uma palavra: ").lower()

resul=[]
for p in palavras:
    if len(p) > 5:
        for letra in p:
            if letra in 'python':
                resul.append(p)
                break

print(resul)


# 4) Escreva uma função válido(idade, renda) que recebe dois argumentos: a idade
# de uma pessoa (inteiro) e sua renda mensal (float ou inteiro). A função deve
# retornar True se a pessoa tiver 18 anos ou mais e renda de pelo menos R$2500,
# ou se tiver 21 anos ou mais, independentemente da renda. Caso contrário, a
# função deve retornar False.

def válido(idade, renda):
    if idade >=18 and renda>=2500:
        return True
    elif idade>=21 and renda<2500:
        return True
    else:
        return False
    
print(válido(18, 3000))

# 5) O código abaixo foi escrito para somar os valores de uma lista de preços,
# mas contém vários erros distintos (de sintaxe e lógica). a) Encontre todos os
# erros, indicando se são de sintaxe ou de lógica. b) Reescreva o código
# corrigido.
def soma_preços(lista):
    total = 0.0
    for preço in lista:
        total = total + preço
    return total
preços = "19.90 35.00 12.50 9.90".split()
print("Total: R$" + soma_preços(preços))


# 6) Consumo de energia acumulativo
# Consumo base 120 kWh, cada novo aparelho aumenta +10 kWh/mês
# Um novo aparelho a cada 3 meses
# Calcular após quantos meses o consumo ultrapassa 240 kWh

consumo_base = 120
meses = 0
consumo = consumo_base
aparelhos = 0

while consumo <= 240:
    meses += 1
    if meses % 3 == 0:
        aparelhos += 1
    consumo = consumo_base + aparelhos * 10

print(f"Mês: {meses}, Consumo: {consumo} kWh")

# 7) Função zigue_zague(s)
# Deixa maiúsculas as letras nas posições pares (0, 2, 4,...)

def zigue_zague(s):
    resultado = ""
    for i, letra in enumerate(s):
        if i % 2 == 0:
            resultado += letra.upper()
        else:
            resultado += letra.lower()
    return resultado

# Exemplo
print(zigue_zague("Python"))  # PyThOn
print(zigue_zague("bAnana"))  # BANaNa

# Versão com list comprehension
def zigue_zague_lc(s):
    return "".join([letra.upper() if i % 2 == 0 else letra.lower() for i, letra in enumerate(s)])

print(zigue_zague_lc("Python"))  # PyThOn

# ------------------------------------------

# 8) Função pell(n)
# Sequência de Pell: p(0)=0, p(1)=1, p(n)=2*p(n-1)+p(n-2)

def pell(n):
    if n == 0:
        return 0
    if n == 1:
        return 1
    a, b = 0, 1  # p(0), p(1)
    k = 2
    while k <= n:
        a, b = b, 2*b + a
        k += 1
    return b

# Exemplo
print(pell(5))  # 29
print(pell(0))  # 0
print(pell(1))  # 1
print(pell(4))  # 12

# ------------------------------------------

# 9) Determinar valores de x para cada saída
# x = ?  # descobrir

# Condições:
# if x % 2 == 0 and x < 0: -> "par negativo"
# elif x % 2 != 0 and x > 0 and x < 10: -> "ímpar pequeno"
# elif x % 3 == 0 or x > 100: -> "divisível por 3 ou grande"
# else: -> "caso genérico"

# Possíveis valores:
# "par negativo" -> x = -2
# "ímpar pequeno" -> x = 3
# "divisível por 3 ou grande" -> x = 6 ou x = 101
# "caso genérico" -> x = 7 (não cai em nenhuma das condições acima)

# Exemplo de teste
x = -2
if x % 2 == 0 and x < 0:
    print("par negativo")
elif x % 2 != 0 and x > 0 and x < 10:
    print("ímpar pequeno")
elif x % 3 == 0 or x > 100:
    print("divisível por 3 ou grande")
else:
    print("caso genérico")

# ------------------------------------------

# 10) Teste de mesa da função processa(lista)
# Conta números pares até encontrar o 42 (break)

def processa(lista):
    x = 0
    achou = False
    for i in range(len(lista)):
        n = lista[i]
        if n == 42:
            achou = True
            break
        if n % 2 == 0:
            x = x + 1
        print(i, n, x, achou)  # para teste de mesa
    print("Total de pares antes do 42:", x)

# Lista de teste
lista = [2, 4, 1, 6, 42, 13, 10, 5]
processa(lista)

# Saída esperada do teste de mesa:
# 0 2 1 False
# 1 4 2 False
# 2 1 2 False
# 3 6 3 False
# 4 42 3 True
# Total de pares antes do 42: 3
