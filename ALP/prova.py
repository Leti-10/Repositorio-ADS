#1.#

nums=[2, 7, 1, -1, 10]
soma=0
quant=0
for x in nums:
    soma+=x
    quant+=1
media= soma/quant
print(f'A média é:{media}')

print()
#2.#

a=int(input('Insira o Lado a:'))
b=int(input('Insira o Lado b:'))
c=int(input('Insira o Lado c:'))
if a>b+c or b>c+a or c>a+b:
    print('Não pode ser um triângulo!')
elif a==b==c:
    print('Equilátero!')
elif b==c or a==b or a==c:  
    print('Isóceles!')
else:
    print('Escaleno!')

print()
#3.#

h= 80000
b=200000
anos=0
while b>=h:
    h=h+h*0.03
    b=b+b*0.015
    anos=anos+1
print(f'A quantidade de anos necessários são: {anos}')

print()
#4.#
# a) lista=[4, 1, 3, 10]
# b) for x in lista: print(x)

lista=[4, 1, 3, 10]
quant=0
while quant<len(lista):
    print(lista[quant])
    quant+=1

print()
#5.#

print(len([x for x in range(0,10000)
           if '42' in str(x) ]))

print()
#6.#

def apaga(s, n):
    return s[:n]+s[n+1:]

print()
#7.#

def seguindo(n):
    for x in range(10):
        if str(x)+str(x) in n:
            return True
    return False

print()
#8.#

def date_fashion(eu, par):
    if eu <=2 or par<=2:
        return 0
    if eu >=8 or par>=8:
        return 2
    return 1

print()
#9.#

def has22(nums):
    return '2, 2' in str(nums)

print()
#10.#

def pego_correndo(speed, is_birthday):
    if is_birthday:
        speed=speed-5
    if speed<=60:
        return 0
    if 61<=speed<=80:
        return 1
    return 2

def troca(a, b):
    return a==b and b==a


def test(obtido, esperado):
  if obtido == esperado:
    prefixo = ' Parabéns!'
  else:
    prefixo = ' Ainda não'
  print ('%s obtido: %s esperado: %s'
         % (prefixo, repr(obtido), repr(esperado)))

def main():
  print ('seguindo')
  test(seguindo('11234'), True)
  test(seguindo('12345'), False)
  
  print ()
  print ('date fashion')
  test(date_fashion(5, 10), 2)
  test(date_fashion(5, 2), 0)
  test(date_fashion(5, 5), 1)
  test(date_fashion(3, 3), 1)
  test(date_fashion(10, 2), 0)
  test(date_fashion(2, 9), 0)
  test(date_fashion(9, 9), 2)
  test(date_fashion(10, 5), 2)
  test(date_fashion(2, 2), 0)
  test(date_fashion(3, 7), 1)
  test(date_fashion(2, 7), 0)
  test(date_fashion(6, 2), 0)

if __name__ == '__main__':
    main()