package pl.edu.pwr.lab2.i242487.dataObjects

import java.util.*

data class Task(private var title: String,
                private var description: String,
                private var dueDate: Date,
                private var status: Int,
                private var type: Int){

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
