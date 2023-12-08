object Day8 {

    private val r = Regex("([0-9a-zA-Z]{3})")

    fun solvePart1(input: Sequence<String>): Int {
        val (instructions, steps) = parseInput(input)

        return countSteps(
            instructions = instructions,
            fromStep = "AAA",
            isLastStep = { it == "ZZZ" },
            steps = steps
        )
    }

    fun solvePart2(input: Sequence<String>): Long {
        val (instructions, steps) = parseInput(input)
        val startSteps = steps.keys.toList().filter { it.endsWith("A") }

        return startSteps
            .map { step ->
                countSteps(
                    instructions = instructions,
                    fromStep = step,
                    isLastStep = { it.endsWith("Z") },
                    steps = steps
                ).toLong()
            }
            .let(::findLCM)
    }

    private fun countSteps(
        instructions: String,
        fromStep: String,
        isLastStep: (String) -> Boolean,
        steps: Map<String, Pair<String, String>>
    ): Int {
        var opCount = 0
        var currentStep = fromStep

        while (!isLastStep.invoke(currentStep)) {
            when (instructions[opCount % instructions.length]) {
                'L' -> currentStep = steps.getValue(currentStep).first
                'R' -> currentStep = steps.getValue(currentStep).second
            }
            opCount += 1
        }

        return opCount
    }


    private fun parseInput(input: Sequence<String>): Pair<String, Map<String, Pair<String, String>>> {
        val list = input.toList()
        val instructions = list.first()
        val steps = mutableMapOf<String, Pair<String, String>>()

        for (i in 2 until list.size) {
            val (from, toLeft, toRight) = r.findAll(list[i]).map { it.value }.toList()
            steps[from] = toLeft to toRight
        }

        return instructions to steps
    }

    private fun findLCM(numbers: List<Long>): Long {
        var result = numbers[0]
        for (i in 1 until numbers.size) {
            result = findLCM(result, numbers[i])
        }
        return result
    }

    private fun findLCM(a: Long, b: Long): Long {
        val larger = if (a > b) a else b
        val maxLcm = a * b
        var lcm = larger
        while (lcm <= maxLcm) {
            if (lcm % a == 0L && lcm % b == 0L) {
                return lcm
            }
            lcm += larger
        }
        return maxLcm
    }
}