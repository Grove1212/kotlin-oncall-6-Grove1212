package oncall.controller

import oncall.view.InputView

class GetInput(private val inputView: InputView) {
    fun run(){
        while (true){
            try {
                inputView.getMonthAndStartDayOfTheWeek()
            } catch (e : Exception){
                println(e.message)
            }
        }
    }
}