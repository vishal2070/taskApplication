package com.example.taskapplication.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.taskapplication.DataClass

@Dao
interface AppDao {

    @Query("SELECT * FROM user")
    fun getAllData(): LiveData<List<DataClass>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(data: List<DataClass>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSingle(data: DataClass)
}