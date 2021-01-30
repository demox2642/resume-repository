sealed class FireType(val numberOfShots:Int) {
    object SingleShot : FireType(1)
    object BurstShot : FireType(5)
}