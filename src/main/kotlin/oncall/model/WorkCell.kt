package oncall.model

import java.time.DayOfWeek

class WorkCell(
    month: Int,
    date: Int,
    val dayOfWeek: DayOfWeek?,
    var employee: String = "",
    var isHoliday: Boolean = false
):CalendarCell(month, date) {

}