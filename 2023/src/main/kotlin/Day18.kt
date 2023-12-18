import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

object Day18 {

    fun solvePart1(input: Sequence<String>): Long {
        return input.map {
            val (direction, length, _) = it.split(" ")
            direction to length.toLong()
        }.collectIntervals().count()
    }

    fun solvePart2(input: Sequence<String>): Long {
        return input.map {
            val (_, _, color) = it.split(" ")
            val clearedColor = color.drop(2).dropLast(1)
            val direction = when (clearedColor.last()) {
                '0' -> "R"
                '1' -> "D"
                '2' -> "L"
                '3' -> "U"
                else -> throw IllegalStateException("Unknown direction '${clearedColor.last()}'")
            }
            val length = clearedColor.dropLast(1).toLong(16)

            direction to length
        }.collectIntervals().count()
    }

    private fun Field.count(): Long {
        var resultCount = 0L
        var prevRowIntervals: List<СontinuousInterval>? = null

        for (row in top..down) {
            var rowCount = 0L
            val rowIntervals = getRowIntervals(row)

            for (x in rowIntervals.indices) {
                rowCount += rowIntervals[x].horizontalLength

                if (x == rowIntervals.lastIndex) continue

                var count = 0L
                for (j in x + 1..rowIntervals.lastIndex) {
                    count += if (isPillarShaped(row, rowIntervals[j], prevRowIntervals)) 2L else 1L
                }

                if (count % 2 == 1L) {
                    rowCount += (rowIntervals[x].rightX + 1 to rowIntervals[x + 1].leftX - 1).length()
                }
            }

            prevRowIntervals = rowIntervals
            resultCount += rowCount
        }

        return resultCount
    }

    private fun Field.isPillarShaped(
        row: Long,
        interval: СontinuousInterval,
        prevRowIntervals: List<СontinuousInterval>?
    ): Boolean {
        if (interval.horizontalLength > 1L && (row == top || row == down)) return true

        if (interval.horizontalLength == 1L) return false

        prevRowIntervals ?: return true

        val counter = prevRowIntervals.count {
            (interval.leftX == it.leftX || interval.leftX == it.rightX || interval.rightX == it.leftX || interval.rightX == it.rightX) && it.downY == row - 1
        }

        return if (interval.downY > row) {
            counter == 0
        } else if (interval.topY < row) {
            counter == 1
        } else {
            counter == 2 || counter == 0
        }
    }

    private fun Sequence<Pair<String, Long>>.collectIntervals(): Field {
        var intervals: List<Interval> = fold(mutableListOf()) { acc, line ->
            val (direction, length) = line
            val fixedLength = length - 1

            var firstPosition = acc.lastOrNull()?.last ?: Position(0, 0)
            var lastPosition = firstPosition
            when (direction) {
                "R" -> {
                    firstPosition = firstPosition.copy(x = firstPosition.x + 1)
                    lastPosition = firstPosition.copy(x = firstPosition.x + fixedLength)
                }

                "U" -> {
                    firstPosition = firstPosition.copy(y = firstPosition.y - 1)
                    lastPosition = firstPosition.copy(y = firstPosition.y - fixedLength)
                }

                "L" -> {
                    firstPosition = firstPosition.copy(x = firstPosition.x - 1)
                    lastPosition = firstPosition.copy(x = firstPosition.x - fixedLength)
                }

                "D" -> {
                    firstPosition = firstPosition.copy(y = firstPosition.y + 1)
                    lastPosition = firstPosition.copy(y = firstPosition.y + fixedLength)
                }
            }
            acc.add(Interval(firstPosition, lastPosition))
            acc
        }

        var minX = Long.MAX_VALUE
        var minY = Long.MAX_VALUE
        var maxX = Long.MIN_VALUE
        var maxY = Long.MIN_VALUE

        intervals.forEach {
            minX = min(minX, min(it.first.x, it.last.x))
            minY = min(minY, min(it.first.y, it.last.y))
            maxX = max(maxX, max(it.first.x, it.last.x))
            maxY = max(maxY, max(it.first.y, it.last.y))
        }

        val diffX = abs(minX)
        val diffY = abs(minY)

        intervals = intervals.map { interval ->
            Interval(
                first = Position(interval.first.x + diffX, interval.first.y + diffY),
                last = Position(interval.last.x + diffX, interval.last.y + diffY),
            )
        }.sortedBy { it.leftX }

        return Field(
            left = 0,
            top = 0,
            right = maxX + diffX,
            down = maxY + diffY,
            intervals = intervals
        )
    }

    private data class Field(
        val left: Long,
        val top: Long,
        val right: Long,
        val down: Long,
        val intervals: List<Interval>
    )

    private fun Field.getRowIntervals(row: Long): List<СontinuousInterval> {
        val rowIntervals = intervals.filter { row >= it.topY && row <= it.downY }
        val result = mutableListOf<СontinuousInterval>()

        var prevInterval: Interval? = rowIntervals.firstOrNull()

        for (i in 1..rowIntervals.lastIndex) {
            val interval = rowIntervals[i]

            if (prevInterval != null && prevInterval.rightX + 1 == interval.leftX) {
                result.add(СontinuousInterval(prevInterval, interval))
                prevInterval = null
            } else if (prevInterval != null) {
                result.add(СontinuousInterval(prevInterval))
                prevInterval = interval
            } else {
                prevInterval = interval
            }
        }

        if (prevInterval != null) {
            result.add(СontinuousInterval(prevInterval))
        }
        return result
    }

    private data class СontinuousInterval(
        val first: Interval,
        val last: Interval? = null
    ) {
        val leftX: Long by lazy {
            if (last == null) {
                min(first.first.x, first.last.x)
            } else {
                min(min(first.first.x, first.last.x), min(last.first.x, last.last.x))
            }
        }

        val rightX: Long by lazy {
            if (last == null) {
                max(first.first.x, first.last.x)
            } else {
                max(max(first.first.x, first.last.x), max(last.first.x, last.last.x))
            }
        }

        val topY: Long by lazy {
            if (last == null) {
                min(first.first.y, first.last.y)
            } else {
                min(min(first.first.y, first.last.y), min(last.first.y, last.last.y))
            }
        }

        val downY: Long by lazy {
            if (last == null) {
                max(first.first.y, first.last.y)
            } else {
                max(max(first.first.y, first.last.y), max(last.first.y, last.last.y))
            }
        }

        val horizontalLength: Long by lazy { rightX - leftX + 1 }
    }

    private data class Interval(
        val first: Position,
        val last: Position,
    ) {
        val topY = min(first.y, last.y)
        val downY = max(first.y, last.y)
        val leftX = min(first.x, last.x)
        val rightX = max(first.x, last.x)
    }

    private data class Position(
        val x: Long,
        val y: Long,
    )

    private fun Pair<Long, Long>.length() = second - first + 1
}