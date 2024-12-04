import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day4Test {

    private val input = """
        MMMSXXMASM
        MSAMXMSMSA
        AMXSXMAAMM
        MSAMASMSMX
        XMASAMXAMM
        XXAMMXXAMA
        SMSMSASXSS
        SAXAMASAAA
        MAMMMXMMMM
        MXMXAXMASX
    """.trimIndent()

    @Test
    fun test1p1() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(18, Day4.solvePart1(input))
    }

    @Test
    fun test2p1() {
        val input = FileHelper.readByLines("day4")
        Assertions.assertEquals(2603, Day4.solvePart1(input))
    }

    @Test
    fun test1p2() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(9, Day4.solvePart2(input))
    }

    @Test
    fun test2p2() {
        val input = FileHelper.readByLines("day4")
        Assertions.assertEquals(1965, Day4.solvePart2(input))
    }
}