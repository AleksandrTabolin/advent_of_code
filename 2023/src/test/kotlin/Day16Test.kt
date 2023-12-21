import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import utils.FileHelper

class Day16Test {

    private val input = """
        .|...\....
        |.-.\.....
        .....|-...
        ........|.
        ..........
        .........\
        ..../.\\..
        .-.-/..|..
        .|....-|.\
        ..//.|....
    """.trimIndent()

    @Test
    fun test1p1() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(46, Day16.solvePart1(input))
    }

    @Test
    fun test2p1() {
        val input = FileHelper.readByLines("day16")
        Assertions.assertEquals(7884, Day16.solvePart1(input))
    }

    @Test
    fun test1p2() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(51, Day16.solvePart2(input))
    }

    @Test
    fun test2p2() {
        val input = FileHelper.readByLines("day16")
        Assertions.assertEquals(8185, Day16.solvePart2(input))
    }
}