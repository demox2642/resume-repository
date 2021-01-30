import kotlin.random.Random


class Team (person:Int) {

    val personList: List<Warrior> = setPersonList(person)

    private  fun setPersonList(person: Int) :List<Warrior>{
        val warriors = mutableListOf<Warrior>()
        for (i in 0..(person-1)){
            val randomInt:Int = Random.nextInt(0,100)

            print("я в setPersonList")

            if (randomInt in 0..45){
                warriors.add(i,Recruit())
                println("я создал Рекрута")
            }
            if (randomInt in 46..70){
                warriors.add(i,Sergeant())
                println("я создал Sergeant")
            }
            if (randomInt in 71..95){warriors.add(i,Сaptain ())
                println("я создал Сaptain")
            }
            if (randomInt in 96..100){warriors.add(i,General ())
                println("я создал General")
            }

        }
        println("я создал войнов")
        return warriors
    }
}
