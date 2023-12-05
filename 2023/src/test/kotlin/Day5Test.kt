import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day5Test {

    private val input = """
           seeds: 79 14 55 13

           seed-to-soil map:
           50 98 2
           52 50 48

           soil-to-fertilizer map:
           0 15 37
           37 52 2
           39 0 15

           fertilizer-to-water map:
           49 53 8
           0 11 42
           42 0 7
           57 7 4

           water-to-light map:
           88 18 7
           18 25 70

           light-to-temperature map:
           45 77 23
           81 45 19
           68 64 13

           temperature-to-humidity map:
           0 69 1
           1 0 69

           humidity-to-location map:
           60 56 37
           56 93 4
    """.trimIndent()

    @Test
    fun test1p1() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(35, Day5.solvePart1(input))
    }

    @Test
    fun test2p1() {
        val input = FileHelper.readByLines("day5")
        Assertions.assertEquals(278755257, Day5.solvePart1(input))
    }

    @Test
    fun test1p2() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(46, Day5.solvePart2(input))
    }

    @Test
    fun test2p2() {
        val input = FileHelper.readByLines("day5")
        Assertions.assertEquals(26829166, Day5.solvePart2(input))
    }
}