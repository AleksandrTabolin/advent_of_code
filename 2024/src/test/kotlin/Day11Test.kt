import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day11Test {

    private val input = """
        125 17
    """.trimIndent()

    @Test
    fun test1p1() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(55312, Day11.solvePart1(input))
    }

    @Test
    fun test2p1() {
        val input = FileHelper.readByLines("day11")
        Assertions.assertEquals(224529, Day11.solvePart1(input))
    }

    @Test
    fun test2p2() {
        val input = FileHelper.readByLines("day11")
        Assertions.assertEquals(266820198587914, Day11.solvePart2(input))
    }
}