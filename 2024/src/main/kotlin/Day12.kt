import utils.Direction
import utils.Step
import utils.nextStep

object Day12 {

    fun solvePart1(input: Sequence<String>): Int {
        val board = input.toList().map { it.toCharArray() }
        var result = 0
        val visited = mutableSetOf<Pair<Int, Int>>()
        for (i in board.indices) {
            for (j in board[i].indices) {
                if (i to j !in visited) {
                    val (area, perimeter) = board.count(i, j, visited)
                    result += area * perimeter
                }
            }
        }
        return result
    }

    fun solvePart2(input: Sequence<String>): Int {
        val board = input.toList().map { it.toCharArray() }
        var result = 0
        val visited = mutableSetOf<Pair<Int, Int>>()
        for (i in board.indices) {
            for (j in board[i].indices) {
                if (i to j !in visited) {
                    val items = mutableListOf<Pair<Int, Int>>()
                    board.collect(i, j, visited, items)
                    val sides = board.countSides(items)
                    val area = items.size
                    result += sides * area
                }
            }
        }
        return result
    }

    private fun List<CharArray>.countSides(items: List<Pair<Int, Int>>): Int {
        val innerVisited = mutableSetOf<Step>()
        var result = 0
        for (item in items) {
            val up = item.first - 1 to item.second
            if (isInBound(up) && get(up.first)[up.second] == get(item.first)[item.second]) {
                continue
            }
            val step = Step(item.first, item.second, Direction.LEFT)

            if (step !in innerVisited) {
                result += countSides(step, innerVisited)
            }
        }
        return result
    }

    private fun List<CharArray>.collect(
        i: Int,
        j: Int,
        visited: MutableSet<Pair<Int, Int>>,
        result: MutableList<Pair<Int, Int>>
    ) {
        val char = get(i)[j]
        result.add(i to j)
        visited.add(i to j)
        for (d in Direction.entries) {
            val nextI = i + d.di
            val nextJ = j + d.dj
            if (isInBound(nextI to nextJ) && get(nextI)[nextJ] == char && nextI to nextJ !in visited) {
                collect(nextI, nextJ, visited, result)
            }
        }
    }

    private fun List<CharArray>.countSides(step: Step, visited: MutableSet<Step>): Int {
        if (step in visited) return 0
        visited.add(step)

        var sides = 0
        val nextStep: Step

        if (isRightSame(step)) {
            sides += 1
            nextStep = step.copy(direction = step.direction.turnRight()).nextStep()
        } else if (isNextStepSame(step)) {
            nextStep = step.nextStep()
        } else {
            sides += 1
            nextStep = step.copy(direction = step.direction.turnLeft())
        }
        sides += countSides(nextStep, visited)
        return sides
    }

    private fun List<CharArray>.isNextStepSame(step: Step): Boolean = isNextStepSame(step.i, step.j, step.direction)

    private fun List<CharArray>.isRightSame(step: Step): Boolean {
        val up = step.direction.turnRight()
        return isNextStepSame(step.i, step.j, up)
    }

    private fun List<CharArray>.isNextStepSame(i: Int, j: Int, direction: Direction): Boolean {
        val nextI = i + direction.di
        val nextJ = j + direction.dj
        return isInBound(nextI to nextJ) && get(nextI)[nextJ] == get(i)[j]
    }

    private fun List<CharArray>.count(i: Int, j: Int, visited: MutableSet<Pair<Int, Int>>): Pair<Int, Int> {
        var area = 1
        var perimeter = 0
        val char = get(i)[j]
        visited.add(i to j)
        for (d in Direction.entries) {
            val nextI = i + d.di
            val nextJ = j + d.dj
            if (!isInBound(nextI to nextJ) || get(nextI)[nextJ] != char) {
                perimeter += 1
            } else if (nextI to nextJ !in visited) {
                val (a, p) = count(nextI, nextJ, visited)
                area += a
                perimeter += p
            }
        }
        return area to perimeter
    }

    private fun List<CharArray>.isInBound(p: Pair<Int, Int>) = p.first in indices && p.second in get(p.first).indices

}