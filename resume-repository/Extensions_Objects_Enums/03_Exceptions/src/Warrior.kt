interface Warrior {
    var isKilled:Boolean
    val dodge:Int

    fun toAttack(warrior :Warrior)
    fun takeDamage(damage :Int)
    fun health(): Int
}