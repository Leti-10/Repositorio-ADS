text="the python software foundation and the global python community welcome and encourage participation by everyone  our community is based on mutual respect  tolerance  and encouragement  and we are working to help each other live up to these principles  we want our community to be more diverse  whoever you are  and whatever your background  we welcome you".split()
def python(palavra):
    for letra in palavra:
        if letra in 'python':
            return True
        return False
lista=[]
for l in text:
    if python(l) and len(l)>4:
        lista.append(l)
print(lista)
print(len(lista))
