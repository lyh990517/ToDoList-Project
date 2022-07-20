package com.example.todoapp.Presentation.View

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isGone
import com.example.todoapp.Data.Entity.ToDoEntity
import com.example.todoapp.Presentation.BaseActivity
import com.example.todoapp.Presentation.ToDoState
import com.example.todoapp.Presentation.ViewModel.ToDoDetailViewModel
import com.example.todoapp.Presentation.ViewModel.ToDoListViewModel
import com.example.todoapp.databinding.ActivityTododetailBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import kotlin.coroutines.CoroutineContext

internal class ToDoDetailActivity : BaseActivity<ToDoDetailViewModel>(), CoroutineScope {

    private lateinit var binding: ActivityTododetailBinding
    override val viewModel: ToDoDetailViewModel by viewModel {
        parametersOf(intent.getLongExtra("id", -1))
    }

    override fun observeData() {
        viewModel.ToDoDetailLiveData.observe(this) {
            when (it) {
                is ToDoState.Uninitialized -> initView()
                is ToDoState.Loading -> handleLoading()
                is ToDoState.SuccessDetail -> handleSuccess(it)
                is ToDoState.Update -> handleUpdate()
                is ToDoState.Delete -> handleDelete()
            }
        }
    }

    private fun handleDelete() {

    }

    private fun handleUpdate() {

    }

    private fun handleLoading() = with(binding) {
        progress.isGone = false
    }

    private fun handleSuccess(it: ToDoState.SuccessDetail) = with(binding) {
        progress.isGone = true
        titleDetail.setText(it.ToDoEntity.title)
        contentDetail.setText(it.ToDoEntity.content)
    }

    private fun initView() = with(binding) {

        viewModel.fetchData()

        val ID = intent.getLongExtra("id", -1)

        completeButton.setOnClickListener {
            val title = titleDetail.text.toString()
            val content = contentDetail.text.toString()
            viewModel.updateItem(title, content)
            val intent = Intent(this@ToDoDetailActivity, ToDoListActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent)
        }
        deleteButton.setOnClickListener {
            viewModel.deleteItem(ID)
            val intent = Intent(this@ToDoDetailActivity, ToDoListActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTododetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()
}