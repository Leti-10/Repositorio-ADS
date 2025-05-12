s=float(input('Valor do salário atual:'))
a=float(input('Percentual do aumento:'))
aumento=s*a/100
novo=s+aumento
print(f'O valor do aumento é: R${aumento:.2f}')
print(f'O salário novo é: R${novo:.2f}')
