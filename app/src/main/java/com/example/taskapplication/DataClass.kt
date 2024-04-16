package com.example.taskapplication

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
class DataClass (
    @PrimaryKey(autoGenerate = true) val id: Int,
    var title: String,
    var body: String,

    ) {
}