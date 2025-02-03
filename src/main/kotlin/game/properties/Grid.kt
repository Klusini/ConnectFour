package game.properties

enum class Colors {
    Empty, Yellow, Red, Green, Blue
}

class Grid(private val cols :Int = 7, private val rows :Int = 6){
    private var gridVisualization = MutableList(rows) { MutableList(cols) { Colors.Empty } }

    fun placePiece(col : Int, row : Int, color : Colors){
                gridVisualization[row][col] = color
    }

    fun checkIfEmpty(col : Int) : Int {
        for(row in 0..< rows){
                if(gridVisualization[row][col] == Colors.Empty){
                    return row
                }
        }
        return -1
    }

    fun getColumns() : Int{
        return cols
    }

    private fun getRows() : Int{
        return rows
    }

    private fun checkPieceColor(col : Int, row : Int) : Colors {
        return gridVisualization[row][col]
    }

    fun checkForWin(grid : Grid, col : Int, row : Int, connectN : Int, color: Colors) : Boolean{
        var counter = 0
        for(i in (grid.getColumns() - 1) downTo 0){
            if(grid.checkPieceColor(i,row) == color){
                counter++
            }
            else {
                counter = 0
            }
            if(counter == connectN){
                return true
            }

        }

        counter = 0
        for(i in (grid.getRows() - 1) downTo 0){
            if(grid.checkPieceColor(col,i) == color){
                counter++
            }
            else{
                counter = 0
            }
            if(counter == connectN){
                return true
            }
        }

        var diagonals : Int
        counter = 0
        for(i in 0 ..< grid.getRows()){
            diagonals = col + row - i
            if(diagonals >= 0 && diagonals < grid.getColumns() && grid.checkPieceColor(diagonals,i) == color){
                counter++
            }
            else{
                counter = 0
            }
            if(counter == connectN){
                return true
            }
        }

        counter = 0
        for(i in 0 ..< grid.getRows()){
            diagonals = col - row + i
            if(diagonals >= 0 && diagonals < grid.getColumns() && grid.checkPieceColor(diagonals,i) == color){
                counter++
            }
            else{
                counter = 0
            }
            if(counter == connectN){
                return true
            }
        }
        return false
    }
}