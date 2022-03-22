package pl.edu.pwr.lab2.i242487.utils

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import pl.edu.pwr.lab2.i242487.dataObjects.Task

class Utils {

    companion object {

        const val SP_NAME = "tasks"
        const val SP_KEY_TASK_LIST = "taskList"

        const val DATE_FORMAT_dd_MM_yyyy = "dd.MM.yyyy"

        fun <T> setList(spEditor: SharedPreferences.Editor, spKey: String, list: MutableList<T>) {
            val gson = Gson()
            val jsonString = gson.toJson(list)
            spEditor.putString(spKey, jsonString)
            spEditor.commit()
        }

        fun getTaskList(sp: SharedPreferences): MutableList<Task> {
            var list: MutableList<Task>
            val gson  = Gson()
            val serializedObject = sp.getString(SP_KEY_TASK_LIST, gson.toJson(mutableListOf<Task>()))
            val type = object: TypeToken<MutableList<Task>>(){}.type
            list = gson.fromJson(serializedObject, type)
            return list
        }

    }

}