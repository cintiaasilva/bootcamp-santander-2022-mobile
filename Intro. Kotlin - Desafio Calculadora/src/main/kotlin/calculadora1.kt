import kotlin.math.pow

fun main(args: Array<String>) {
    println("Calculadora básica \n")
    receberNumeros()
}

fun receberNumeros() {
    println("Digite o primeiro número: ")
    val num1 = readLine()
    println("Digite o segundo número: ")
    val num2 = readLine()
    validarNumeros(num1, num2)

}

fun validarNumeros(num1: String?, num2: String?) {

    if (num1.isNullOrEmpty() || num2.isNullOrEmpty()) {
        println("Inválido! Digite um número para ser calculado")
        receberNumeros()
    } else {
        println(
            "Digite o número da operação que quer calcular: \n" +
                    "----------------------------------\n" +
                    "1 - soma \n" +
                    "2 - subtração \n" +
                    "3 - multiplicação \n" +
                    "4 - divisão \n" +
                    "5 - porcentagem \n" +
                    "6 - potência \n" +
                    "----------------------------------\n"
        )
        val operador = readLine()

        val resultado = calcularNumeros(num1.toFloat(), num2.toFloat(), operador!!.toInt())
        println("O resultado desta operação é: %.2f".format(resultado))
    }
}

fun calcularNumeros(num1: Float?, num2: Float?, operador: Int): Float? {
    return when (operador) {
        1 -> num1!! + num2!!
        2 -> num1!! - num2!!
        3 -> num1!! * num2!!
        4 -> num1!! / num2!!
        5 -> (num1!! * num2!!) / 100
        6 -> num1!!.pow(num2!!)
        else -> {
            println("Inválido! Digite o número da operação \n")
            receberNumeros()
            null
        }
    }
}
// o ponto de exclamação dupla (!!) tenta acessar o valor da variável, mas se ela for nula lança uma NullPointerException

