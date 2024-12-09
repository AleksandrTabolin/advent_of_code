import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day9Test {

    private val input = """
        2333133121414131402
    """.trimIndent()

    @Test
    fun test1p1() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(1928, Day9.solvePart1(input))
    }

    @Test
    fun test2p1() {
        val input = FileHelper.readByLines("day9")
        Assertions.assertEquals(6432869891895, Day9.solvePart1(input))
    }

    @Test
    fun test1p2() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(2858, Day9.solvePart2(input))
    }

    @Test
    fun test2p2() {
        val input = FileHelper.readByLines("day9")
        Assertions.assertEquals(6467290479134, Day9.solvePart2(input))
    }
}