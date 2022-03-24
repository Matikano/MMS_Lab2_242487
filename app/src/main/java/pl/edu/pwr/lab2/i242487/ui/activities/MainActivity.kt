package pl.edu.pwr.lab2.i242487.ui.activities

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.edu.pwr.lab2.i242487.R
import pl.edu.pwr.lab2.i242487.ui.adapters.TaskListAdapter
import pl.edu.pwr.lab2.i242487.model.dataObjects.Task
import pl.edu.pwr.lab2.i242487.databinding.ActivityMainBinding
import pl.edu.pwr.lab2.i242487.ui.dialogs.AddTaskDialogFragment
import pl.edu.pwr.lab2.i242487.utils.Utils

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding;

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var spEditor: SharedPreferences.Editor

    private lateinit var taskList: MutableList<Task>

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var rvAdapter: TaskListAdapter

    private lateinit var addTaskDialogCallback: AddTaskDialogFragment.AddTaskDialogFragmentCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViews()

        sharedPreferences = getSharedPreferences(Utils.SP_NAME, Context.MODE_PRIVATE)
        spEditor = sharedPreferences.edit()
    }

    override fun onResume() {
        super.onResume()
        loadTaskList()
        setUpAdapters()
        setUpCallbacks()
    }

    private fun setUpViews() {
        recyclerView = binding.rvTaskList
    }

    private fun setUpCallbacks() {
        addTaskDialogCallback = AddTaskDialogFragment.AddTaskDialogFragmentCallback { task ->
            taskList.add(task)
            Utils.setList(spEditor, Utils.SP_KEY_TASK_LIST, taskList)
            rvAdapter.notifyItemInserted(rvAdapter.itemCount - 1)
        }
    }

    private fun setUpAdapters() {
        viewManager = LinearLayoutManager(this)
        rvAdapter = TaskListAdapter(this, taskList)
        recyclerView.apply {
            layoutManager = viewManager
            adapter = rvAdapter
        }
    }

    private fun loadTaskList() {
        taskList = Utils.getTaskList(sharedPreferences)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.add_task -> showAddTaskDialog()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun showAddTaskDialog() {
        AddTaskDialogFragment(addTaskDialogCallback).apply {
            show(supportFragmentManager, AddTaskDialogFragment.TAG)
        }
    }

}