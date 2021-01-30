fun main() {
    println("Введите количество войнов в команде")
    val line = readLine()
    val num: Int? = line?.toIntOrNull()
    if (num != null) {
        println("Вы ввели число: $num")

        //     Battle(num)
        val battle = Battle(num)
        while (!battle.finBattle) {
            battle.nextStepBattle()
        }
//        battle.nextStepBattle()
//        battle.nextStepBattle()
    } else {
        println("Вы ввели некорректное число")
    }
}
