abstract class AbstractWeapon(
        val maxOfRoundsPerMagazine: Int,
        val fireType: FireType

) {

    val ammo = mutableListOf<Ammo>()

    val presenceCartridges: Boolean
        get() = ammo.isNotEmpty()

    abstract fun createCartidge(): Ammo

    fun recharge() {
        ammo.clear()
        for (i in 1..maxOfRoundsPerMagazine) {
            ammo.add(createCartidge())
        }
    }

    fun getCartidgeForShot(): List<Ammo> {
        val cartidgeList = mutableListOf<Ammo>()

        for (i in 0..fireType.numberOfShots) {
            if (ammo.isEmpty()) {
                break
            }
            cartidgeList.add(ammo.removeAt(ammo.size - 1))
        }

        return cartidgeList
    }
}