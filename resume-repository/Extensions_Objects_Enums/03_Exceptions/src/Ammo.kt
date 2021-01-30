import kotlin.random.Random

enum class Ammo (
    val damage: Int,
    val criticalDamageChance: Int,
    val criticalDamageRatio: Int
) {
    GUNCARTIDGE(10,10,2),
    MACHINECARTIDGE(30,20,4),
    MACHINECARTIDGEARMORPIERCING(60,20,6),
    SNIPERRIFLECARTIDGE(55,50,8),
    SNIPERRIFLECARTIDGEARMORPIERCING(70,50,8);

    fun totalDamage():Int{
        var cartidgeDamage:Int = damage
        if (Random.nextInt(1,100) == criticalDamageChance){
            cartidgeDamage *= criticalDamageRatio
        }

        return cartidgeDamage
    }

}