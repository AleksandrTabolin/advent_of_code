import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day6Test {

    private val input = """
           Time:      7  15   30
           Distance:  9  40  200
    """.trimIndent()

    @Test
    fun test1p1() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(288, Day6.solvePart1(input))
    }

    @Test
    fun test2p1() {
        val input = FileHelper.readByLines("day6")
        Assertions.assertEquals(500346, Day6.solvePart1(input))
    }

    @Test
    fun test1p2() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(71503, Day6.solvePart2(input))
    }

    @Test
    fun test2p2() {
        val input = FileHelper.readByLines("day6")
        Assertions.assertEquals(42515755, Day6.solvePart2(input))
    }
}