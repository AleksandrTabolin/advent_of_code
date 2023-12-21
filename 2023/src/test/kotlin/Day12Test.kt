import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import utils.FileHelper

class Day12Test {

    private val input = """
        ???.### 1,1,3
        .??..??...?##. 1,1,3
        ?#?#?#?#?#?#?#? 1,3,1,6
        ????.#...#... 4,1,1
        ????.######..#####. 1,6,5
        ?###???????? 3,2,1
    """.trimIndent()

    @Test
    fun test1p1() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(21L, Day12.solvePart1(input))
    }

    @Test
    fun test2p1() {
        val input = FileHelper.readByLines("day12")
        Assertions.assertEquals(7173L, Day12.solvePart1(input))
    }

    @Test
    fun test1p2() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(525152, Day12.solvePart2(input))
    }

    @Test
    fun test2p2() {
        val input = FileHelper.readByLines("day12")
        Assertions.assertEquals(29826669191291, Day12.solvePart2(input))
    }
}