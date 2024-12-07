object Day7 {

    fun solvePart1(input: Sequence<String>): Long {
        return input.parseInput().sumOf { (expected, numbers) ->
            if (numbers.checkVariants1(1, numbers.first(), expected)) expected else 0
        }
    }

    fun solvePart2(input: Sequence<String>): Long {
        return input.parseInput().sumOf { (expected, numbers) ->
            if (numbers.checkVariants1(1, numbers.first(), expected)
                || numbers.checkVariants2(1, numbers.first(), expected)
            ) expected else 0
        }
    }

    private fun List<Long>.checkVariants1(pos: Int, current: Long, expected: Long): Boolean {
        if (pos == size && current == expected) return true
        if (current > expected || pos > lastIndex) return false
        return checkVariants1(pos + 1, current + get(pos), expected) ||
                checkVariants1(pos + 1, current * get(pos), expected)
    }

    private fun List<Long>.checkVariants2(pos: Int, current: Long, expected: Long): Boolean {
        if (pos == size && current == expected) return true
        if (current > expected || pos > lastIndex) return false
        return checkVariants2(pos + 1, current + get(pos), expected) ||
                checkVariants2(pos + 1, current * get(pos), expected) ||
                checkVariants2(pos + 1, "$current${get(pos)}".toLong(), expected)
    }

    private fun Sequence<String>.parseInput(): Sequence<Pair<Long, List<Long>>> {
        return map { line ->
            line.split(": ").let { (expected, numbers) ->
                expected.toLong() to numbers.split(" ").map { number -> number.toLong() }
            }
        }
    }
}