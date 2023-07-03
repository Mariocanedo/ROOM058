package com.example.room058.Model

import androidx.lifecycle.LiveData


// RESPONSABILIDAD DE EXPONER LOS DATOS PARA VIEWMODEL
class TaskRepository(private val taskDao: TaskDao) {

    // Este value va a contener toda la información de la BD

    val listAllTask: LiveData<List<Task>> = taskDao.getAllTask1()


    suspend fun inserTask(task: Task) {
        taskDao.insertTask(task)
    }


    suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }


    suspend fun deleteAllTask() {

        taskDao.deletAll()
    }

}