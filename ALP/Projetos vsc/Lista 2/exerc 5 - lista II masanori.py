a=float(input('Insira um númuro:'))
b=float(input('Insira um númuro:'))
c=float(input('Insira um númuro:'))
if a<=b and a<=c:
    print(f'{a} é o menor número')
elif b<=c:
    print(f'{b} é o menor número')
else:
    print(f'{c} é o menor número')

if a>=b and a>=c:
    print(f'{a} é o maior número')
elif b>=c:
    print(f'{b} é o maior número')
else:
    print(f'{c} é o maior número')