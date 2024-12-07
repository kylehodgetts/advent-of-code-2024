import java.io.File
import kotlin.math.abs

data class Report(val levels: List<Int>) {
    val safe: Boolean by lazy {
        isSafe(levels) || levels.indices.any { i -> isSafe(levels.toMutableList().also { it.removeAt(i) }) }
    }

    private fun isSafe(levels: List<Int>): Boolean {
        if (levels.size < 2) return true
        val increasing = levels[0] < levels[1]
        for (i in 1..<levels.size) {
            val diff = abs(levels[i - 1] - levels[i])
            if (diff == 0 || diff > 3) return false
            if (increasing && levels[i - 1] > levels[i]) return false
            if (!increasing && levels[i - 1] < levels[i]) return false
        }
        return true
    }
}

fun main() {
    // time code
    val start = System.currentTimeMillis()
    val safeReportsCount = File("input.txt").useLines { lines ->
        lines.map { line -> Report(line.split(" ").map { it.toInt() }) }.count { it.safe }
    }
    val end = System.currentTimeMillis()
    println("Time: ${end - start} ms")

    println(safeReportsCount)
}