import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day7Test {

    private val input = """
        190: 10 19
        3267: 81 40 27
        83: 17 5
        156: 15 6
        7290: 6 8 6 15
        161011: 16 10 13
        192: 17 8 14
        21037: 9 7 18 13
        292: 11 6 16 20
    """.trimIndent()

    @Test
    fun test1p1() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(3749, Day7.solvePart1(input))
    }

    @Test
    fun test2p1() {
        val input = FileHelper.readByLines("day7")
        Assertions.assertEquals(2437272016585, Day7.solvePart1(input))
    }

    @Test
    fun test1p2() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(11387, Day7.solvePart2(input))
    }

    @Test
    fun test2p2() {
        val input = FileHelper.readByLines("day7")
        Assertions.assertEquals(162987117690649, Day7.solvePart2(input))
    }
}