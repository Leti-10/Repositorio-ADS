salário=float(input('Salário por hora:'))
mes=float(input('Horas trabalhadas no mês:'))
bruto= salário*mes

ir=bruto*0.11
inss=bruto*0.08
sind=bruto*0.05
s_l= bruto - ir-inss-sind
print(f' \n O salário bruto é: {bruto:.2f}\n \n O salário líquido é: R$ {s_l:.2f}\n \n O valor pago ao IR é de: R${ir:.2f}\n \n O valor pago ao INSS é de:{inss:.2f}\n \n O valor pago ao Sindicato é de: R${sind:.2f}\n ')
