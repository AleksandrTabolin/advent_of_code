import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day18Test {

    private val input = """
        5,4
        4,2
        4,5
        3,0
        2,1
        6,3
        2,4
        1,5
        0,6
        3,3
        2,6
        5,1
        1,2
        5,5
        2,5
        6,5
        1,4
        0,4
        6,4
        1,1
        6,1
        1,0
        0,5
        1,6
        2,0
    """.trimIndent()

    @Test
    fun test1p1() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(22, Day18.solvePart1(input, 12,7))
    }

    @Test
    fun test2p1() {
        val input = FileHelper.readByLines("day18")
        Assertions.assertEquals(408, Day18.solvePart1(input, 1024, 71))
    }

    @Test
    fun test1p2() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(6 to 1, Day18.solvePart2(input, 12,7))
    }

    @Test
    fun test2p2() {
        val input = FileHelper.readByLines("day18")
        Assertions.assertEquals(45 to 46, Day18.solvePart2(input, 1024, 71))
    }
}