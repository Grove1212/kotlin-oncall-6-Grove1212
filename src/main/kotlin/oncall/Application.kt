package oncall

import oncall.controller.InputController
import oncall.controller.OrderTable
import oncall.view.InputView
import oncall.view.OutputView

fun main() {
    val inputView = InputView()
    val outputView = OutputView()
    val inputController = InputController(inputView)
    val orderTable = OrderTable(outputView, inputController)
    orderTable.run()
}
