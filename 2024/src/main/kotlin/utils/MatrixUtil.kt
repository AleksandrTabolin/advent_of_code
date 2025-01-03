package utils

enum class Direction(
    val di: Int,
    val dj: Int,
    val isVertical: Boolean = false,
    val isHorizontal: Boolean = false,
    val sign: Char
) {
    UP(di = -1, dj = 0, isVertical = true, sign = '^'),
    DOWN(di = 1, dj = 0, isVertical = true, sign = 'v'),
    LEFT(di = 0, dj = -1, isHorizontal = true, sign = '<'),
    RIGHT(di = 0, dj = 1, isHorizontal = true, sign = '>');

    fun turnLeft() = when (this) {
        UP -> LEFT
        DOWN -> RIGHT
        RIGHT -> UP
        LEFT -> DOWN
    }

    fun turnRight() = when (this) {
        UP -> RIGHT
        DOWN -> LEFT
        RIGHT -> DOWN
        LEFT -> UP
    }

    fun turnBack() = when (this) {
        UP -> DOWN
        DOWN -> UP
        RIGHT -> LEFT
        LEFT -> RIGHT
    }

    fun isOrthogonal(d: Direction) = isVertical != d.isVertical
}


fun String.parseDirections(): List<Direction> {
    return map { d -> Direction.entries.first { it.sign == d } }
}

data class Step(
    val i: Int,
    val j: Int,
    val direction: Direction,
)

fun Pair<Int, Int>.dir(p2: Pair<Int, Int>): Direction {
    val p1 = this
    return when (p1.first - p2.first) {
        1 -> Direction.UP
        -1 -> Direction.DOWN
        else -> when (p1.second - p2.second) {
            1 -> Direction.LEFT
            -1 -> Direction.RIGHT
            else -> throw IllegalStateException("wrong!")
        }
    }
}

fun Step.turnRight(): Step = copy(direction = direction.turnRight())

fun Step.turnLeft(): Step = copy(direction = direction.turnLeft())

fun Step.nextStep(): Step = Step(i + direction.di, j + direction.dj, direction)

fun List<CharArray>.isInBound(p: Pair<Int, Int>) = p.first in indices && p.second in get(p.first).indices

fun List<CharArray>.getValue(p: Pair<Int, Int>): Char = get(p.first)[p.second]

fun List<CharArray>.setValue(p: Pair<Int, Int>, char: Char) {
    get(p.first)[p.second] = char
}

fun Pair<Int, Int>.nextPoint(d: Direction) = first + d.di to second + d.dj

fun Pair<Int, Int>.reverse() = second to first

fun Pair<Int, Int>.inBound(size: Int) = first in 0 until size && second in 0 until size

fun List<CharArray>.print() {
    forEach { row ->
        row.forEach { print(it) }
        println()
    }
}

fun pointsInFourDirections(pos: Pos) : Sequence<Pos> = Direction.entries.asSequence().map(pos::nextPoint)

typealias Pos = Pair<Int, Int>