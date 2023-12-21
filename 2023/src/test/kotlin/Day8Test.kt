import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import utils.FileHelper

class Day8Test {

    private val input1 = """
        RL

        AAA = (BBB, CCC)
        BBB = (DDD, EEE)
        CCC = (ZZZ, GGG)
        DDD = (DDD, DDD)
        EEE = (EEE, EEE)
        GGG = (GGG, GGG)
        ZZZ = (ZZZ, ZZZ)
    """.trimIndent()

    private val input2 = """
        LR
    
        11A = (11B, XXX)
        11B = (XXX, 11Z)
        11Z = (11B, XXX)
        22A = (22B, XXX)
        22B = (22C, 22C)
        22C = (22Z, 22Z)
        22Z = (22B, 22B)
        XXX = (XXX, XXX)
    """.trimIndent()

    @Test
    fun test1p1() {
        val input = input1.splitToSequence("\n")
        Assertions.assertEquals(2, Day8.solvePart1(input))
    }

    @Test
    fun test2p1() {
        val input = FileHelper.readByLines("day8")
        Assertions.assertEquals(12083, Day8.solvePart1(input))
    }

    @Test
    fun test1p2() {
        val input = input2.splitToSequence("\n")
        Assertions.assertEquals(6, Day8.solvePart2(input))
    }

    @Test
    fun test2p2() {
        val input = FileHelper.readByLines("day8")
        Assertions.assertEquals(13385272668829, Day8.solvePart2(input))
    }
}