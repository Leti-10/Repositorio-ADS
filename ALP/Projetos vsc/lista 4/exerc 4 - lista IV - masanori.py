text='The Python Software Foundation and the global Python community welcome and encourage participation by everyone. Our community is based on mutual respect, tolerance, and encouragement, and we are working to help each other live up to these principles. We want our community to be more diverse: whoever you are, and whatever your background, we welcome you.'.lower()
import string
for p in string.punctuation:
    text=text.replace(p, ' ')
lista=[]
for l in text.split():
    if l[0] in 'python' or l[-1] in 'python':
        lista.append(l)
print(lista)
