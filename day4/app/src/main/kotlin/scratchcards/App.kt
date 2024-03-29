/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package scratchcards

import java.io.File
import kotlin.math.pow

fun readFile(fileName: String): List<String> {
    val currentDir = System.getProperty("user.dir")
    val file: File = File("$currentDir/src/main/resources/$fileName")
    return file.bufferedReader().readLines()
}

fun computeWorthOfScratchcards(cards: List<String>): Int {
    var total: Int = 0
    val regex: Regex = Regex("Card\\s+\\d+:")
    val r: Regex = Regex("\\d+")
    for (card in cards) {
        val array: Array<String> = card.replace(regex, "").split("|").toTypedArray()
        val matchesOfWinningNumbers = r.findAll(array[0])
        val matchesOfActualNumbers = r.findAll(array[1])
        val winningNumbers: Set<Int> = matchesOfWinningNumbers.toList().map { it.value.toInt() }.toSet()
        val actualNumbers: Set<Int> = matchesOfActualNumbers.toList().map { it.value.toInt() }.toSet()
        val n: Int = winningNumbers.intersect(actualNumbers).size
        if (n > 0) {
            total += (2.0).pow(n - 1).toInt()
        }
    }
    return total
}

fun computeNumberOfScratchCards(cards: List<String>): Int {
    val regex: Regex = Regex("Card\\s+\\d+:")
    val r: Regex = Regex("\\d+")
    val size: Int = cards.size
    val numberOfCards: Array<Int> = Array<Int>(size) { 1 }
    cards.forEachIndexed { index, value ->
        val array: Array<String> = value.replace(regex, "").split("|").toTypedArray()
        val matchesOfWinningNumbers = r.findAll(array[0])
        val matchesOfActualNumbers = r.findAll(array[1])
        val winningNumbers: Set<Int> = matchesOfWinningNumbers.toList().map { it.value.toInt() }.toSet()
        val actualNumbers: Set<Int> = matchesOfActualNumbers.toList().map { it.value.toInt() }.toSet()
        val n: Int = winningNumbers.intersect(actualNumbers).size
        for (i in (index + 1)..(index + n)) {
            if (i < size)  {
                numberOfCards[i] += numberOfCards[index]
            }
        }
    }
    return numberOfCards.sum()
}


fun main() {
    val input: List<String> = readFile("input.txt")
    val points: Int = computeWorthOfScratchcards(input)
    println("Total points: $points")
    val numberOfCards: Int = computeNumberOfScratchCards(input)
    println("Total number of cards: $numberOfCards")
}