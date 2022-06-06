import java.util.*
import kotlin.math.pow

fun main(args: Array<String>) {
    println("Calculadora básica \n")
    coletarNumeros()
}

fun coletarNumeros(){
    val read = Scanner(System.`in`)
    println("Digite o primeiro número: ")
    val num1 = read.nextFloat()
    println("Digite o segundo número: ")
    val num2 = read.nextFloat()

    println("Digite o número que representa a operação que pretende realizar: \n" +
            "----------------------------------\n" +
            "1 - soma \n" +
            "2 - subtração \n" +
            "3 - multiplicação \n" +
            "4 - divisão \n" +
            "5 - porcentagem \n" +
            "6 - potência \n"+
            "----------------------------------\n")

    val operador = read.nextInt()
    checarNumeros(num1, num2, operador)
}

fun checarNumeros(n1: Float?, n2: Float?, operador: Int?) {
    if (operador != null || operador in 1..4) {
        val resultado = calcular(n1, n2, operador)
        println("O resultado desta operação é: %.2f".format(resultado))
    }

}

fun calcular(n1: Float?, n2: Float?, operador: Int?) : Float?{
        return when (operador) {
            1 -> n1!! + n2!!
            2 -> n1!! - n2!!
            3 -> n1!! * n2!!
            4 -> n1!! / n2!!
            5 -> (n1!! * n2!!) / 100
            6 -> n1!!.pow(n2!!)
            else -> {
                println("Digite a operação para calcular")
                coletarNumeros()
                null
            }
        }
}


