def soma_duplas(nums):
    return sum(n for n in set(nums) if nums.count(n) == 2)

print(soma_duplas([1, 2, 2, 3, 4, 6, 6]))
print(soma_duplas([1,2,3,4,5,5,6,6,7]))
print("----------------------------------------------------")

l = [1, 2, 2, 3, 4, 6, 6]

soma = 0
for n in set(l):
    if l.count(n) == 2:
        soma +=n
    

print(soma)
         
    
