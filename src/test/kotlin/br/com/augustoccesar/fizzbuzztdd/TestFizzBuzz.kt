package br.com.augustoccesar.fizzbuzztdd

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.FreeSpec

class TestFizzBuzz : FreeSpec() {
    init {
        "getExpectedResponse" - {
            "should return fizz when number is divisible by 3" {
                getExpectedResponse(3) shouldBe "fizz"
            }

            "should return buzz when number is divisible by 5" {
                getExpectedResponse(5) shouldBe "buzz"
            }

            "should return fizz buzz when number is divisible by 3 and 5" {
                getExpectedResponse(15) shouldBe "fizz buzz"
            }
        }

        "updateStatus" -{
            "if machine turn" - {
                "should answer write and update status" {
                    val gameStatus = GameStatus(1, turn = Turn.MACHINE, isRunning = true)
                    updateStatus(gameStatus, "", getExpectedResponse(gameStatus.number))

                    gameStatus.turn shouldBe Turn.USER
                    gameStatus.isRunning shouldBe true
                    gameStatus.number shouldBe 2
                }
            }

            "if user turn" - {
                "should update if right answer" {
                    val gameStatus = GameStatus(1, turn = Turn.USER, isRunning = true)
                    updateStatus(gameStatus, "1", getExpectedResponse(gameStatus.number))

                    gameStatus.turn shouldBe Turn.MACHINE
                    gameStatus.isRunning shouldBe true
                    gameStatus.number shouldBe 2
                }

                "should exit if wrong answer" {
                    val gameStatus = GameStatus(1, turn = Turn.USER, isRunning = true)
                    updateStatus(gameStatus, "2", getExpectedResponse(gameStatus.number))

                    gameStatus.turn shouldBe Turn.USER
                    gameStatus.isRunning shouldBe false
                    gameStatus.number shouldBe 2
                }
            }
        }
    }
}