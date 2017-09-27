package br.com.augustoccesar.fizzbuzztdd

import java.util.Random

val random = Random()

enum class Turn(val value: Int) {
    USER(0),
    MACHINE(1);

    companion object {
        fun getTurn(int: Int): Turn {
            return when (int) {
                0 -> Turn.USER
                1 -> Turn.MACHINE
                else -> Turn.MACHINE
            }
        }
    }
}

data class GameStatus(var number: Int = 1, var turn: Turn, var isRunning: Boolean)

fun getExpectedResponse(number: Int): String {
    return if (number.rem(3) == 0 && number.rem(5) == 0) {
        "fizz buzz"
    } else if (number.rem(3) == 0) {
        "fizz"
    } else if (number.rem(5) == 0) {
        "buzz"
    } else {
        number.toString()
    }
}

fun readValueIfUserTurn(gameStatus: GameStatus): String {
    return if (gameStatus.turn == Turn.USER) {
        readLine() ?: ""
    } else {
        ""
    }
}

fun updateStatus(gameStatus: GameStatus, response: String, expectedResponse: String) {
    if (gameStatus.turn == Turn.MACHINE) {
        println("Machine: $expectedResponse")
        gameStatus.turn = Turn.USER
    } else {
        if (response != expectedResponse) {
            println("You missed!")
            gameStatus.isRunning = false
        } else {
            println("You: $response")
            gameStatus.turn = Turn.MACHINE
        }
    }

    gameStatus.number++
}

fun runGame(seedRunning: Boolean = true,
            seedNumber: Int = 1,
            seedTurn: Turn = Turn.getTurn(random.nextInt(1 - 0) + 0)) {

    val gameStatus = GameStatus(seedNumber, seedTurn, seedRunning)

    while (gameStatus.isRunning) {
        val expectedResponse = getExpectedResponse(gameStatus.number)
        updateStatus(gameStatus, readValueIfUserTurn(gameStatus), expectedResponse)
    }
}

fun main(args: Array<String>) {
    runGame()
}