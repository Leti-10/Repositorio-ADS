h = 80000
b = 200000
anos = 0
while h <= b:
  h = h + h * 0.03
  b = b + b * 0.015
  anos = anos + 1
print (f'A quantidade de anos necessários são: {anos}')
