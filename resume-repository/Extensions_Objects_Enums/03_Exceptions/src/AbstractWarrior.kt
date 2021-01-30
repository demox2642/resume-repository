import kotlin.random.Random

abstract class AbstractWarrior(
    val maximumHealth: Int,
    override val dodge: Int,
    val accuracy: Int,
    val weapon: AbstractWeapon
) : Warrior {

    var health: Int = maximumHealth
    override var isKilled = false

//    override fun toAttack(warrior: Warrior){
//        if (!weapon.presenceCartridges){
//            weapon.recharge()
//        }else {
//            var demage:Int = 0
//           if (Random.nextInt(1,100) >= accuracy && Random.nextInt(1,100) >=  dodge){
//              val cartidgeForShot: List<Ammo> = weapon.getCartidgeForShot()
//               for (i in 0 until cartidgeForShot.size){
//                   demage += cartidgeForShot.get(i).totalDamage()
//               }
//           }
//            warrior.takeDamage(demage)
//        }
//    }

    fun checkAvailabilityCartidge(warrior: Warrior) {
        if (!weapon.presenceCartridges) {
            throw Exception("NoAmmoException")
        }
    }

    override fun toAttack(warrior: Warrior) {
        try {
            checkAvailabilityCartidge(warrior)
            var demage: Int = 0
            if (Random.nextInt(1, 100) >= accuracy && Random.nextInt(1, 100) >= dodge) {
                val cartidgeForShot: List<Ammo> = weapon.getCartidgeForShot()
                for (i in 0 until cartidgeForShot.size) {
                    demage += cartidgeForShot.get(i).totalDamage()
                }
            }
            warrior.takeDamage(demage)
        } catch (e: Exception) {

            println("${warrior.javaClass.name}: У меня кончились патроны. Перезаряжаюсь.")
            weapon.recharge()
            println("${warrior.javaClass.name}: Патронов полная обойма")
        }
    }

    override fun takeDamage(demage: Int) {
        if (health - demage <= 0) {
            health = 0
            isKilled = true
        } else {
            health -= demage
        }
    }

    override fun health(): Int {
        return health
    }
}
