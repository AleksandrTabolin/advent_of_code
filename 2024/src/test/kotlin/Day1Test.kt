import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day1Test {

    private val inputP1 = """
        3   4
        4   3
        2   5
        1   3
        3   9
        3   3
    """.trimIndent()

    @Test
    fun test1p1() {
        val input = inputP1.splitToSequence("\n")
        Assertions.assertEquals(11, Day1.solvePart1(input))
    }

    @Test
    fun test2p1() {
        val input = FileHelper.readByLines("day1")
        Assertions.assertEquals(1765812, Day1.solvePart1(input))
    }

    @Test
    fun test1p2() {
        val input = inputP1.splitToSequence("\n")
        Assertions.assertEquals(31, Day1.solvePart2(input))
    }

    @Test
    fun test2p2() {
        val input = FileHelper.readByLines("day1")
        Assertions.assertEquals(20520794, Day1.solvePart2(input))
    }
}