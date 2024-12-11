package utils

fun findLCM(numbers: List<Long>): Long {
    var result = numbers[0]
    for (i in 1 until numbers.size) {
        result = findLCM(result, numbers[i])
    }
    return result
}

private fun findLCM(a: Long, b: Long): Long {
    val larger = if (a > b) a else b
    val maxLcm = a * b
    var lcm = larger
    while (lcm <= maxLcm) {
        if (lcm % a == 0L && lcm % b == 0L) {
            return lcm
        }
        lcm += larger
    }
    return maxLcm
}

fun Long.length(): Int {
    var value = this
    var size = 0
    while (value > 0) {
        value /= 10
        size += 1
    }
    return size
}

fun Long.pow(x: Int): Long = (2..x).fold(this) { r, _ -> r * this }

fun Int.length(): Int {
    var value = this
    var size = 0
    while (value > 0) {
        value /= 10
        size += 1
    }
    return size
}

fun Int.pow(x: Int): Int = (2..x).fold(this) { r, _ -> r * this }

fun Long.splitInHalf(): Pair<Long, Long> {
    val m = 10L.pow(length() / 2)
    return this / m to this % m
}

fun Int.splitInHalf(): Pair<Int, Int> {
    val m = 10.pow(length() / 2)
    return this / m to this % m
}
