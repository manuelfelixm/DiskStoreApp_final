package com.example.diskstoreapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.diskstoreapp.model.Customer
import com.example.diskstoreapp.model.Disk

@Dao
interface CustomerDao {
    @Query("SELECT * from customer")
    fun list(): List<Customer>
    @Insert
    fun save(customer: Customer)
}