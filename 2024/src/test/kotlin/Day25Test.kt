import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day25Test {

    private val input = """
        #####
        .####
        .####
        .####
        .#.#.
        .#...
        .....

        #####
        ##.##
        .#.##
        ...##
        ...#.
        ...#.
        .....

        .....
        #....
        #....
        #...#
        #.#.#
        #.###
        #####

        .....
        .....
        #.#..
        ###..
        ###.#
        ###.#
        #####

        .....
        .....
        .....
        #....
        #.#..
        #.#.#
        #####
    """.trimIndent()

    @Test
    fun test1p1() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(3, Day25.solvePart1(input))
    }

    @Test
    fun test2p1() {
        val input = FileHelper.readByLines("day25")
        Assertions.assertEquals(3264, Day25.solvePart1(input))
    }

    @Test
    fun test1p2() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(-1, Day25.solvePart2(input))
    }

    @Test
    fun test2p2() {
        val input = FileHelper.readByLines("day25")
        Assertions.assertEquals(-1, Day25.solvePart2(input))
    }
}