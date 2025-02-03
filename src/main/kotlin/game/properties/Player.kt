package game.properties

class Player(private var color: Colors) {
    private var playerPoints :Int = 0

    fun getColor(): Colors {
        return color
    }

    fun  incPlayerPoints() {
        playerPoints += 1
    }

    fun getPlayerScore() : Int{
        return playerPoints
    }

    fun playMove(player : Player, grid: Grid, connectN : Int) : Boolean{
        println("Place your token on a empty field")
        var col = readlnOrNull()?.toInt()
        while((col == null)  || col !in 0..<grid.getColumns() || grid.checkIfEmpty(col) == -1){
            println("Choose an empty field !")
            col = readlnOrNull()?.toInt()
        }
        val row = grid.checkIfEmpty(col)
        grid.placePiece(col, row, player.getColor())
        return grid.checkForWin(grid, col, row, connectN, player.getColor())
    }
}