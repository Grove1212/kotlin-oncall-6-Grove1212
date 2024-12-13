package oncall.controller

import oncall.view.InputView

class GetInput(private val inputView: InputView) {
    fun run(){
        while (true){
            try {
                inputView.getMonthAndStartDayOfTheWeek()
                inputView.getTurnNumber()
            } catch (e : Exception){
                println(e.message)
            }
        }
    }
}