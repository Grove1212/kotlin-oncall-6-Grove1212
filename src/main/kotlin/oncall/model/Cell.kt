package oncall.model

import java.time.DayOfWeek

class Cell(
    val month: Int,
    val date: Int,
    val dayOfWeek: DayOfWeek?,
    val employee: String = "",
    val isHoliday: Boolean = false
) {

}