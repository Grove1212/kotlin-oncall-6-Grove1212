package oncall.controller

import oncall.model.Cell
import oncall.view.OutputView
import java.time.DayOfWeek

class OrderTable(
    private val outputView: OutputView,
    private val inputController: InputController
) {
    fun run() {
        val (month, startWeekDay) = inputController.controlGetMonthAndStartDayOfWeek()
        val weekdayOrder = inputController.controlGetWeekdayOrder()
        val holidayOrder = inputController.controlGetHolidayOrder()

        val shiftTable = makeShiftTable(month, startWeekDay)
        putWeekdayOrder(shiftTable)
        putHolidayOrder(shiftTable)
    }

    private fun makeShiftTable(month: Int, startWeekDayOfString: String): MutableList<Cell> {
        val shiftTable = mutableListOf<Cell>()
        val lastDateOfMonth = getLastDateOfMonth(month)
        val weekDay = getStartWeekDay(startWeekDayOfString)

        for (i in 1..lastDateOfMonth) {
            shiftTable.add(Cell(month, i, weekDay?.plus(1)))
        }
        return shiftTable
    }

    private fun putHolidayOrder(shiftTable: MutableList<Cell>) {
        TODO("Not yet implemented")
    }

    private fun putWeekdayOrder(shiftTable: MutableList<Cell>) {
        TODO("Not yet implemented")
    }

    fun getStartWeekDay(startWeekDay: String): DayOfWeek? = when (startWeekDay) {
        "월" -> DayOfWeek.MONDAY
        "화" -> DayOfWeek.SATURDAY
        "수" -> DayOfWeek.TUESDAY
        "목" -> DayOfWeek.THURSDAY
        "금" -> DayOfWeek.FRIDAY
        "토" -> DayOfWeek.SATURDAY
        "일" -> DayOfWeek.SUNDAY
        else -> null
    }

    fun getLastDateOfMonth(month: Int): Int = when (month) {
        2 -> 28
        1, 3, 5, 7, 8, 10, 12 -> 31
        4, 6, 9, 11 -> 30
        else -> -1
    }
}