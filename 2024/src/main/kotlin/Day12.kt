import utils.Direction
import utils.Step
import utils.nextStep
import utils.turnRight

object Day12 {

    fun solvePart1(input: Sequence<String>): Int {
        val board = input.toList()
        var result = 0
        val visited = mutableSetOf<Pair<Int, Int>>()
        for (i in board.indices) {
            for (j in board[i].indices) {
                if (i to j !in visited) {
                    val (area, perimeter) = board.countAreaAndPerimeter(i, j, visited)
                    result += area * perimeter
                }
            }
        }
        return result
    }

    fun solvePart2(input: Sequence<String>): Int {
        val board = input.toList()
        var result = 0
        val visited = mutableSetOf<Pair<Int, Int>>()
        for (i in board.indices) {
            for (j in board[i].indices) {
                if (i to j !in visited) {
                    val items = mutableListOf<Pair<Int, Int>>().apply { board.collect(i, j, visited, this) }
                    result += board.countSides(items) * items.size
                }
            }
        }
        return result
    }

    private fun List<CharSequence>.countSides(items: List<Pair<Int, Int>>): Int {
        val visited = mutableSetOf<Step>()
        var result = 0
        for (item in items) {
            val up = item.first - 1 to item.second
            if (isInBound(up) && get(up.first)[up.second] == get(item.first)[item.second]) {
                continue
            }
            val step = Step(item.first, item.second, Direction.LEFT)
            if (step !in visited) {
                result += countSides(step, visited)
            }
        }
        return result
    }

    private fun List<CharSequence>.collect(
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

    private fun List<CharSequence>.countSides(step: Step, visited: MutableSet<Step>): Int {
        if (step in visited) return 0
        visited.add(step)

        var sides = 0
        val nextStep = if (isNextStepSame(step.turnRight())) {
            sides += 1
            step.turnRight().nextStep()
        } else if (!isNextStepSame(step)) {
            sides += 1
            step.copy(direction = step.direction.turnLeft())
        } else {
            step.nextStep()
        }
        sides += countSides(nextStep, visited)
        return sides
    }

    private fun List<CharSequence>.isNextStepSame(step: Step): Boolean {
        val nextI = step.i + step.direction.di
        val nextJ = step.j + step.direction.dj
        return isInBound(nextI to nextJ) && get(nextI)[nextJ] == get(step.i)[step.j]
    }

    private fun List<CharSequence>.countAreaAndPerimeter(
        i: Int,
        j: Int,
        visited: MutableSet<Pair<Int, Int>>
    ): Pair<Int, Int> {
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
                val (a, p) = countAreaAndPerimeter(nextI, nextJ, visited)
                area += a
                perimeter += p
            }
        }
        return area to perimeter
    }

    private fun List<CharSequence>.isInBound(p: Pair<Int, Int>) = p.first in indices && p.second in get(p.first).indices
}