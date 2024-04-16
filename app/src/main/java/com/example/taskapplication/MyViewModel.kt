package com.example.taskapplication

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.taskapplication.db.ApiService
import com.example.taskapplication.db.AppDatabase
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyViewModel (application: Application) : AndroidViewModel(application) {
    private val repository: Repository
    val allData: LiveData<List<DataClass>>

    init {
        val dao = AppDatabase.getDatabase(application).dao()
        val apiService = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
        repository = Repository(dao, apiService)
        allData = repository.allData
    }

    fun fetchDataAndStoreInDb() {
        if (isNetworkAvailable(getApplication())) {
            viewModelScope.launch {
                repository.fetchDataAndStoreInDb()
            }
        } else {
            // Handle no internet connection
            // For example, show a toast message or display an error message to the user

            val context = getApplication<Application>().applicationContext
            Toast.makeText(context, "No internet connection", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val capabilities =
            connectivityManager.getNetworkCapabilities(network)
        return capabilities != null &&
                (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
    }


}