import random
n=random.sample(range(1,101), 20)
par=[x for x in n if x%2==0]
ímpar=[x for x in n if x%2==1]
print('Esses foram os números sorteados:',n)
print('Esses números são par:',par)
print('Esses números são ímpar:',ímpar)
    


