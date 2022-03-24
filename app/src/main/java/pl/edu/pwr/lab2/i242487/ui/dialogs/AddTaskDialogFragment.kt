package pl.edu.pwr.lab2.i242487.ui.dialogs

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.google.android.material.datepicker.MaterialDatePicker
import pl.edu.pwr.lab2.i242487.R
import pl.edu.pwr.lab2.i242487.model.dataObjects.Task
import pl.edu.pwr.lab2.i242487.databinding.DialogAddTaskBinding
import pl.edu.pwr.lab2.i242487.utils.Utils
import java.text.SimpleDateFormat
import java.util.*

class AddTaskDialogFragment(private val dialogCallback: AddTaskDialogFragmentCallback) : DialogFragment() {

    private lateinit var binding: DialogAddTaskBinding

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var spEditor: SharedPreferences.Editor

    private var dueDate: Date? = null

    companion object {
        const val TAG = "Add Task Dialog"
        const val DATE_PICKER_TAG = "Due Date Picker"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogAddTaskBinding.inflate(inflater)

        setButtonListeners()

        sharedPreferences = requireContext().getSharedPreferences(Utils.SP_NAME, Context.MODE_PRIVATE)
        spEditor = sharedPreferences.edit()

        return binding.root
    }

    private fun setButtonListeners() {

        binding.btnCancel.setOnClickListener {
            dialog?.cancel()
        }

        binding.btnAddTask.setOnClickListener {
            if(validateFields()){
                val type = when (binding.rgType.checkedRadioButtonId){
                    R.id.rbEmail -> Task.TYPE_EMAIL
                    R.id.rbPhone -> Task.TYPE_PHONE
                    R.id.rbMeeting -> Task.TYPE_MEETING
                    R.id.rbTodo -> Task.TYPE_TODO
                    else -> 0
                }

                val task = Task(
                    binding.tietTitle.text.toString(),
                    binding.tietDescription.text.toString(),
                    dueDate!!,
                    Task.STATUS_NOT_DONE,
                    type
                )

                dialogCallback.onNewTaskCreated(task)

                dialog?.cancel()
            }
        }

        val dateTimePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText(R.string.date_picker_title)
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()


        binding.btnDatePicker.setOnClickListener {
            dateTimePicker.show(parentFragmentManager, DATE_PICKER_TAG)
            dateTimePicker.addOnPositiveButtonClickListener {

                dueDate = Date(dateTimePicker.selection!!)
                val dateFormat = SimpleDateFormat(Utils.DATE_FORMAT_dd_MM_yyyy, Locale.ENGLISH)

                binding.tietDueDate.setText(dateFormat.format(dueDate!!))
            }
        }


    }

    private fun validateTitle(): Boolean {
        val valid = binding.tietTitle.text.toString() != ""

        when {
            valid -> binding.tilTitle.helperText = null
            else -> binding.tilTitle.helperText = requireContext().getText(R.string.title_validation_error)
        }

        return valid
    }

    private fun validateDueDate(): Boolean {
        val valid = binding.tietDueDate.text.toString() != ""

        when {
            valid -> binding.tilDueDate.helperText = null
            else -> binding.tilDueDate.helperText = requireContext().getText(R.string.due_date_validation_error)
        }

        return valid && dueDate != null
    }

    private fun validateTypeSelection(): Boolean {
        val valid =  when (binding.rgType.checkedRadioButtonId) {
            R.id.rbEmail, R.id.rbPhone, R.id.rbMeeting, R.id.rbTodo -> true
            else -> false
        }
        return valid
    }

    private fun validateFields(): Boolean {
        return validateTitle() && validateDueDate() && validateTypeSelection()
    }


    fun interface AddTaskDialogFragmentCallback {
        fun onNewTaskCreated(task: Task)
    }


}