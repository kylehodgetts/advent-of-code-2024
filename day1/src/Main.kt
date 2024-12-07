import java.io.File
import kotlin.math.abs

data class Pair(val left: Int, val right: Int)

fun main() {
    val fileName = "input.txt"

    val pairs = File(fileName).useLines { lines ->
        lines.map { line ->
            val (left, right) = line.split("   ").map { it.toInt() }
            Pair(left, right)
        }.toList()
    }

    val sortedByLeft = pairs.sortedBy { it.left }
    val sortedByRight = pairs.sortedBy { it.right }

    val totalDistance = sortedByLeft
        .zip(sortedByRight)
        .sumOf { (leftPair, rightPair) -> abs(leftPair.left - rightPair.right) }

    println("Total distance: $totalDistance")

    val rightOccurrences = sortedByRight.groupingBy { it.right }.eachCount()

    val similarity = sortedByLeft
        .sumOf { it.left * (rightOccurrences[it.left] ?: 0) }

    println("Similarity: $similarity")
}