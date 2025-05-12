meses='''janeiro fevereiro março abril maio junho julho agosto setembro outubro novembro dezemebro'''.split()
d,m,a=input('Insira uma data no formato dd/mm/aaaa:').split('/')
m=int(m)
print(f'{d} de {meses[m-1]} de {a}')


