package pl.edu.pwr.lab2.i242487.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pl.edu.pwr.lab2.i242487.R
import pl.edu.pwr.lab2.i242487.databinding.ActivityTaskDetailsBinding
import pl.edu.pwr.lab2.i242487.model.dataObjects.Task

class TaskDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskDetailsBinding

    private lateinit var task: Task

    companion object {
        const val BUNDLE_KEY_TASK = "task"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTaskDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        task = intent.getSerializableExtra(BUNDLE_KEY_TASK) as Task

    }

    override fun onResume() {
        super.onResume()
        setUpViews()
    }

    private fun setUpViews() {
        when (task.status){
            Task.STATUS_DONE -> {
                binding.cvtvStatus.text = getString(R.string.task_status_done)
                binding.cvivStatusIcon.setImageResource(R.drawable.ic_baseline_done_green24)
            }
            Task.STATUS_NOT_DONE -> {
                binding.cvtvStatus.text = getString(R.string.task_status_ongoing)
                binding.cvivStatusIcon.setImageResource(R.drawable.ic_in_progress_24)
            }
        }

        when (task.type){
            Task.TYPE_EMAIL -> {
                binding.cvtvType.text = getString(R.string.task_type_email)
                binding.cvivTypeIcon.setImageResource(R.drawable.ic_email_24)
            }
            Task.TYPE_PHONE -> {
                binding.cvtvType.text = getString(R.string.task_type_phone)
                binding.cvivTypeIcon.setImageResource(R.drawable.ic_phone_24)
            }
            Task.TYPE_MEETING -> {
                binding.cvtvType.text = getString(R.string.task_type_meeting)
                binding.cvivTypeIcon.setImageResource(R.drawable.ic_meeting_24)
            }
            Task.TYPE_TODO -> {
                binding.cvtvType.text = getString(R.string.task_type_todo)
                binding.cvivTypeIcon.setImageResource(R.drawable.ic_todo_24)
            }
        }

        binding.tvLabel.text = task.title

        binding.cvtvDescription.text = when (task.description){
            "" ->  "-"
            else -> task.description
        }
    }


}