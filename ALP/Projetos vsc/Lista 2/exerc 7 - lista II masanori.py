t=int(input("Metros² a serem pintados:"))
if t % 54 == 0:
    l= t/54
else:
    l=int(t/54)+1
v=l*80
print(f'Você irá precisar de {l} latas')
print(f'O valor total é: R$ {v:.2f}')
