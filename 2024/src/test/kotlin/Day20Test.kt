import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day20Test {

    private val input = """
        ###############
        #...#...#.....#
        #.#.#.#.#.###.#
        #S#...#.#.#...#
        #######.#.#.###
        #######.#.#...#
        #######.#.###.#
        ###..E#...#...#
        ###.#######.###
        #...###...#...#
        #.#####.#.###.#
        #.#...#.#.#...#
        #.#.#.#.#.#.###
        #...#...#...###
        ###############
    """.trimIndent()

    @Test
    fun test1p1() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(44, Day20.solve(input, 2, 2))
    }

    @Test
    fun test2p1() {
        val input = FileHelper.readByLines("day20")
        // 1436 too low
        Assertions.assertEquals(1463, Day20.solve(input, 2, 100))
    }

    @Test
    fun test1p2() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(285, Day20.solve(input, 20, 50))
    }

    @Test
    fun test2p2() {
        val input = FileHelper.readByLines("day20")
        Assertions.assertEquals(985332, Day20.solve(input, 20, 100))
    }
}