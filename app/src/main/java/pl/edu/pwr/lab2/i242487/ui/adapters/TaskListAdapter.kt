package pl.edu.pwr.lab2.i242487.ui.adapters

import android.content.Context
import android.graphics.Canvas
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import pl.edu.pwr.lab2.i242487.R
import pl.edu.pwr.lab2.i242487.model.dataObjects.Task
import pl.edu.pwr.lab2.i242487.utils.Utils
import java.text.SimpleDateFormat
import java.util.*


class TaskListAdapter(private var context: Context,
                      var taskList: MutableList<Task>)
    : RecyclerView.Adapter<TaskListAdapter.ViewHolder>() {

    var onItemClick: ((Task) -> Unit) ?= null

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

    fun markAsDone(position: Int){
        taskList[position].toggleStatus()
        notifyItemChanged(position)
    }

    fun removeItem(position: Int){
        taskList.removeAt(position)
        notifyItemRemoved(position)
    }


    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        var ivIcon: ImageView? = null
        var ivStatus: ImageView? = null
        var tvTitle: TextView? = null
        var tvDueDate: TextView? = null

        init {
            ivIcon = itemView?.findViewById(R.id.ivTypeIcon)
            ivStatus = itemView?.findViewById(R.id.ivStatusIcon)
            tvTitle = itemView?.findViewById(R.id.tvTitle)
            tvDueDate = itemView?.findViewById(R.id.tvDueDate)

            itemView!!.setOnClickListener {
                onItemClick?.invoke(taskList[adapterPosition])
            }
        }

    }


    abstract class SwipeGesture(val context: Context) :
        ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){

        val deleteColor = ContextCompat.getColor(context, R.color.delete_color)
        val doneColor = ContextCompat.getColor(context, R.color.done_color)
        val deleteIcon = R.drawable.ic_baseline_delete_36
        val doneIcon = R.drawable.ic_baseline_done_36


        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onChildDraw(
            c: Canvas,
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            dX: Float,
            dY: Float,
            actionState: Int,
            isCurrentlyActive: Boolean
        ) {

            RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                .addSwipeLeftActionIcon(doneIcon)
                .addSwipeLeftBackgroundColor(doneColor)
                .addSwipeRightActionIcon(deleteIcon)
                .addSwipeRightBackgroundColor(deleteColor)
                .create()
                .decorate();

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        }

    }

}