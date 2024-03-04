package com.example.diskstoreapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.diskstoreapp.database.AppDatabase
import com.example.diskstoreapp.databinding.ActivityCreateDiskBinding
import com.example.diskstoreapp.model.Disk

class CreateDiskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateDiskBinding

    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateDiskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = Room
            .databaseBuilder(
                this,
                AppDatabase::class.java,
                AppDatabase.DATABASE_NAME
            )
            .allowMainThreadQueries().build()

        binding.saveButton.setOnClickListener{
            val serialnumber = binding.serialnumberEditText.text.toString()
            val title = binding.titleEditText.text.toString()
            val author = binding.authorEditText.text.toString()
            val year = binding.yearEditText.text.toString()

            val disk = Disk(
                serialnumber = serialnumber,
                title = title,
                author = author,
                year = year
            )

            db
                .diskDao()
                .save(disk)

            finish()
        }
        binding.returnButton.setOnClickListener {
            finish()
        }
    }
}