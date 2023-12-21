import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day9Test {

    private val input = """
        0 3 6 9 12 15
        1 3 6 10 15 21
        10 13 16 21 30 45
    """.trimIndent()

    @Test
    fun test1p1() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(114, Day9.solvePart1(input))
    }

    @Test
    fun test2p1() {
        val input = FileHelper.readByLines("day9")
        Assertions.assertEquals(1702218515, Day9.solvePart1(input))
    }

    @Test
    fun test1p2() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(2, Day9.solvePart2(input))
    }

    @Test
    fun test2p2() {
        val input = FileHelper.readByLines("day9")
        Assertions.assertEquals(925, Day9.solvePart2(input))
    }
}