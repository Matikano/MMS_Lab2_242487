package pl.edu.pwr.lab2.i242487

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.RecyclerView
import pl.edu.pwr.lab2.i242487.dataObjects.Task
import pl.edu.pwr.lab2.i242487.databinding.ActivityMainBinding
import pl.edu.pwr.lab2.i242487.utils.Utils
import java.util.prefs.AbstractPreferences

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding;

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var spEditor: SharedPreferences.Editor

    private lateinit var taskList: MutableList<Task>

    private lateinit var recyclerView: RecyclerView
    private lateinit var rvAdapter:
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences(Utils.SP_NAME, Context.MODE_PRIVATE)
        spEditor = sharedPreferences.edit()
    }

    override fun onResume() {
        super.onResume()
        loadTaskList()
    }

    private fun loadTaskList() {
        taskList = Utils.getTaskList(sharedPreferences);
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
        TODO("Not yet implemented")
    }


}