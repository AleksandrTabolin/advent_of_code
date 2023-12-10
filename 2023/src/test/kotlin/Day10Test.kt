import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day10Test {

    private val input1 = """
        ..F7.
        .FJ|.
        SJ.L7
        |F--J
        LJ...
    """.trimIndent()

    private val input2 = """
        FF7FSF7F7F7F7F7F---7
        L|LJ||||||||||||F--J
        FL-7LJLJ||||||LJL-77
        F--JF--7||LJLJ7F7FJ-
        L---JF-JLJ.||-FJLJJ7
        |F|F-JF---7F7-L7L|7|
        |FFJF7L7F-JF7|JL---7
        7-L-JL7||F7|L7F-7F7|
        L.L7LFJ|||||FJL7||LJ
        L7JLJL-JLJLJL--JLJ.L
    """.trimIndent()

    @Test
    fun test1p1() {
        val input = input1.splitToSequence("\n")
        Assertions.assertEquals(8, Day10.solvePart1(input))
    }

    @Test
    fun test2p1() {
        val input = FileHelper.readByLines("day10")
        Assertions.assertEquals(6870, Day10.solvePart1(input))
    }

    @Test
    fun test1p2() {
        val input = input2.splitToSequence("\n")
        Assertions.assertEquals(10, Day10.solvePart2(input))
    }

    @Test
    fun test2p2() {
        val input = FileHelper.readByLines("day10")
        Assertions.assertEquals(287, Day10.solvePart2(input))
    }
}