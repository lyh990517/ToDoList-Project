package com.example.todoapp.Presentation.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.Data.Entity.ToDoEntity
import com.example.todoapp.databinding.ItemTodoBinding

class ToDoAdapter : RecyclerView.Adapter<ToDoAdapter.ViewHolder>() {

    private var ToDoList = listOf<ToDoEntity>()
    private lateinit var DeleteClickListener: (ToDoEntity) -> Unit
    private lateinit var DetailClickListener: (ToDoEntity) -> Unit

    inner class ViewHolder(
        private val binding: ItemTodoBinding,
        private val DeleteListener: (ToDoEntity) -> Unit,
        private val DetailListener: (ToDoEntity) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(entity: ToDoEntity) {
            binding.titleItem.text = entity.title
            binding.contentItem.text = entity.content
            binding.deleteItem.setOnClickListener {
                DeleteListener(entity)
            }


            binding.root.setOnClickListener {
                DetailListener(entity)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoAdapter.ViewHolder {
        val view = ItemTodoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(view, DeleteClickListener, DetailClickListener)
    }

    override fun onBindViewHolder(holder: ToDoAdapter.ViewHolder, position: Int) {
        holder.bind(ToDoList[position])
    }

    override fun getItemCount(): Int = ToDoList.size

    fun setData(
        List: List<ToDoEntity>,
        DeleteListener: (ToDoEntity) -> Unit,
        DetailListener: (ToDoEntity) -> Unit
    ) {
        ToDoList = List
        DeleteClickListener = DeleteListener
        DetailClickListener = DetailListener
        notifyDataSetChanged()
    }
}