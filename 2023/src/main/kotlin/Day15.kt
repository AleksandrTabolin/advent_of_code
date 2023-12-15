object Day15 {

    fun solvePart1(input: String): Int {
        return input.split(',').sumOf { it.calcHash() }
    }

    fun solvePart2(input: String): Int {
        return input.collectLenses().map { (number, box) ->
            box.values.mapIndexed { slot, focalLength -> ((number + 1) * (slot + 1) * focalLength) }.sum()
        }.sum()
    }

    private fun String.calcHash(): Int = fold(0) { acc, char -> (acc + char.code) * 17 % 256 }

    private fun String.collectLenses(): Map<Int, Map<String, Int>> {
        return split(',').fold(mutableMapOf<Int, MutableMap<String, Int>>()) { acc, text ->
            if (text.contains('=')) {
                val (code, size) = text.split('=')
                acc.getOrPut(code.calcHash()) { mutableMapOf() }[code] = size.toInt()
            } else if (text.last() == '-') {
                val code = text.dropLast(1)
                acc[code.calcHash()]?.remove(code)
            }
            acc
        }

    }
}