import random
v=random.sample(range(1,101), 10)
maior=menor=v[0]
for x in v[1:]:
    if x>maior:maior=x
    if x<menor:menor=x
print(f'O vetor é: {v}')
print(f'O maior número é: {maior}')
print(f'O menos número é: {menor}')

