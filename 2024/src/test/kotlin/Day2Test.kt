import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.system.measureTimeMillis

class Day2Test {

    private val input = """
        7 6 4 2 1
        1 2 7 8 9
        9 7 6 2 1
        1 3 2 4 5
        8 6 4 4 1
        1 3 6 7 9
    """.trimIndent()

    @Test
    fun test1p1() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(2, Day2.solvePart1(input))
    }

    @Test
    fun test2p1() {
        val input = FileHelper.readByLines("day2")
        Assertions.assertEquals(407, Day2.solvePart1(input))
    }

    @Test
    fun test1p2() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(4, Day2.solvePart2(input))
    }

    @Test
    fun test2p2() {
        val input = FileHelper.readByLines("day2")
        Assertions.assertEquals(459, Day2.solvePart2(input))
    }
}