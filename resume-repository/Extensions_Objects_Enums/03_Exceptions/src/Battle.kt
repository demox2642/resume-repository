import kotlin.random.Random

class Battle(person :Int ) {
    val team1 : Team = Team(person)
    val team2 : Team = Team(person)
    var finBattle : Boolean = false



    fun getBattleState():BattleState{
        var team1Helth : Int = 0
        var team2Helth : Int = 0

        var state : BattleState = BattleState.Draw

        for (i in 0..(team1.personList.size-1))
        {
           team1Helth += team1.personList.get(i).health()

        }

        for (i in 0..(team2.personList.size-1))
        {
            team2Helth += team2.personList.get(i).health()

        }

        if (team1Helth > 0 && team2Helth > 0){state = BattleState.Progress(team1Helth,team2Helth)}
        if (team1Helth > 0 && team2Helth == 0)
        {
            state =  BattleState.WinTeam1
            finBattle = true
        }
        if (team1Helth == 0 && team2Helth > 0)
        {
            state =  BattleState.WinTeam2
            finBattle = true
        }


        return state
    }


    fun nextStepBattle(){
        team1.personList.shuffled()
        team2.personList.shuffled()

        var team1Helth : Int = 0
        var team2Helth : Int = 0

        for (i in 0..(team1.personList.size-1))
        {

            team1Helth += team1.personList.get(i).health()
        }

        for (i in 0..(team2.personList.size-1))
        {
            team2Helth += team2.personList.get(i).health()

        }


        if (Random.nextInt(1,2)==1)
        {
            buttle(team1,team2)
           // println(getBattleState().state)
            } else{
            buttle(team2,team1)
           // println(getBattleState().state)
        }
        println(getBattleState().state)
    }

    private fun buttle(team1: Team, team2: Team) {

        val livePersonTeam1 = team1.personList.filter { !it.isKilled }
        val livePersonTeam2 = team2.personList.filter { !it.isKilled }

        for (i in 0..(livePersonTeam2.size-1)) {
            if (i > livePersonTeam1.size-1) {
                livePersonTeam2.get(i).toAttack(livePersonTeam1.get(0))
            }
            else {

                livePersonTeam2.get(i).toAttack(livePersonTeam1.get(i))
            }
            if (i > (livePersonTeam1.size-1)) {
                break
            } else {
                if (livePersonTeam2.size < livePersonTeam1.size && i == (livePersonTeam2.size-1)) {
                    for (a in livePersonTeam2.size-1..livePersonTeam1.size-1) {
                        livePersonTeam1.get(a).toAttack(livePersonTeam2.get(0))
                    }
                }
                else {
                    livePersonTeam1.get(i).toAttack(livePersonTeam2.get(i))
                }
            }
        }
    }

}


