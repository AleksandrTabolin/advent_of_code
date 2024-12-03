object Day3 {

    fun solvePart1(input: Sequence<String>): Int {
        return "mul\\((\\d+),(\\d+)\\)"
            .toRegex()
            .findAll(input.joinToString())
            .map { result -> result.groupValues.let { it[1].toInt() * it[2].toInt() } }
            .sum()
    }

    fun solvePart2(input: Sequence<String>): Int {
        var enabled = true
        return "((do\\(\\))|(don't\\(\\))|(mul\\((\\d+),(\\d+)\\)))"
            .toRegex()
            .findAll(input.joinToString())
            .map { result ->
                var r = 0
                val action = result.groupValues.first()
                if (action.startsWith("don't")) {
                    enabled = false
                } else if (action.startsWith("do")) {
                    enabled = true
                } else if (action.startsWith("mul") && enabled) {
                    r = result.groupValues[5].toInt() * result.groupValues[6].toInt()
                }
                r
            }
            .sum()
    }
}