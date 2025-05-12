a=int(input('Digite um número:'))
b=int(input('Digite um número:'))
while a%b !=0:
    a,b=b,a%b
print(f'o mdc é {b}')
