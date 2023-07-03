package com.example.room058.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.room058.Model.Task
import com.example.room058.Model.TaskDataBase
import com.example.room058.Model.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModel( application: Application) : AndroidViewModel(application){

    //conexión con repositorio
    private val repository: TaskRepository

    // Live Data que expone la info del modelo

    val allTask : LiveData<List<Task>>


    init {
        // necesito la intancia del repositorio

        val taskDao = TaskDataBase.getDatabase(application).getTaskDao()
        repository = TaskRepository(taskDao)
        allTask = repository.listAllTask

    }

    // maneja con corutinas hace que se ejecuto el proceso en el hilos secundario
    fun insertTask(task: Task) = viewModelScope.launch {
        repository.inserTask(task)
    }

    // actualiza
    fun updateTask(task: Task) = viewModelScope.launch {
        repository.updateTask(task)
    }

    //delte all task
    fun deleteTask(task: Task)= viewModelScope.launch {
        repository.deleteAllTask()
    }

    private var selectedTask: MutableLiveData<Task?> = MutableLiveData()

    // funcion para seleccionar

    fun selected(task: Task?){
        // guardar la tarea selecionada
        selectedTask.value = task
    }
    // funcion para recibir el objeto seleccionado
    fun selectedItem(): LiveData<Task?> = selectedTask




}