object Day1 {

    fun solvePart1(input: Sequence<String>): Int {
        return input.map { line ->
            val first = line.first { it.isDigit() }.toString()
            val last = line.last { it.isDigit() }.toString()
            (first + last).toInt()
        }.sum()
    }

    fun solvePart2(input: Sequence<String>): Int {
        val spelledDigits = mapOf(
            "1" to "1", "2" to "2", "3" to "3",
            "4" to "4", "5" to "5", "6" to "6",
            "7" to "7", "8" to "8", "9" to "9",
            "one" to "1", "two" to "2", "three" to "3",
            "four" to "4", "five" to "5", "six" to "6",
            "seven" to "7", "eight" to "8", "nine" to "9",
        )

        val digits = spelledDigits.keys.toList()
        return input.map { line ->
            val first = spelledDigits[line.findAnyOf(digits)!!.second]
            val last = spelledDigits[line.findLastAnyOf(digits)!!.second]
            (first + last).toInt()
        }.sum()
    }
}