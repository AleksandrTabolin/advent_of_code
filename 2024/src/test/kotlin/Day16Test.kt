import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day16Test {

    private val input1 = """
        ###############
        #.......#....E#
        #.#.###.#.###.#
        #.....#.#...#.#
        #.###.#####.#.#
        #.#.#.......#.#
        #.#.#####.###.#
        #...........#.#
        ###.#.#####.#.#
        #...#.....#.#.#
        #.#.#.###.#.#.#
        #.....#...#.#.#
        #.###.#.#.#.#.#
        #S..#.....#...#
        ###############        
    """.trimIndent()

    private val input2 = """
        #################
        #...#...#...#..E#
        #.#.#.#.#.#.#.#.#
        #.#.#.#...#...#.#
        #.#.#.#.###.#.#.#
        #...#.#.#.....#.#
        #.#.#.#.#.#####.#
        #.#...#.#.#.....#
        #.#.#####.#.###.#
        #.#.#.......#...#
        #.#.###.#####.###
        #.#.#...#.....#.#
        #.#.#.#####.###.#
        #.#.#.........#.#
        #.#.#.#########.#
        #S#.............#
        #################
    """.trimIndent()

    @Test
    fun test1p1() {
        val input = input1.splitToSequence("\n")
        Assertions.assertEquals(7036, Day16.solvePart1(input))
    }

    @Test
    fun test2p1() {
        val input = input2.splitToSequence("\n")
        Assertions.assertEquals(11048, Day16.solvePart1(input))
    }

    @Test
    fun test3p1() {
        val input = FileHelper.readByLines("day16")
        Assertions.assertEquals(134588, Day16.solvePart1(input))
    }

    @Test
    fun test1p2() {
        val input = input1.splitToSequence("\n")
        Assertions.assertEquals(45, Day16.solvePart2(input))
    }

    @Test
    fun test2p2() {
        val input = input2.splitToSequence("\n")
        Assertions.assertEquals(64, Day16.solvePart2(input))
    }

    @Test
    fun test3p2() {
        val input = FileHelper.readByLines("day16")
        Assertions.assertEquals(631, Day16.solvePart2(input))
    }
}