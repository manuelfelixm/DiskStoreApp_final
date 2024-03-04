package com.example.diskstoreapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "disk")
data class Disk(
    @PrimaryKey val serialnumber: String,
    @ColumnInfo("title") val title: String,
    @ColumnInfo("author") val author: String,
    @ColumnInfo("year") val year: String
)
