package pl.edu.pwr.lab2.i242487.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.edu.pwr.lab2.i242487.R
import pl.edu.pwr.lab2.i242487.model.dataObjects.Task
import pl.edu.pwr.lab2.i242487.utils.Utils
import java.text.SimpleDateFormat
import java.util.*

class TaskListAdapter(private var context: Context,
                      var taskList: MutableList<Task>)
    : RecyclerView.Adapter<TaskListAdapter.ViewHolder>() {

    override fun getItemCount(): Int = taskList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.list_item_task, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = taskList[position]

        holder.tvTitle?.text = task.title
        holder.tvDueDate?.text = SimpleDateFormat(Utils.DATE_FORMAT_dd_MM_yyyy, Locale.UK).format(task.dueDate)

        when (task.status){
            Task.STATUS_DONE -> holder.ivStatus?.setImageResource(R.drawable.ic_baseline_done_green24)
            Task.STATUS_NOT_DONE -> holder.ivStatus?.setImageResource(R.drawable.ic_in_progress_24)
        }

        when (task.type){
            Task.TYPE_EMAIL -> holder.ivIcon?.setImageResource(R.drawable.ic_email_48)
            Task.TYPE_PHONE -> holder.ivIcon?.setImageResource(R.drawable.ic_phone_48)
            Task.TYPE_MEETING -> holder.ivIcon?.setImageResource(R.drawable.ic_meeting_48)
            Task.TYPE_TODO -> holder.ivIcon?.setImageResource(R.drawable.ic_todo_48)
        }
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        var ivIcon: ImageView? = null
        var ivStatus: ImageView? = null
        var tvTitle: TextView? = null
        var tvDueDate: TextView? = null

        init {
            ivIcon = itemView?.findViewById(R.id.ivTypeIcon)
            ivStatus = itemView?.findViewById(R.id.ivStatusIcon)
            tvTitle = itemView?.findViewById(R.id.tvTitle)
            tvDueDate = itemView?.findViewById(R.id.tvDueDate)
        }
    }

}