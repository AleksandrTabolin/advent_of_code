import utils.Direction
import utils.inBound
import utils.nextPoint
import utils.reverse

object Day18 {

    fun solvePart1(input: Sequence<String>, fallSize: Int, size: Int): Int {
        val corrupted = input.parseInput().take(fallSize).toSet()
        return bfs(size, corrupted)
    }

    fun solvePart2(input: Sequence<String>, fallSize: Int, size: Int): Pair<Int, Int> {
        val corrupted = input.parseInput()
        for (i in fallSize..corrupted.lastIndex) {
            if (bfs(size, corrupted.take(i).toSet()) == -1) return corrupted[i - 1].reverse()
        }
        return -1 to -1
    }

    private fun bfs(
        size: Int,
        corrupted: Set<Pair<Int, Int>>,
    ): Int {
        val queue = ArrayDeque<Pair<Pair<Int, Int>, Int>>()
        val start = 0 to 0
        queue.add(start to 0)
        val visited = mutableSetOf<Pair<Int, Int>>()
        visited.add(start)

        while (queue.isNotEmpty()) {
            val (pos, steps) = queue.removeFirst()

            if (pos.first == size - 1 && pos.second == size - 1) return steps

            Direction.entries.forEach { d ->
                val nextPos = pos.nextPoint(d)
                if (nextPos.inBound(size) && nextPos !in visited && nextPos !in corrupted) {
                    visited.add(nextPos)
                    queue.addLast(nextPos to steps + 1)
                }
            }
        }
        return -1
    }

    private fun Sequence<String>.parseInput(): List<Pair<Int, Int>> {
        return map { line -> line.split(',').let { it.last().toInt() to it.first().toInt() } }.toList()
    }
}