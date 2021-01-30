import AbstractWeapon as AbstractWeapon


object Weapons {

    object Pistol: AbstractWeapon(12,FireType.SingleShot){
        override fun createCartidge(): Ammo {
             return Ammo.GUNCARTIDGE
        }

    }

    object Ak47:AbstractWeapon(32,FireType.BurstShot) {
        override fun createCartidge(): Ammo {

            return Ammo.MACHINECARTIDGE
        }
    }

    object  SnaiperRifle:AbstractWeapon(5,FireType.SingleShot) {
        override fun createCartidge(): Ammo {

            return Ammo.SNIPERRIFLECARTIDGE
        }
    }

    object MiniGan: AbstractWeapon(100,FireType.BurstShot) {
        override fun createCartidge(): Ammo {

            return Ammo.MACHINECARTIDGEARMORPIERCING
        }
    }

}


