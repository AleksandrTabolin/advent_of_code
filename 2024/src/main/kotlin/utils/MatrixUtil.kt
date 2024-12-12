package utils

enum class Direction(val di: Int, val dj: Int) {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1);

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

data class Step(
    val i: Int,
    val j: Int,
    val direction: Direction,
)

fun Step.nextStep(): Step = Step(i + direction.di, j + direction.dj, direction)