def fat(a):
    n=1
    f=1
    while True:
        f=f*n
        yield f
        n=n+1
a=fat(0)
for i in range(5):
    print(next(a), end=' ')
        