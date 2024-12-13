import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day13Test {

    private val input = """
        Button A: X+94, Y+34
        Button B: X+22, Y+67
        Prize: X=8400, Y=5400

        Button A: X+26, Y+66
        Button B: X+67, Y+21
        Prize: X=12748, Y=12176

        Button A: X+17, Y+86
        Button B: X+84, Y+37
        Prize: X=7870, Y=6450

        Button A: X+69, Y+23
        Button B: X+27, Y+71
        Prize: X=18641, Y=10279
    """.trimIndent()

    @Test
    fun test1p1() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(480, Day13.solvePart1(input))
    }

    @Test
    fun test2p1() {
        val input = FileHelper.readByLines("day13")
        Assertions.assertEquals(31065, Day13.solvePart1(input))
    }

    @Test
    fun test1p2() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(875318608908, Day13.solvePart2(input))
    }

    @Test
    fun test2p2() {
        val input = FileHelper.readByLines("day13")
        Assertions.assertEquals(93866170395343, Day13.solvePart2(input))
    }
}