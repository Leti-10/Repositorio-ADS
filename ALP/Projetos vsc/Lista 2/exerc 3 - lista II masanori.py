p = float(input('Peso do peixe:'))
if p > 50.00:
    extra=p-50.00
    multa=extra*4
else:
    print('O peixe não passou do peso limite!')
    print('Você não tem que pagar nada!')
print(f'o peixe passou do peso limite por {extra: .2f} kg')
print(f'o preço da multa é: {multa: .2f}')

