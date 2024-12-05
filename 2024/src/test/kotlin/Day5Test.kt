import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day5Test {

    private val input = """
        47|53
        97|13
        97|61
        97|47
        75|29
        61|13
        75|53
        29|13
        97|29
        53|29
        61|53
        97|53
        61|29
        47|13
        75|47
        97|75
        47|61
        75|61
        47|29
        75|13
        53|13

        75,47,61,53,29
        97,61,53,29,13
        75,29,13
        75,97,47,61,53
        61,13,29
        97,13,75,29,47
    """.trimIndent()

    @Test
    fun test1p1() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(143, Day5.solvePart1(input))
    }

    @Test
    fun test2p1() {
        val input = FileHelper.readByLines("day5")
        Assertions.assertEquals(5391, Day5.solvePart1(input))
    }

    @Test
    fun test1p2() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(123, Day5.solvePart2(input))
    }

    @Test
    fun test2p2() {
        val input = FileHelper.readByLines("day5")
        Assertions.assertEquals(6142, Day5.solvePart2(input))
    }
}