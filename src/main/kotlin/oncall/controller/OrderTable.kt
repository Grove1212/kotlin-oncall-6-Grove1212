package oncall.controller

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


    }

    fun getStartWeekDay(startWeekDay: String): DayOfWeek? = when (startWeekDay) {
        "월" ->DayOfWeek.MONDAY
        "화" ->DayOfWeek.SATURDAY
        "수" ->DayOfWeek.TUESDAY
        "목" ->DayOfWeek.THURSDAY
        "금" ->DayOfWeek.FRIDAY
        "토" ->DayOfWeek.SATURDAY
        "일" ->DayOfWeek.SUNDAY
        else ->null
    }

    fun getLastDayOfMonth(month: Int): Int = when (month) {
        2 -> 28
        1, 3, 5, 7, 8, 10, 12 -> 31
        4, 6, 9, 11 -> 30
        else -> -1
    }
}