object Day7 {

    private val OP1 = arrayOf(Op.MULT, Op.PLUS)
    private val OP2 = arrayOf(Op.MULT, Op.PLUS, Op.CONCAT)

    fun solvePart1(input: Sequence<String>): Long {
        return input.parseInput().sumOf { (expected, numbers) ->
            expected.takeIf {
                numbers.checkVariants(expected = expected, ops = OP1)
            } ?: 0
        }
    }

    fun solvePart2(input: Sequence<String>): Long {
        return input.parseInput().sumOf { (expected, numbers) ->
            expected.takeIf {
                numbers.checkVariants(expected = expected, ops = OP1) ||
                        numbers.checkVariants(expected = expected, ops = OP2)
            } ?: 0
        }
    }

    private fun List<Long>.checkVariants(pos: Int = 1, current: Long = first(), expected: Long, ops: Array<Op>): Boolean = when {
        pos == size && current == expected -> true
        current > expected || pos > lastIndex -> false
        else -> ops.any { checkVariants(pos + 1, it.block(current, get(pos)), expected, ops) }
    }

    enum class Op(val block: (l: Long, r: Long) -> Long) {
        PLUS({ l, r -> l + r }),
        MULT({ l, r -> l * r }),
        CONCAT({ l, r -> "$l$r".toLong() }),
    }

    private fun Sequence<String>.parseInput(): Sequence<Pair<Long, List<Long>>> {
        return map { line ->
            line.split(": ").let { (expected, numbers) ->
                expected.toLong() to numbers.split(" ").map { number -> number.toLong() }
            }
        }
    }
}