# 1) maior_abaixo_media(lista)
# Retorna o maior número abaixo da média

def maior_abaixo_media(lista):
    if not lista:
        return None
    media = sum(lista)/len(lista)
    menores = [x for x in lista if x < media]  # lista dos elementos menores que a média
    if not menores:
        return None
    maior = menores[0]
    for num in menores:  # encontra o maior da lista filtrada
        if num > maior:
            maior = num
    return maior

# Exemplos
print(maior_abaixo_media([10, 20, 30, 40]))  # 20
print(maior_abaixo_media([5, 5, 5]))         # None
print(maior_abaixo_media([1, 100]))          # 1
print(maior_abaixo_media([4, -8, 15, -16, 23, 42]))  # 4

# ------------------------------------------

# 2) zigue_zague(s)
# Alterna maiúsculas e minúsculas nas posições ímpares
def zigue_zague(s):
    resultado = ""
    for i, letra in enumerate(s):
        if i % 2 != 0:
            resultado += letra.upper()
        else:
            resultado += letra.lower()
    return resultado

# Exemplo
print(zigue_zague('abacate'))  # aBaCaTe
print(zigue_zague(''))         # ''

# Versão com list comprehension
def zigue_zague_lc(s):
    return "".join([letra.upper() if i % 2 != 0 else letra.lower() for i, letra in enumerate(s)])

print(zigue_zague_lc('abacate'))  # aBaCaTe

# ------------------------------------------

# 3) Corrigindo o código de soma de preços
# Erros: nome da função, uso de variáveis (total não definido), sintaxe do print

def soma(lista):
    total = 0.0
    for p in lista:
        total += float(p)  # converter string para float
    return total

precos = "19.90 35.00 12.50 9.90".split()
print("Total: R$", soma(precos))  # Total: R$ 77.4

# 4) def pares_finais(n). A função recebe um número inteiro positivo n. Ela deve
# retornar quantos dígitos pares aparecem seguidos no final do número. Exemplos:
# pares_finais(245680) -> 3, pares_finais(123456) -> 1, pares_finais(13) -> 0.

def pares_finais(n):
    cont=0
    coiso = str(n)
    for x in reversed(coiso):
        if int(x)%2==0:
            cont+=1
        else:
            break
    return cont

print(pares_finais(245680))
print(pares_finais(123456))  
print(pares_finais(13))

print("--------------------------------------------")

# 5) Enade 2011. No livro “O Homem que Calculava”, de Malba Tahan, um personagem
# desejava ganhar os grãos de trigos que fossem distribuídos sobre um tabuleiro
# de xadrez do seguinte modo: um grão na primeira casa do tabuleiro, o dobro (2)
# na segunda, novamente o dobro (4) na terceira, outra vez o dobro (8) na quarta,
# e assim por diante, até a sexagésima quarta casa do tabuleiro. Faça um
# algoritmo que calcule a quantidade total de grãos de trigo necessários para
# realizar esta distribuição. Não use o operador ** de exponenciação neste
# exercício.

total = 0
grãos=1

for i in range(64):
    total += grãos
    grãos*=2
print(total)

# 6) mesmas_letras(s1, s2)
# Verifica se duas strings têm exatamente as mesmas letras, independente da ordem e quantidade
def mesmas_letras(s1, s2):
    set1 = set(s1)
    set2 = set(s2)
    return set1 == set2

# Exemplos
print(mesmas_letras('banana', 'abn'))  # True
print(mesmas_letras('casa', 'casal'))  # False
print(mesmas_letras('casal', 'casa'))  # False
print(mesmas_letras('amor', 'roma'))   # True

# Versão usando for (sem set)
def mesmas_letras_for(s1, s2):
    letras1 = ""
    letras2 = ""
    for letra in s1:
        if letra not in letras1:
            letras1 += letra
    for letra in s2:
        if letra not in letras2:
            letras2 += letra
    return sorted(letras1) == sorted(letras2)

print(mesmas_letras_for('amor', 'roma'))  # True

# 7) maldição(s)
# Substitui a vogal mais frequente por '#' (primeira no caso de empate)
def maldicao(s):
    vogais = 'aeiou'
    frequencias = {v: s.count(v) for v in vogais}  # conta todas as vogais
    # pega a vogal com maior contagem
    mais_freq = max(frequencias, key=lambda x: frequencias[x])
    return s.replace(mais_freq, '#')

print(maldicao('abracadabra bobo sem nocao'))  # '#br#c#d#br# bobo sem noc#o'
print(maldicao('aaaxxxeeexxxiiixxxoooxxxuuu'))  # '###xxxeeexxxiiixxxoooxxxuuu'

# ------------------------------------------

# 8)def sanduíche_com_vogais(s). Conta quantas vezes um padrão "sanduíche com
# vogais" aparece em uma string. Um sanduíche com vogais é uma sequência de 3
# caracteres onde o primeiro e o terceiro são vogais e o segundo pode ser
# qualquer caractere. Exemplos: sanduíche('amazing') -> 2, sanduíche('cooool') ->
# 2, sanduíche('aeiouAEIOU') -> 8, sanduíche('') -> 0

def sanduiche_com_vogais(s):
    vogais = "aeiouAEIOU"
    cont = 0
    
    for i in range(len(s) - 2):
        if s[i] in vogais and s[i+2] in vogais:
            cont += 1
    return cont



print(sanduiche_com_vogais("amazing"))    
print(sanduiche_com_vogais("cooool"))     
print(sanduiche_com_vogais("aeiouAEIOU")) 
print(sanduiche_com_vogais("")) 

# 9)def soma_duplas(nums). Dada uma lista de inteiros nums, some cada número que
# aparece exatamente duas vezes, apenas uma vez na soma final. Exemplos:
# soma_duplas([1, 2, 2, 5, 3, 3, 3, 4, 4]) -> 2 + 4 = 6, soma_duplas([1, 2, 1, 2,
# 3]) -> 1 + 2 = 3, soma_duplas([]) -> 0.

def soma_duplas(nums):
    return sum([n for n in set(nums) if nums.count(n) == 2])

print(soma_duplas([1, 2, 2, 5, 3, 3, 3, 4, 4]))

# 10) Código dado
x = ['', 'abacate', {}, {42: 'resposta'}, [], [42], 0, 42, 3 == 3.0, '' in 'abacate']
res = []
while x:
    item = x[0]
    x = x[1:]
    if item:
        res.append(item)
    else:
        res.append('Falso lógico')
print(res)
if [42] in res:
    print('Eu sou Feliz!')
print(res.count('Falso lógico'))
print(res.count(True))
print(res.count('Falso'))
if False:
    print('Falso!')
if True:
    print('Vou aprender mais!')

# Explicação rápida:
# - itens "vazios" ou False: '', {}, [], 0 => "Falso lógico"
# - itens verdadeiros: 'abacate', {42:'resposta'}, [42], 42, True (3==3.0), '' in 'abacate' -> True
# - [42] está em res, então imprime 'Eu sou Feliz!'

# ------------------------------------------

# 11) espelho_frase(frase)
# Inverte a ordem das palavras e inverte cada palavra
def espelho_frase(frase):
    return " ".join([palavra[::-1] for palavra in frase.split()][::-1])

print(espelho_frase("Python é Legal"))  # "lageL é nohtyP"
print(espelho_frase("uma frase de teste"))  # "etset ed esarf amu"









        
