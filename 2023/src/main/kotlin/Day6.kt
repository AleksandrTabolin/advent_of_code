object Day6 {

    private val regex = Regex("\\s+")

    fun solvePart1(input: Sequence<String>): Int {
        return parseRaces1(input)
            .map(::process)
            .reduce { acc, element -> acc * element }
    }

    fun solvePart2(input: Sequence<String>): Int {
        return parseRaces2(input).let(::process)
    }

    private fun process(race: Pair<Long, Long>): Int {
        val (time, distance) = race
        return (1..time).count { i -> (time - i) * i > distance }
    }

    private fun parseRaces1(input: Sequence<String>): List<Pair<Long, Long>> {
        val inputList = input.toList()
        val time = inputList.first().substringAfter("Time:").trimStart().split(regex).map { it.toLong() }
        val distance = inputList.last().substringAfter("Distance:").trimStart().split(regex).map { it.toLong() }
        return time.zip(distance)
    }

    private fun parseRaces2(input: Sequence<String>): Pair<Long, Long> {
        val inputList = input.toList()
        val time = inputList.first().substringAfter("Time:").replace(" ", "").toLong()
        val distance = inputList.last().substringAfter("Distance:").replace(" ", "").toLong()
        return time to distance
    }

}