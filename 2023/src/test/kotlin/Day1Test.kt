import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day1Test {

    private val inputP1 = """
        1abc2
        pqr3stu8vwx
        a1b2c3d4e5f
        treb7uchet
    """.trimIndent()

    private val inputP2 = """
        two1nine
        eightwothree
        abcone2threexyz
        xtwone3four
        4nineeightseven2
        zoneight234
        7pqrstsixteen
    """.trimIndent()

    @Test
    fun test1p1() {
        val input = inputP1.splitToSequence("\n")
        Assertions.assertEquals(142, Day1.solvePart1(input))
    }

    @Test
    fun test2p1() {
        val input = FileHelper.readByLines("day1")
        Assertions.assertEquals(54667, Day1.solvePart1(input))
    }

    @Test
    fun test1p2() {
        val input = inputP2.splitToSequence("\n")
        Assertions.assertEquals(281, Day1.solvePart2(input))
    }

    @Test
    fun test2p2() {
        val input = FileHelper.readByLines("day1")
        Assertions.assertEquals(54203, Day1.solvePart2(input))
    }
}