package oncall.controller

import oncall.model.CalendarCell
import oncall.model.WorkCell
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
        val holidays = inputController.controlGetHolidays()

        val shiftTable = makeShiftTable(month, startWeekDay)
        putHoliday(shiftTable, holidays)
        putOrder(shiftTable, weekdayOrder, holidayOrder)
    }

    private fun makeShiftTable(month: Int, startWeekDayOfString: String): MutableList<WorkCell> {
        val shiftTable = mutableListOf<WorkCell>()
        val lastDateOfMonth = getLastDateOfMonth(month)
        val weekDay = getStartWeekDay(startWeekDayOfString)

        for (i in 1..lastDateOfMonth) {
            shiftTable.add(WorkCell(month, i, weekDay?.plus(1)))
        }
        return shiftTable
    }

    private fun putHoliday(shiftTable: MutableList<WorkCell>, holidays: List<CalendarCell>) {
        holidays.filter { shiftTable[0].month == it.month }
            .forEach { holiday ->
                shiftTable.find { it.date == holiday.date }?.isHoliday = true
            }
    }

    private fun putOrder(shiftTable: MutableList<WorkCell>, weekdayOrder: List<String>, holidayOrder: List<String>) {
        val weekdayCells = shiftTable.filter { it.dayOfWeek != DayOfWeek.SATURDAY && it.dayOfWeek != DayOfWeek.SUNDAY && !it.isHoliday}

        val holidayCells = shiftTable.filter { it.dayOfWeek == DayOfWeek.SATURDAY || it.dayOfWeek == DayOfWeek.SUNDAY || it.isHoliday }

        assignEmployees(weekdayCells, weekdayOrder)
        assignEmployees(holidayCells, holidayOrder)
    }

    private fun assignEmployees(workCells: List<WorkCell>, order: List<String>) {
        workCells.forEachIndexed { index, cell ->
            cell.employee = order[index % order.size]
        }
    }

    private fun getStartWeekDay(startWeekDay: String): DayOfWeek? = when (startWeekDay) {
        "월" -> DayOfWeek.MONDAY
        "화" -> DayOfWeek.SATURDAY
        "수" -> DayOfWeek.TUESDAY
        "목" -> DayOfWeek.THURSDAY
        "금" -> DayOfWeek.FRIDAY
        "토" -> DayOfWeek.SATURDAY
        "일" -> DayOfWeek.SUNDAY
        else -> null
    }

    private fun getLastDateOfMonth(month: Int): Int = when (month) {
        2 -> 28
        1, 3, 5, 7, 8, 10, 12 -> 31
        4, 6, 9, 11 -> 30
        else -> -1
    }
}