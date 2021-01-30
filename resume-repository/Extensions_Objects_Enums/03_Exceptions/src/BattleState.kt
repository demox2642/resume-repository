sealed class BattleState(val state : String){

    class Progress(team1Health:Int,team2Health:Int) : BattleState("Статус команд:commandAHealth=$team1Health, commandBHealth=$team2Health")
    object WinTeam1 : BattleState("Победила команда 1")
    object WinTeam2 : BattleState("Победила команда 2")
    object Draw : BattleState("Ничья")
}

