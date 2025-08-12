def zigue_zague(s):
    resul=""
    for x in range(len(s)):
        if x % 2 == 0:
            resul += s[x].upper()
        else:
            resul += s[x]
    return resul

print(f'{zigue_zague('julia')}')