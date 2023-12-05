object Day5 {

    fun solvePart1(input: Sequence<String>): Int {
        val puzzle = parsePuzzle(input)

        var currentTag = "seed"
        var seeds: List<Long> = puzzle.seeds

        while (currentTag != "location") {
            val mapper = puzzle.mappers.getValue(currentTag)
            currentTag = mapper.to

            seeds = seeds.map { mapper.ranges.mapToNext(it) }
        }

        return seeds.min().toInt()
    }

    fun solvePart2(input: Sequence<String>): Int {
        val puzzle = parsePuzzle(input)

        var currentTag = "seed"
        var seedRanges: List<Pair<Long, Long>> = puzzle.seeds.chunked(2).map { it.first() to it.last() }

        while (currentTag != "location") {
            val mapper = puzzle.mappers.getValue(currentTag)

            currentTag = mapper.to

            seedRanges = seedRanges.flatMap { mapper.ranges.mapToNext(it) }
        }

        return seedRanges.minBy { it.first }.first.toInt()
    }

    private fun parsePuzzle(input: Sequence<String>): Puzzle {
        var seeds = emptyList<Long>()

        var mapperFromTo: Pair<String, String>? = null
        var mapperRange: MutableList<Range>? = null

        val mappers = mutableMapOf<String, Mapper>()

        input.forEach { line ->
            if (line.startsWith("seeds: ")) {
                seeds = line.substringAfter("seeds: ").split(' ').map { it.toLong() }
            } else if (line.isEmpty()) {
                mapperFromTo?.let { (from, to) ->
                    mappers[from] = Mapper(to, requireNotNull(mapperRange))
                }
                mapperFromTo = null
                mapperRange = null
            } else if (line.endsWith(" map:")) {
                val (from, to) = line.substringBefore(" map:").split("-to-")
                mapperFromTo = from to to
                mapperRange = mutableListOf()
            } else if (mapperRange != null) {
                val (startDist, startSrc, length) = line.split(' ').map { it.toLong() }
                mapperRange?.add(Range(startDist, startSrc, length))
            }
        }

        mapperFromTo?.let { (from, to) ->
            mappers[from] = Mapper(to, requireNotNull(mapperRange))
        }

        return Puzzle(seeds, mappers)
    }


    private fun List<Range>.mapToNext(src: Long): Long {
        return firstOrNull { range -> src in range.startSrc until (range.startSrc + range.length) }
            ?.let { range -> range.startDist + (src - range.startSrc) }
            ?: src
    }

    private fun List<Range>.mapToNext(src: Pair<Long, Long>): List<Pair<Long, Long>> {
        val ranges = this
        val resultList = mutableListOf<Pair<Long, Long>>()

        var toCheck = listOf(src)
        for (range in ranges) {
            var newToCheck = mutableListOf<Pair<Long, Long>>()

            toCheck.forEach { inputRange ->
                val result = checkRanges(range, inputRange)
                if (result.remains.isNotEmpty()) {
                    newToCheck.addAll(result.remains)
                }
                result.overlap?.let {
                    resultList.add(it)
                }
            }
            toCheck = newToCheck

            if (toCheck.isEmpty()) {
                break
            }
        }

        if (toCheck.isNotEmpty()) {
            resultList.addAll(toCheck)
        }

        return resultList
    }

    private fun checkRanges(range: Range, src: Pair<Long, Long>): CheckResult {
        if (!range.checkOverlap(src)) {
            return CheckResult(remains = listOf(src))
        }

        val inputStart = src.first
        val inputEnd = src.first + src.second - 1
        val startInRange = range.startSrc <= inputStart && (range.startSrc + range.length) > inputStart
        var endInRange = range.startSrc <= inputEnd && (range.startSrc + range.length) > inputEnd

        return if (startInRange && endInRange) {
            CheckResult(
                overlap = range.startDist + (inputStart - range.startSrc) to src.second
            )
        } else if (!startInRange && endInRange) {
            CheckResult(
                remains = listOf(inputStart to range.startSrc - inputStart),
                overlap = range.startDist to src.second - (range.startSrc - inputStart)
            )
        } else if (startInRange && !endInRange) {
            CheckResult(
                remains = listOf(range.startSrc + range.length - 1 to (src.first + src.second) - (range.startSrc + range.length)),
                overlap = range.startDist + (inputStart - range.startSrc) to (range.startSrc + range.length) - src.first
            )
        } else {
            CheckResult(
                remains = listOf(
                    inputStart to range.startSrc - inputStart,
                    range.startSrc + range.length - 1 to (src.first + src.second) - (range.startSrc + range.length)
                ),
                overlap = range.startDist to range.length
            )
        }
    }

    private fun Range.checkOverlap(input: Pair<Long, Long>): Boolean {
        return startSrc < input.first + input.second && input.first < startSrc + length
    }

    private data class Puzzle(
        val seeds: List<Long>,
        val mappers: Map<String, Mapper>
    )

    private data class Mapper(
        val to: String,
        val ranges: List<Range>
    )

    private data class Range(
        val startDist: Long,
        val startSrc: Long,
        val length: Long
    )

    private data class CheckResult(
        val remains: List<Pair<Long, Long>> = emptyList(),
        val overlap: Pair<Long, Long>? = null
    )
}