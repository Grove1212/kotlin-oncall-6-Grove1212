package oncall.view

import oncall.model.WorkCell

class OutputView {
    fun printOrderTable(orderTable: List<WorkCell>) {
        println(orderTable.joinToString("\n"))
    }
}