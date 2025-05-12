#def inverte(frase). Faça uam função que receba uma frase como
#entrada e devolva as palavras da frase invertidas.
#inverte('batatinha quando nasce') -> 'nasce quando batatinha'

#def dma(s). Existem datas em dois formatos: 'dd-mm-aaaa', 'dd-/mm/aaaa'.
#Retorna dia, mês e ano numa lista. Se não '-' ou '/' retorna a string original.
#dma('08-11-2024') -> ['08', '11', '2024']
#dma('08/11/2024') -> ['08', '11', '2024']
#dma('abobrinha') -> ['abobrinha']

#def anagrama (s1, s2). Duas palavras são anagramas quando possuem as mesma
#letras em outra ordem. sem usar repetições (for e while).
#anagrama ('alegria', 'alegria') -> True
#anagrama ('sim', 'siiiimmm') -> False
# anaragrama ('palmeiras', 'abacate') -> False


# def inverte(frase):
#     x=frase.split(" ")
#     y=x[::-1]
#     return ' '.join(y)

# def dma(s):
#     if "-" in s:
#         return s.split('-')
#     elif "/" in s:
#         return s.split('/')
#     else:
#         return s

# def anagrama(s1, s2):
#     return sorted(s1)==sorted(s2)