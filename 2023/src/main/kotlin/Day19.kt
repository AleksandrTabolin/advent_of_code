object Day19 {

    fun solvePart1(input: Sequence<String>): Int {
        return input.parseInput().getAcceptedRatings().sumOf { it.values.sum() }
    }

    fun solvePart2(input: Sequence<String>): Long {
        return input.parseInput().calculatePossibleDistinctCombinations()
    }

    private fun Input.getAcceptedRatings(): List<Map<String, Int>> =
        ratings.filter { it.isAccepted(startWorkflow, workflows) }

    private fun Input.calculatePossibleDistinctCombinations(): Long {
        val input = "xmas".associate { it.toString() to Interval(1, 4000) }
        return workflows.calculate(startWorkflow, input)
    }

    private fun Map<String, List<Op>>.calculate(
        workflowName: String,
        input: Map<String, Interval>
    ): Long {
        if (workflowName.isAccept()) {
            return input.values.fold(1L) { acc, c -> acc * (c.right - c.left + 1L) }
        }

        if (workflowName.isReject()) {
            return 0
        }

        val workflow = getValue(workflowName)

        val bag = input.toMutableMap()

        var count = 0L

        for (op in workflow) {
            when (op) {
                is Op.Compare -> {
                    val (accepted, rejected) = bag.getValue(op.rating).splitIntervals(op)

                    if (accepted.isCorrect()) {
                        bag[op.rating] = accepted
                        count += calculate(op.dist, bag)
                    }

                    if (rejected.isCorrect()) {
                        bag[op.rating] = rejected
                    } else {
                        break
                    }
                }

                is Op.Redirect -> {
                    count += calculate(op.dist, bag)
                }
            }
        }
        return count
    }

    private fun Map<String, Int>.isAccepted(startWorkflow: String, workflows: Map<String, List<Op>>): Boolean {
        var dist = startWorkflow

        while (true) {
            if (dist.isAccept()) return true
            if (dist.isReject()) return false

            val workflow = workflows.getValue(dist)

            for (op in workflow) {
                if (op is Op.Compare) {
                    if (op.check(this)) {
                        dist = op.dist
                        break
                    }
                } else {
                    dist = op.dist
                    break
                }
            }
        }
    }

    private fun Sequence<String>.parseInput(): Input {
        val workflows = mutableMapOf<String, List<Op>>()
        val ratings = mutableListOf<Map<String, Int>>()

        var parseWorkflows = true
        forEach { line ->
            if (line.isEmpty()) {
                parseWorkflows = false
            } else if (parseWorkflows) {
                val name = line.substring(0, line.indexOf('{'))
                val ops = line.substring(line.indexOf('{') + 1, line.lastIndexOf('}'))
                workflows[name] = ops.parseWorkflow()
            } else {
                ratings.add(line.parseRatings())
            }
        }

        return Input(
            startWorkflow = "in",
            workflows = workflows,
            ratings = ratings
        )
    }

    private fun String.parseWorkflow(): List<Op> {
        return split(',').fold(mutableListOf()) { acc, op ->
            if (op.contains(':')) {
                val (compare, dist) = op.split(':')
                if (compare.contains('<')) {
                    val (rate, value) = compare.split('<')
                    acc.add(Op.Compare(rate, '<', value.toLong(), dist))
                } else {
                    val (rate, value) = compare.split('>')
                    acc.add(Op.Compare(rate, '>', value.toLong(), dist))
                }
            } else {
                acc.add(Op.Redirect(op))
            }
            acc
        }
    }

    private fun String.parseRatings(): Map<String, Int> {
        val rating = substring(indexOf('{') + 1, lastIndexOf('}'))
        return rating.split(',').fold(mutableMapOf()) { acc, rate ->
            val (name, value) = rate.split('=')
            acc[name] = value.toInt()
            acc
        }
    }

    data class Input(
        val startWorkflow: String,
        val workflows: Map<String, List<Op>>,
        val ratings: List<Map<String, Int>>
    )

    sealed class Op {
        abstract val dist: String

        data class Compare(
            val rating: String,
            val op: Char,
            val threshold: Long,
            override val dist: String
        ) : Op()

        data class Redirect(override val dist: String) : Op()
    }

    data class Interval(val left: Long, val right: Long) {
        override fun toString(): String = "$left..$right"
    }

    fun Interval.isCorrect(): Boolean = left <= right

    private fun Interval.splitIntervals(op: Op.Compare): Pair<Interval, Interval> {
        return if (op.op == '>') {
            Interval(op.threshold + 1, right) to Interval(left, op.threshold)
        } else {
            Interval(left, op.threshold - 1) to Interval(op.threshold, right)
        }
    }

    private fun String.isAccept() = this == "A"

    private fun String.isReject() = this == "R"

    private fun Op.Compare.check(ratings: Map<String, Int>): Boolean {
        return when (op) {
            '>' -> ratings.getValue(rating) > threshold
            '<' -> ratings.getValue(rating) < threshold
            else -> false
        }
    }

}