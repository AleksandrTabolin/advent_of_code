import utils.pow
import utils.takeDigits
import utils.takeIntList

object Day17 {

    fun solvePart1(input: Sequence<String>): String {
        val (regs, program) = input.parseInput()
        return compute(regs, program).joinToString(",")
    }

    fun solvePart2(input: Sequence<String>): Long {
        val (_, program) = input.parseInput()
        val result = mutableListOf<Long>()
        val outOperand = program.chunked(2).first { it.first() == 5 }.last()
        search(program, outOperand, program.lastIndex, 0, result)
        return result.min()
    }

    fun compute(regs: Registers, program: List<Int>, forceEnd: Boolean = false): List<Int> {
        var instructionPointer = 0
        val out = mutableListOf<Int>()

        while (instructionPointer in program.indices) {
            val op = program[instructionPointer]
            val operand = program[instructionPointer + 1]

            var opJump = 2
            when (op) {
                0 -> regs.a /= 2L.pow(regs.comboOperand(operand))
                1 -> regs.b = regs.b xor operand.toLong()
                2 -> regs.b = regs.comboOperand(operand) % 8
                3 -> if (regs.a != 0L && !forceEnd) {
                    instructionPointer = operand
                    opJump = 0
                }
                4 -> regs.b = regs.b xor regs.c
                5 -> out.add(regs.comboOperand(operand).toInt() % 8)
                6 -> regs.b = regs.a / 2L.pow(regs.comboOperand(operand))
                7 -> regs.c = regs.a / 2L.pow(regs.comboOperand(operand))
            }
            instructionPointer += opJump
        }
        return out
    }

    private fun search(program: List<Int>, outOperand: Int, num: Int, end: Long, result: MutableList<Long>) {
        for (a in end * 8L..< (end + 1) * 8) {
            val reg = Registers(a = a)
            compute(reg, program, forceEnd = true)
            if ((reg.comboOperand(outOperand) % 8).toInt() == program[num]) {
                if (num == 0) result.add(a) else search(program, outOperand,num - 1, a, result)
            }
        }
    }

    private fun Registers.comboOperand(operand: Int): Long {
        return when (operand) {
            0, 1, 2, 3 -> operand.toLong()
            4 -> a
            5 -> b
            6 -> c
            else -> throw IllegalStateException("Should not appear in valid programs: $operand")
        }
    }

    private fun Sequence<String>.parseInput(): Pair<Registers, List<Int>> {
        var a = 0
        var b = 0
        var c = 0
        var program = listOf<Int>()
        forEach { line ->
            when {
                line.startsWith("Register A:") -> a = line.takeDigits()
                line.startsWith("Register B:") -> b = line.takeDigits()
                line.startsWith("Register C:") -> c = line.takeDigits()
                line.startsWith("Program:") -> program = line.takeIntList()
            }
        }
        return Registers(a = a.toLong(), b = b.toLong(), c = c.toLong()) to program
    }

    data class Registers(
        var a: Long = 0,
        var b: Long = 0,
        var c: Long = 0,
    )


}