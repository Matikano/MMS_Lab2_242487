package pl.edu.pwr.lab2.i242487.dataObjects

import java.util.*

data class Task(var title: String,
                var description: String,
                var dueDate: Date,
                var status: Int,
                var type: Int){

    companion object {
        const val STATUS_DONE = 1
        const val STATUS_NOT_DONE = 0

        const val TYPE_TODO = 1
        const val TYPE_EMAIL = 2
        const val TYPE_PHONE = 3
        const val TYPE_MEETING = 4
    }

    fun toggleStatus() {
        when (status) {
            STATUS_DONE -> return
            STATUS_NOT_DONE -> this.status = STATUS_DONE
        }
    }


}
