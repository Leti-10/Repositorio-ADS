a=int(input('Lado a:'))
b=int(input('Lado b:'))
c=int(input('Lado a:'))
if a>a+b or b>a+c or c>a+b:
    print('Não pode ser um triângulo')
elif a==b==c:
    print('Equilátero')
elif a==b or b==c or c==a:
    print('isóceles')
else:
    print('Escaleno')
