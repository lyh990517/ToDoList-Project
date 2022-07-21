package com.example.todoapp.Presentation.View

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.core.view.isGone
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.Data.Entity.ToDoEntity
import com.example.todoapp.Presentation.Adapter.ToDoAdapter
import com.example.todoapp.Presentation.BaseActivity
import com.example.todoapp.Presentation.ToDoState
import com.example.todoapp.Presentation.ViewModel.ToDoListViewModel
import com.example.todoapp.R
import com.example.todoapp.databinding.ActivityTodolistBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext

internal class ToDoListActivity : BaseActivity<ToDoListViewModel>(), CoroutineScope {
    private lateinit var binding: ActivityTodolistBinding
    private val adapter = ToDoAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodolistBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override val viewModel: ToDoListViewModel by viewModel()

    override fun observeData() {
        viewModel.ToDoLiveData.observe(this) {
            when (it) {
                is ToDoState.Uninitialized -> initView()
                is ToDoState.Loading -> handleLoading()
                is ToDoState.Success -> handleSuccess(it)
                is ToDoState.Insert -> handleInsert()
                is ToDoState.Delete -> handleDelete()
            }
        }
    }

    private fun handleDelete() = with(binding) {
        ToDoRefresh.isRefreshing = false
        viewModel.fetchData()
    }

    private fun handleInsert() = with(binding) {
        ToDoRefresh.isRefreshing = false

        viewModel.fetchData()
    }

    private fun handleSuccess(it: ToDoState.Success) = with(binding) {

        ToDoRefresh.isRefreshing = false
        ToDoRefresh.isEnabled = it.ToDoList.isNotEmpty()
        ToDoRecycler.isEnabled = it.ToDoList.isNotEmpty()

        adapter.setData(it.ToDoList,
            DeleteListener = {
                viewModel.deleteOne(it.id)
            },
            DetailListener = {
                val intent = Intent(this@ToDoListActivity, ToDoDetailActivity::class.java)
                intent.putExtra("id", it.id)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent)
            }
        )
    }

    private fun handleLoading() = with(binding) {
        ToDoRefresh.isRefreshing = true
    }

    private fun initView() = with(binding) {
        ToDoRecycler.layoutManager = LinearLayoutManager(this@ToDoListActivity)
        ToDoRecycler.adapter = adapter

        ToDoRefresh.setOnRefreshListener {
            viewModel.fetchData()
        }
        inputButton.setOnClickListener {
            if(inputBar.text.isEmpty() || contentInput.text.isEmpty()){
                Toast.makeText(this@ToDoListActivity,"제목과 내용울 둘다 입력해주십시오.",Toast.LENGTH_SHORT).show()
            }else{
                val entity = ToDoEntity(
                    title = inputBar.text.toString(),
                    content = contentInput.text.toString()
                )
                viewModel.insertOne(entity)
                inputBar.text.clear()
                contentInput.text.clear()
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
}