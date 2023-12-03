object Day3 {

    private val checkPositionOffsets = listOf(
        -1 to 1, -1 to 0, -1 to -1,
        1 to 0, 1 to 1, 1 to -1,
        0 to 1, 0 to -1
    )

    fun solvePart1(input: Sequence<String>): Int {
        return input.toList()
            .findNumbersAdjacentToSymbol { !it.isDigit() && it != '.' }
            .sumOf { (number, _) -> number }
    }

    fun solvePart2(input: Sequence<String>): Int {
        return input.toList()
            .findNumbersAdjacentToSymbol { it == '*' }
            .groupBy(
                keySelector = { (_, symbols) ->
                    val (_, i, j) = symbols.first()
                    i to j
                },
                valueTransform = { (number, _) -> number }
            )
            .filter { it.value.size == 2 }
            .values.sumOf { it.first() * it.last() }
    }

    private fun List<String>.findNumbersAdjacentToSymbol(
        isAdjacentToSymbol: (Char) -> Boolean,
    ): Sequence<Pair<Int, List<Triple<Char, Int, Int>>>> = sequence {
        val allInput = this@findNumbersAdjacentToSymbol

        var acc = ""
        var isAccAdjacentToSymbol = false
        var accSymbols = mutableListOf<Triple<Char, Int, Int>>()

        for (i in allInput.indices) {
            val line = allInput[i]
            for (j in line.indices) {
                if (line[j].isDigit()) {
                    acc += line[j]
                    isAccAdjacentToSymbol = isAccAdjacentToSymbol || allInput.isAdjacentToSymbol(i, j) { char, i, j ->
                        val result = isAdjacentToSymbol.invoke(char)
                        if (result) {
                            accSymbols.add(Triple(char, i, j))
                        }
                        result
                    }
                } else {
                    if (isAccAdjacentToSymbol) {
                        yield(acc.toInt() to accSymbols)
                    }
                    acc = ""
                    isAccAdjacentToSymbol = false
                    accSymbols.clear()
                }
            }

            if (acc.isNotEmpty() && isAccAdjacentToSymbol) {
                yield(acc.toInt() to accSymbols)
            }
            acc = ""
            isAccAdjacentToSymbol = false
            accSymbols.clear()
        }
    }

    private fun List<String>.isAdjacentToSymbol(i: Int, j: Int, checkSymbol: (Char, Int, Int) -> Boolean): Boolean {
        var result = false
        checkPositionOffsets.forEach { (di, dj) ->
            val char = getCharAt(i + di, j + dj)

            if (char != null && checkSymbol.invoke(char, i + di, j + dj)) {
                result = true
            }
        }

        return result
    }

    private fun List<String>.getCharAt(i: Int, j: Int): Char? {
        if (i < 0 || i >= size) return null
        val row = get(i)
        if (j < 0 || j >= row.length) return null
        return row[j]
    }
}