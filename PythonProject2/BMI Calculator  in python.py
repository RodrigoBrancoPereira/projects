# Solicita ao utilizador o peso e a altura
peso = float(input("Introduza o peso (em kg): "))
altura = float(input("Introduza a altura (em metros): "))

# Calcula o IMC
imc = peso / (altura ** 2)

# Determina a categoria e uma recomendação
if imc < 19:
    categoria = "Magro"
    recomendacao = "Recomenda-se consultar um nutricionista para avaliar uma possível dieta de aumento de peso."
elif 19 <= imc <= 25:
    categoria = "Normal"
    recomendacao = "Parabéns! Mantenha uma alimentação equilibrada e pratique exercício físico regularmente."
elif 25 < imc <= 30:
    categoria = "Excesso de peso"
    recomendacao = "Considere ajustar a alimentação e aumentar a prática de exercício físico."
elif 30 < imc <= 40:
    categoria = "Obeso"
    recomendacao = "É aconselhável procurar orientação médica e nutricional para melhorar a saúde."
else:
    categoria = "Obesidade mórbida"
    recomendacao = "Procure ajuda médica com urgência. É importante cuidar da sua saúde."

# Calcula o peso mínimo e máximo para a categoria "Normal"
peso_minimo_normal = 19 * (altura ** 2)
peso_maximo_normal = 25 * (altura ** 2)

# Apresenta os resultados
print(f"\nIMC: {imc:.2f}")
print(f"Categoria: {categoria}")
print(f"Peso mínimo para a categoria 'Normal': {peso_minimo_normal:.2f} kg")
print(f"Peso máximo para a categoria 'Normal': {peso_maximo_normal:.2f} kg")
print(f"Recomendação: {recomendacao}")
