package pl.edu.pwr.lab2.i242487.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.edu.pwr.lab2.i242487.dataObjects.Task

class TaskListRecyclerViewAdapter(private var context: Context,
                                  var taskList: MutableList<Task>)
    : RecyclerView.Adapter<TaskListRecyclerViewAdapter.ViewHolder>() {

    override fun getItemCount(): Int = taskList.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        var ivIcon: ImageView? = null
        var tvTitle: TextView? = null
        var tvDueDate: TextView? = null
        var ivStatus: ImageView? = null
    }

}