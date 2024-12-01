import java.util.LinkedList

object Day21 {

    fun solvePart1(input: Sequence<String>): Int {
        val maze = input.map { it.toMutableList() }.toList()

        val start = maze.findStartPosition()

        maze.set(start, 'O')

        while(true) {
            for (y in maze.indices) {
                for (x in maze[y].indices) {
                    if (maze[y][x] == 'O') {


                    }
                }
            }
        }



        return -1
    }


    private fun List<List<Char>>.findStartPosition(): Position {
        val maze = this
        for (y in maze.indices) {
            for (x in maze[y].indices) {
                if (maze[y][x] == 'S') return Position(x = x, y = y)
            }
        }
        throw IllegalStateException("there is no start point")
    }

    fun List<MutableList<Char>>.set(position: Position, char: Char) {
        get(position.y)[position.x] = char
    }

    fun List<List<Char>>.get(position: Position) : Char = get(position.y)[position.x]

    fun solvePart2(input: Sequence<String>): Int {
        return -1
    }


    data class Position(
        val x: Int,
        val y: Int,
    )
}