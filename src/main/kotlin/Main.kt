fun main() {
    println("\n=== JOGO: 4 LINHA ===\n")
    val numLinhas = 6 // Exemplo: 6 linhas
    val numColunas = 7 // Exemplo: 7 colunas

    // Cria o tabuleiro
    val tabuleiro = criaTabuleiro(numLinhas, numColunas)
    var terminou = false
    var turnoJogador = true // True para jogador, false para computador

    // Loop principal do jogo
    while (!terminou) {
        if (turnoJogador) {
            println("\n--- Vez do Jogador ---")
            var jogadaValida = false
            while (!jogadaValida) {
                jogadaValida = jogadaJogador(tabuleiro)
            }
        } else {
            println("\n--- Vez do Computador ---")
            jogadaComputador(tabuleiro)
        }

        // Imprime o tabuleiro após a jogada
        imprimeTabuleiro(tabuleiro)

        // Verifica se algum jogador venceu
        if (ganhouJogo(tabuleiro)) {
            println("\n🎉 Vitória de ${if (turnoJogador) "Jogador" else "Computador"}!")
            terminou = true
        } else if (eEmpate(tabuleiro)) {
            println("\n🤝 Empate!")
            terminou = true
        }

        // Alterna o turno
        turnoJogador = !turnoJogador
    }

    // Opção de salvar ou carregar o jogo
    println("\nDeseja salvar o jogo ou carregar um jogo existente? (S para salvar, L para carregar, N para nada)")
    val opcao = readlnOrNull()?.uppercase()

    when (opcao) {
        "S" -> {
            println("Digite o nome do ficheiro para salvar:")
            val nomeFicheiro = readlnOrNull().orEmpty()
            val resultado = gravaJogo(tabuleiro, nomeFicheiro)  // Passando o tabuleiro correto
            println(resultado)
        }
        "L" -> {
            println("Digite o nome do ficheiro para carregar:")
            val nomeFicheiro = readlnOrNull().orEmpty()
            val resultado = leJogo(nomeFicheiro)
            println(resultado)
        }
        "N" -> println("Jogo encerrado.")
        else -> println("Opção inválida.")
    }
}

// Função para imprimir o tabuleiro com as jogadas
fun imprimeTabuleiro(tabuleiro: Array<Array<String?>>) {
    println("\n     === TABULEIRO DE JOGO ===\n")
    print("  ")
    for (coluna in 1..tabuleiro[0].size) {
        print(" $coluna  ")
    }
    println()

    for (linhaAtual in 0 until tabuleiro.size) {
        print("${linhaAtual + 1} ")
        for (coluna in 0 until tabuleiro[linhaAtual].size) {
            print("│ ${tabuleiro[linhaAtual][coluna]} ")
        }
        println("│")
        if (linhaAtual < tabuleiro.size - 1) {
            print("  ")
            repeat(tabuleiro[linhaAtual].size) {
                print("├───")
            }
            println("┤")
        }
    }
    println("\nLegenda: X - Jogador | O - Computador")
}

// Função para criar o tabuleiro
fun criaTabuleiro(numLinhas: Int, numColunas: Int): Array<Array<String?>> {
    return Array(numLinhas) { Array(numColunas) { " " } }
}

fun ganhouJogo(tabuleiro: Array<Array<String?>>): Boolean {
    return eVitoriaHorizontal(tabuleiro) || eVitoriaVertical(tabuleiro) || eVitoriaDiagonal(tabuleiro)
}

fun eEmpate(tabuleiro: Array<Array<String?>>): Boolean {
    if (ganhouJogo(tabuleiro)) return false
    return tabuleiro.all { linha -> linha.all { it != " " } }
}

fun jogadaComputador(tabuleiro: Array<Array<String?>>): Boolean {
    val colunasDisponiveis = tabuleiro[0].indices.filter { coluna ->
        tabuleiro.any { linha -> linha[coluna] == " " }
    }

    if (colunasDisponiveis.isEmpty()) return false

    val colunaEscolhida = colunasDisponiveis.random()
    for (linha in tabuleiro.indices.reversed()) {
        if (tabuleiro[linha][colunaEscolhida] == " ") {
            tabuleiro[linha][colunaEscolhida] = "O"
            return true
        }
    }
    return false
}

fun jogadaJogador(tabuleiro: Array<Array<String?>>): Boolean {
    println("Indique a coluna onde deseja jogar (1-${tabuleiro[0].size}): ")
    val coluna = readlnOrNull()?.toIntOrNull()

    if (coluna == null || coluna !in 1..tabuleiro[0].size) {
        println("Jogada inválida.")
        return false
    }

    val indiceColuna = coluna - 1
    for (linha in tabuleiro.indices.reversed()) {
        if (tabuleiro[linha][indiceColuna] == " ") {
            tabuleiro[linha][indiceColuna] = "X"
            return true
        }
    }

    println("Coluna cheia.")
    return false
}

fun gravaJogo(tabuleiro: Array<Array<String?>>, nomeFicheiro: String): String {
    if (nomeFicheiro.isEmpty()) return "Nome de ficheiro inválido."

    // Aqui estarias a escrever o ficheiro com os dados do tabuleiro
    return "$nomeFicheiro gravado com sucesso (simulado)\nTabuleiro: \n${tabuleiro.joinToString("\n") { it.joinToString(" | ") }}"
}

fun leJogo(nomeFicheiro: String): String {
    if (nomeFicheiro.isEmpty()) return "Nome de ficheiro inválido."
    // Aqui estarias a ler o ficheiro e a reconstruir o estado do jogo
    return "$nomeFicheiro lido com sucesso (simulado)"
}

fun eVitoriaHorizontal(tabuleiro: Array<Array<String?>>): Boolean {
    for (linha in tabuleiro) {
        for (i in 0..linha.size - 4) {
            val celula = linha[i]
            if (celula != " " && celula == linha[i + 1] && celula == linha[i + 2] && celula == linha[i + 3]) {
                return true
            }
        }
    }
    return false
}

fun eVitoriaVertical(tabuleiro: Array<Array<String?>>): Boolean {
    for (coluna in tabuleiro[0].indices) {
        for (linha in 0..tabuleiro.size - 4) {
            val celula = tabuleiro[linha][coluna]
            if (celula != " " && celula == tabuleiro[linha + 1][coluna]
                && celula == tabuleiro[linha + 2][coluna] && celula == tabuleiro[linha + 3][coluna]
            ) {
                return true
            }
        }
    }
    return false
}

fun eVitoriaDiagonal(tabuleiro: Array<Array<String?>>): Boolean {
    for (linha in 0..tabuleiro.size - 4) {
        for (coluna in 0..tabuleiro[0].size - 4) {
            val celula = tabuleiro[linha][coluna]
            if (celula != " " && celula == tabuleiro[linha + 1][coluna + 1]
                && celula == tabuleiro[linha + 2][coluna + 2] && celula == tabuleiro[linha + 3][coluna + 3]
            ) {
                return true
            }
        }
    }

    for (linha in 0..tabuleiro.size - 4) {
        for (coluna in 3 until tabuleiro[0].size) {
            val celula = tabuleiro[linha][coluna]
            if (celula != " " && celula == tabuleiro[linha + 1][coluna - 1]
                && celula == tabuleiro[linha + 2][coluna - 2] && celula == tabuleiro[linha + 3][coluna - 3]
            ) {
                return true
            }
        }
    }

    return false
}
