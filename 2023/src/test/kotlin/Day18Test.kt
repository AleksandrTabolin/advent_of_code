import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import utils.FileHelper

class Day18Test {

    private val input = """
        R 6 (#70c710)
        D 5 (#0dc571)
        L 2 (#5713f0)
        D 2 (#d2c081)
        R 2 (#59c680)
        D 2 (#411b91)
        L 5 (#8ceee2)
        U 2 (#caa173)
        L 1 (#1b58a2)
        U 2 (#caa171)
        R 2 (#7807d2)
        U 3 (#a77fa3)
        L 2 (#015232)
        U 2 (#7a21e3)
    """.trimIndent()

    @Test
    fun test1p1() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(62L, Day18.solvePart1(input))
    }

    @Test
    fun test2p1() {
        val input = FileHelper.readByLines("day18")
        Assertions.assertEquals(106459L, Day18.solvePart1(input))
    }

    @Test
    fun test1p2() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(952408144115L, Day18.solvePart2(input))
    }

    @Test
    fun test2p2() {
        val input = FileHelper.readByLines("day18")
        Assertions.assertEquals(63806916814808L, Day18.solvePart2(input))
    }
}