def soma_duplas(nums):
    return sum(n for n in set(nums) if nums.count(n) == 2)

print(soma_duplas([1, 2, 2, 3, 4, 6, 6]))
print(soma_duplas([1,2,3,4,5,5,6,6,7]))
print("----------------------------------------------------")

l = [1, 2, 2, 3, 4, 6, 6]

soma = 0
for n in set(l):
    if l.count(n) == 2:
        soma +=n
    

print(soma)
         
    
# Seja uma lista de inteiros, mostre apenas os números pares usando list comprehension.

lista=[1,2,3,4,5,6,7,8,9,10]
pares = []

for x in lista:
    if x % 2==0:
        pares.append(x)
print(pares)

# [x for x in lista if x%2==0]


# Crie uma lista com os quadrados de todos os números pares de 1 a 20 usando list comprehension.

par=[]
for x in range(1,21):
    if x%2==0:
        x=x**2
        par.append(x)
# par = [x**2 for x in range(21) if x%2==0]
print(par)

# Dada uma lista de palavras, ordene-a pelo tamanho das palavras em ordem crescente, utilizando sorted() com a cláusula key=.

palavras = ["abacate", "banana", 'colmeia','desodorante', 'elétrico']
ordenadas = sorted(palavras, key=len)
print(ordenadas)

    # Dada uma lista de palavras, ordene-a pelo número de vogais presentes em cada palavra.

def ordene(palavra):
    con=0
    vogais='aeiou'
    for letra in palavra.lower():
        if letra in vogais:
            con=con+1
    return con
            

cont = sorted(palavras, key=ordene)
print(cont)

# Dada uma lista de palavras, ordene-a pelo último caractere de cada palavra.

ord = sorted(palavras, key=lambda palavra : palavra[-1])
print(ord)

# Dada uma string, utilize list comprehension para criar uma nova string onde os caracteres aparecem alternando entre maiúsculas e  minúsculas.

coisa = 'batatinha quando nasce'.lower()
outra=''
for i, x in enumerate(coisa):
    if i % 2 == 0:
        outra += x.upper()
    else:
        outra += x.lower()
# outra = ''.join([x.upper() if i%2==0 else x.lower()
#     for i, x in enumerate(coisa)])
print(outra)

# Dada uma lista de strings contendo números misturados com letras (por exemplo, "a3b", "z12y", "c1x"), ordene a lista com base no número contido na string.

listas=["a3b", "z12y", "c1x"]

def sort_num(algo):
    nums=''
    for x in algo:
        if x.isdigit():
            nums=nums+x
    return(nums)

aojd=sorted(listas, key=sort_num)
print(aojd)

# Crie um dicionário que mapeia os números de 1 a 10 para seus respectivos quadrados, usando dict comprehension.

num_q={n : n**2 for n in range(1,11)}
print(num_q)

# Dada uma string, crie um dicionário onde as chaves são os caracteres e os valores são a contagem de vezes que cada caractere aparece.

# def con_l(letras):
#     cont={}
#     for num in letras:
#         if num in cont:
#             cont[num] +=1
#         else:
#             cont[num]=1
#     return cont
text='banana'
cont = {letra: text.count(letra) for letra in text }
print(cont)

# texto = "banana"
# contagem = {char: texto.count(char) for char in texto}
# print(contagem)


# Dado um dicionário qualquer, crie um novo dicionário onde as chaves e os valores estejam invertidos.

dicti={1:23, 2:34, 3:21, 4:89, 5:76}
out={k:v for v, k in dicti.items()}
# for chave in dicti:
#     valor=dicti[chave]
#     out[valor]=chave

    
print(out)

# Dado um dicionário de números, crie um novo dicionário contendo apenas os pares chave-valor onde o valor seja maior que um determinado número.

dicionario={1:2, 2:14, 3:8, 4:12, 5:9}
novo={}
for chave in dicionario:
    valor=dicionario[chave]
    if valor >= 5 and valor%2==0:
        novo[chave]=valor
print(novo)

# Dado um dicionário, ordene-o pelos valores.

dic= {1:4, 2:15, 3:6, 4:9, 5:20, 6:3, 7:12, 8:7, 9:18, 10:5}
ordem= dict(sorted(dic.items(), key = lambda item:item[-1]))
print(ordem)

# Dado um dicionário onde as chaves são palavras, ordene-o com base no comprimento das chaves.

dici= {"maçã": 5, "banana": 12, "uva": 3, "laranja": 8, "pera": 15, "kiwi": 7}
order= dict(sorted(dici.items(), key = lambda item : len(item[0])))
print(order)

# Dada uma frase, crie um dicionário onde as chaves são palavras e os valores são a contagem de vezes que cada palavra aparece.

frase='abacate abacate cenoura feliz feliz'
def cont_p(texto):
    cont={}
    for palavra in texto.split():
        if palavra in cont:
            cont[palavra] +=1
        else:
            cont[palavra] =1
    return cont

print(cont_p(frase))

# Dado um dicionário onde os valores são números, crie um novo dicionário onde cada valor seja a raiz quadrada do original.

quadrado={1:100, 2:81, 3:64, 4:49, 5:36, 6:25, 7:16, 8:9, 9:4, 10:1}
raizes = {x: v**0.5 for x, v in quadrado.items()}
print(raizes)


# Dada uma lista de palavras, crie um dicionário onde as chaves sejam as primeiras letras e os valores sejam listas das palavras correspondentes.
# lista = 'cax abe b13 c23 arr d12'.split()
# saída:
# {'a': ['abe', 'arr'], 'b':['b13'], 'c':['cax', 'c23'],
#  'd': ['d12']}

lista = 'cax abe b13 c23 arr d12'.split()
cont= {letra: [palavra for palavra in lista if palavra[0] == letra]
       for letra in sorted(set(p[0] for p in lista))}

# for palavra in lista:
#     chave = palavra[0]
#     if chave not in cont:
#         cont[chave] = []
#     cont[chave].append(palavra)

print(cont)
