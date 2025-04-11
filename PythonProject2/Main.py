# Solicita ao usuário o peso e a altura
peso = float(input("Digite o peso (em kg): "))
altura = float(input("Digite a altura (em metros): "))

# Calcula o IMC
imc = peso / (altura ** 2)

# Determina a categoria
if imc < 19:
    categoria = "Magro"
elif 19 <= imc <= 25:
    categoria = "Normal"
elif 25 < imc <= 30:
    categoria = "Excesso de peso"
elif 30 < imc <= 40:
    categoria = "Obeso"
else:
    categoria = "Obesidade mórbida"

# Calcula o peso mínimo e máximo para a categoria "Normal"
peso_minimo_normal = 19 * (altura ** 2)
peso_maximo_normal = 25 * (altura ** 2)

# Apresenta os resultados
print(f"\nIMC: {imc:.2f}")
print(f"Categoria: {categoria}")
print(f"Peso mínimo para a categoria Normal: {peso_minimo_normal:.2f} kg")
print(f"Peso máximo para a categoria Normal: {peso_maximo_normal:.2f} kg")
