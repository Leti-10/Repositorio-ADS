import random
from itertools import chain
n=random.sample(range(1,101), 10)
k=random.sample(range(1,101), 10)
lista= chain.from_iterable(zip(n, k))
print(f'Lista 1 ={n}\nLista 2 ={k}')
print('Lista intercalada =', [*lista])

