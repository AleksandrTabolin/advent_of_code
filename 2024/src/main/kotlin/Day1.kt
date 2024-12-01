import kotlin.math.abs

object Day1 {

    fun solvePart1(input: Sequence<String>): Int {
        return input
            .parseInput()
            .fold(mutableListOf<Int>() to mutableListOf<Int>()) { acc, (l, r) ->
                acc.apply {
                    first.add(l)
                    second.add(r)
                }
            }.let { acc ->
                acc.first.sorted().zip(acc.second.sorted()) { l, r -> abs(l - r) }.sum()
            }
    }

    fun solvePart2(input: Sequence<String>): Int {
        return input
            .parseInput()
            .fold(mutableListOf<Int>() to mutableMapOf<Int, Int>()) { acc, (l, r) ->
                acc.apply {
                    acc.first.add(l)
                    acc.second[r] = acc.second.getOrDefault(r, 0) + 1
                }
            }.let { acc ->
                acc.first.sumOf { it * acc.second.getOrDefault(it, 0) }
            }
    }

    private fun Sequence<String>.parseInput(): Sequence<Pair<Int, Int>> {
        return map { line ->
            line.split("\\s+".toRegex()).let { it[0].toInt() to it[1].toInt() }
        }
    }
}