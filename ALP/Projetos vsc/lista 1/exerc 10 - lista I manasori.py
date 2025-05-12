c=int(input('Cigarros fumados p/d:'))
anos=int(input('Anos fumando:'))
tf=anos*365*c
tempo=tf*10/60/24
print(f'O tempo perdido é de {tempo:.1f} dias.')
