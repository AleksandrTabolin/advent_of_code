package utils

enum class Direction(val di: Int, val dj: Int, val isVertical: Boolean = false, val isHorizontal: Boolean = false) {
    UP(-1, 0, isVertical = true),
    DOWN(1, 0, isVertical = true),
    LEFT(0, -1, isHorizontal = true),
    RIGHT(0, 1, isHorizontal = true);

    fun turnLeft(): Direction {
        return when (this) {
            UP -> LEFT
            DOWN -> RIGHT
            RIGHT -> UP
            LEFT -> DOWN
        }
    }

    fun turnRight(): Direction {
        return when (this) {
            UP -> RIGHT
            DOWN -> LEFT
            RIGHT -> DOWN
            LEFT -> UP
        }
    }
}


fun String.parseDirections(): List<Direction> {
    return map { d ->
        when (d) {
            '>' -> Direction.RIGHT
            'v' -> Direction.DOWN
            '<' -> Direction.LEFT
            '^' -> Direction.UP
            else -> throw IllegalStateException("Unknown direction: '$d'")
        }
    }
}

data class Step(
    val i: Int,
    val j: Int,
    val direction: Direction,
)

fun Step.turnRight(): Step = copy(direction = direction.turnRight())

fun Step.turnLeft(): Step = copy(direction = direction.turnLeft())

fun Step.nextStep(): Step = Step(i + direction.di, j + direction.dj, direction)