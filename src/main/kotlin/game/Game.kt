package game

import game.properties.Grid
import game.properties.Player
import game.properties.Colors

open class Game(private var playersNumber: Int = 2, private val  pointsToWin : Int = 3, private var connectN : Int = 4) {
    private var playersList = listOf<Player>()
    init {
        if (playersNumber !in 1..4) {
            playersNumber = 2
        }
        else{
            //val playersList = listOf<Game.properties.Player>()
            //playersList = List(playersNumber) { Player(Colors.entries[it + 1]) }
            playersList = List(playersNumber) { Player(Colors.entries[(it + 1) % Colors.entries.size]) }
        }
        play()
    }

    private fun play(){
        var maxScore = 0
        var winnersColor : Colors = Colors.Empty
        while(pointsToWin != maxScore){
            val grid = Grid(7, 6)
            val player : Player = playRound(grid)
            maxScore = player.getPlayerScore()
            winnersColor = player.getColor()
        }

        println("Winner is player with $winnersColor color ")
    }

    private fun playRound(grid : Grid) : Player {
        while(true){
            for(player in playersList){
                if(player.playMove(player, grid, connectN)){
                    println("Player with color ${player.getColor()} won this round")
                    player.incPlayerPoints()
                    return player
                }
            }
        }
    }
}