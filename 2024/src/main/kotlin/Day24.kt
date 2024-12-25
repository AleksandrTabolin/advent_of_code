import utils.longLength
import utils.takeDigitsToInt

object Day24 {

    fun solvePart1(input: Sequence<String>): Long {
        val (operands, ops) = input.parseInput()
        val queue = ArrayDeque(ops)
        while (queue.isNotEmpty()) {
            val op = queue.removeFirst()
            if (!op.invoke(operands)) {
                queue.addLast(op)
            }
        }
        return operands.getNumber("z")
    }

    fun solvePart2(input: Sequence<String>): String {
        val (operands, ops) = input.parseInput()

        val replace = replace(
            "bjm" to "z07",
            "z13" to "hsw",
            "z18" to "skf",
            "nvr" to "wkr"
        )
        val x = operands.getNumber("x") // 33990941402531
        val y = operands.getNumber("y") // 22738689785549
        val z = x + y                         // 56729631188080
        val expectedZSize = z.toString(2).length
        println("$x + $y = $z")
        val actualZ = operands.invokeSteps(ops, expectedZSize, z, replace)
        println("expected: $z, actual: $actualZ")
        return replace.keys.sorted().joinToString(",")
    }

    private fun Map<String, Int>.invokeSteps(
        ops: List<Op>,
        steps: Int,
        expectedZ: Long,
        replace: Map<String, String>,
    ): Long {
        var currStep = 0
        val currOperands = mutableMapOf<String, Int>()
        val invokedOps = mutableSetOf<Op>()

        while (currStep < steps) {
            currOperands.putAll(filter { it.key.isInputOperand(currStep) })
            println("#-----$currStep")
            while(true) {
                var count = 0
                for (op in ops) {
                    if (op !in invokedOps && op.invoke(currOperands, replace)) {
                        invokedOps.add(op)
                        count +=1
                    }
                }
                if (count == 0) break
            }
            val actualZValue = currOperands.asSequence().first { it.key.isOutputOperand(currStep) }.value
            val expectedZValue = (expectedZ shr currStep) and 1L
            println("#z$currStep: $actualZValue $expectedZValue")
            if (actualZValue != expectedZValue.toInt()) {
                throw IllegalStateException("Wrong")
            }
            currStep += 1
        }
        return currOperands.getNumber("z")
    }

    private fun String.isInputOperand(pos: Int) = (startsWith("x") || startsWith("y")) && takeDigitsToInt() == pos

    private fun String.isOutputOperand(pos: Int) = startsWith("z") && takeDigitsToInt() == pos

    private fun Map<String, Int>.getNumber(name: String): Long {
        return entries
            .asSequence()
            .filter { it.key.startsWith(name) }
            .sortedByDescending { it.key }
            .fold("") { acc, item ->
                acc + item.value
            }.toLong(2)
    }

    private fun Op.invoke(operands: MutableMap<String, Int>, replace: Map<String, String> = emptyMap()): Boolean {
        val op1Value = operands[operand1]
        val op2Value = operands[operand2]
        if (op1Value == null || op2Value == null) return false
        operands[replace[result] ?: result] = when (op) {
            "AND" -> op1Value.and(op2Value)
            "OR" -> op1Value.or(op2Value)
            "XOR" -> op1Value.xor(op2Value)
            else -> throw IllegalStateException("Unknown op: $op")
        }.also {
            println("$this: [$op1Value $op $op2Value -> $it]")
        }
        return true
    }

    private fun replace(vararg pairs: Pair<String, String>): Map<String, String> {
        return pairs.fold(mutableMapOf()) { acc, item ->
            acc[item.first] = item.second
            acc[item.second] = item.first
            acc
        }
    }

    private fun Sequence<String>.parseInput(): Pair<MutableMap<String, Int>, List<Op>> {
        var collectOps = false
        val operands = mutableMapOf<String, Int>()
        val ops = mutableListOf<Op>()
        forEach { line ->
            if (line.isBlank()) collectOps = true
            else if (collectOps) ops.add(line.parseOp())
            else line.split(": ").let { operands[it.first()] = it.last().toInt() }
        }
        return operands to ops
    }

    private fun String.parseOp(): Op {
        return split(" -> ").let { (left, result) ->
            val (operand1, op, operand2) = split(" ")
            Op(operand1, operand2, op, result)
        }
    }

    private data class Op(
        val operand1: String,
        val operand2: String,
        val op: String,
        val result: String
    ) {
        override fun toString() = "$operand1 $op $operand2 -> $result"
    }
}