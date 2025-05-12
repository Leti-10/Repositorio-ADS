p=float(input('Preço da mercadoria:'))
d=float(input('Percentual do desconto:'))
desconto=p*d/100
novo=p-desconto
print(f'O valor do desconto é: R${desconto:.2f}')
print(f'O valor a pagar é: R${novo:.2f}')
