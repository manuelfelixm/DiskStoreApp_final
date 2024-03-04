package com.example.diskstoreapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.diskstoreapp.model.Disk

@Dao
interface DiskDao {

    @Query("SELECT * FROM disk")
    fun list(): List<Disk>

    @Query("DELETE FROM disk WHERE serialnumber=:serialnumber")
    fun delete(serialnumber: String): Int

    @Query("SELECT * FROM disk WHERE author=:author")
    fun listAuthorDisks(author: String): List<Disk>

    @Insert
    fun save(disk: Disk)
}