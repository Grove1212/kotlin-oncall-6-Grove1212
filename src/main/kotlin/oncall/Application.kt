package oncall

import oncall.controller.GetInput
import oncall.view.InputView

fun main() {
    val inputView = InputView()
    val getInput = GetInput(inputView)
    getInput.run()
}
