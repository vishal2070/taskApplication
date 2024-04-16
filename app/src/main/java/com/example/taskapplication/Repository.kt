package com.example.taskapplication

import androidx.lifecycle.LiveData
import com.example.taskapplication.db.ApiService
import com.example.taskapplication.db.AppDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository (private val dao: AppDao, private val apiService: ApiService) {
    val allData: LiveData<List<DataClass>> = dao.getAllData()

    suspend fun fetchDataAndStoreInDb() {
        withContext(Dispatchers.IO) {
            val data = apiService.getData()
            dao.insertData(data.map { DataClass(it.id, it.title,it.body) })
        }
    }
}