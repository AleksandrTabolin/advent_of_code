import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day14Test {

    private val input = """
        p=0,4 v=3,-3
        p=6,3 v=-1,-3
        p=10,3 v=-1,2
        p=2,0 v=2,-1
        p=0,0 v=1,3
        p=3,0 v=-2,-2
        p=7,6 v=-1,-3
        p=3,0 v=-1,-2
        p=9,3 v=2,3
        p=7,3 v=-1,2
        p=2,4 v=2,-3
        p=9,5 v=-3,-3
    """.trimIndent()

    @Test
    fun test1p1() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(12, Day14.solvePart1(input, 11, 7))
    }

    @Test
    fun test2p1() {
        val input = FileHelper.readByLines("day14")
        Assertions.assertEquals(231782040, Day14.solvePart1(input, 101, 103))
    }

    @Test
    fun test2p2() {
        val input = FileHelper.readByLines("day14")
        Assertions.assertEquals(6475, Day14.solvePart2(input, 101, 103))
    }
}