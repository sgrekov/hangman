package com.intive.hangman.engine

import com.intive.hangman.TextUtils
import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldEqual
import io.kotlintest.specs.BehaviorSpec

class KotlintestGameTest_BehaviorSpec : BehaviorSpec() {
    init {
        val password = "microwave"

        Given("new game") {
            val game = Game(password = password, maxWrongAnswers = 20)

            When("created") {
                Then("has 0 wrong answers") {
                    game.wrongAnswers.shouldEqual(0)
                }

                Then("has all letters dashed") {
                    game.dashedPassword.shouldEqual("_________")
                }
            }
        }

        Given("new game") {
            val game = Game(password = password, maxWrongAnswers = 20)

            When("correct letter suggested") {
                val contains = game.suggestLetter('A')

                for (c in password) {
                    Then("returns true for letter $c") {
                        contains.shouldBe(true)
                    }

                    Then("number of wrong answers did not increase") {
                        game.wrongAnswers.shouldEqual(0)
                    }
                }
            }
        }

        Given("new game") {
            val game = Game(password = password, maxWrongAnswers = 20)

            When("any wrong letter suggested") {
                val alphabetRangeNotIn = TextUtils.createAlphabetRangeNotIn(password)

                for (c in alphabetRangeNotIn) {
                    Then("shold return false for letter $c") {
                        game.suggestLetter(c)
                    }
                }
            }
        }
    }
}