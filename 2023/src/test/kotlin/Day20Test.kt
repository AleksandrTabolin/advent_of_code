import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import utils.FileHelper

class Day20Test {

    private val input1 = """
        broadcaster -> a, b, c
        %a -> b
        %b -> c
        %c -> inv
        &inv -> a
    """.trimIndent()

    private val input2 = """
        broadcaster -> a
        %a -> inv, con
        &inv -> b
        %b -> con
        &con -> output
    """.trimIndent()

    @Test
    fun test1p1() {
        val input = input1.splitToSequence("\n")
        Assertions.assertEquals(32000000L, Day20.solvePart1(input))
    }

    @Test
    fun test2p1() {
        val input = input2.splitToSequence("\n")
        Assertions.assertEquals(11687500L, Day20.solvePart1(input))
    }

    @Test
    fun test3p1() {
        val input = FileHelper.readByLines("day20")
        Assertions.assertEquals(912199500, Day20.solvePart1(input))
    }

    @Test
    fun test1p2() {
        val input = FileHelper.readByLines("day20")
        Assertions.assertEquals(237878264003759, Day20.solvePart2(input))
    }

}