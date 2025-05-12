a=float(input('Insira um númuro:'))
b=float(input('Insira um númuro:'))
c=float(input('Insira um númuro:'))
if a>=b and a>=c:
    print(f'{a:.2f} é o maior número')
elif b>=c:
    print(f'{b:.2f} é o maior número')
else:
    print(f'{c:.2f} é o maior número')
