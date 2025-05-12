n=float(input('Insira uma nota:'))
while n>10 or n<0:
    print('Nota inválida!')
    n=float(input('Insira uma Nota:'))
else:
    n>=0 or n<=10
    print(f'Nota:{n:.2f}')
