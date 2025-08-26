function Empresa(nome, razaoSocial){
    this.nome = nome
    this.razaoSocial = razaoSocial
    this.detalhe = function(){
        return this.nome + "\n" + this.razaoSocial
    }
}

let emp = new Empresa("Mercado Online", "1234567897898" )
let emp2 = new Empresa("Walmart", "1234509604900")
console.log("Detalhe da empresa: \n" + emp.detalhe() )