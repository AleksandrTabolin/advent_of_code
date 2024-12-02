import utils.withoutItemAt
import kotlin.math.abs

object Day2 {

    fun solvePart1(input: Sequence<String>): Int {
        return input
            .parseInput()
            .map { if (it.isSafeEffective()) 1 else 0 }
            .sum()
    }

    fun solvePart2(input: Sequence<String>): Int {
        return input
            .parseInput()
            .map { if (it.isTolerantSafeEffective()) 1 else 0 }
            .sum()
    }

    private fun List<Int>.isSafe(): Boolean {
        val isIncreased = get(0) > get(1)
        return windowed(2).all { it[0] > it[1] == isIncreased && abs(it[0] - it[1]) in 1..3 }
    }

    private fun List<Int>.isTolerantSafe(): Boolean {
        if (isSafe()) return true

        for (i in this.indices) {
            if (withoutItemAt(i).isSafe()) return true
        }
        return false
    }

    private fun List<Int>.isSafeEffective(): Boolean {
        var sign: Boolean? = null
        for (i in 1 until size) {
            val diff = get(i) - get(i - 1)
            if (sign != null && sign != diff > 0 || abs(diff) !in 1..3) return false
            sign = diff > 0
        }
        return true
    }

    private fun List<Int>.isTolerantSafeEffective(): Boolean {
        var increasedPairs = 0
        var decreasedPairs = 0
        for (i in 1 until size) {
            val diff = get(i) - get(i - 1)
            if (diff > 0) {
                increasedPairs += 1
            } else if (diff < 0) {
                decreasedPairs += 1
            }
        }
        val result = lis(if (increasedPairs > decreasedPairs) 1 else -1)
        return size - result < 2
    }

    private fun List<Int>.lisEndingAtIdx(idx: Int, sign: Int): Int {
        if (idx == 0) return 1

        var result = 1

        for (prev in 0 until idx) {
            if (checkViolation(sign, get(prev), get(idx))) {
                result = kotlin.math.max(result, lisEndingAtIdx(prev, sign) + 1)
            }
        }
        return result
    }

    private fun List<Int>.lis(sign: Int): Int {
        var result = 1
        for (idx in 1 until size) {
            result = kotlin.math.max(result, lisEndingAtIdx(idx, sign))
        }
        return result
    }

    private fun checkViolation(sign: Int, prev: Int, current: Int): Boolean {
        return sign * prev < sign * current && abs(prev - current) in 1..3
    }

    private fun Sequence<String>.parseInput(): Sequence<List<Int>> {
        return map { line -> line.split(' ').map { it.toInt() } }
    }
}