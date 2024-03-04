package com.example.diskstoreapp

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.diskstoreapp.database.AppDatabase
import com.example.diskstoreapp.databinding.ActivityMainBinding
import com.example.diskstoreapp.model.Customer
import com.example.diskstoreapp.model.Disk

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.actionMenuToolbar)

        db = Room
            .databaseBuilder(
                this,
                AppDatabase::class.java,
                AppDatabase.DATABASE_NAME
            )
            .allowMainThreadQueries().build()

        //createInitialData()

        binding.disksRecyclerView.layoutManager =
            GridLayoutManager(this, 1, RecyclerView.VERTICAL, false)

        binding.disksRecyclerView.adapter = DiskAdapter(
            db.diskDao().list(), this, db
        )

        binding.addDiskButton.setOnClickListener{
            val createBookIntent = Intent(
                this, CreateDiskActivity::class.java
            )

            startActivity(createBookIntent)
        }
    }

    fun createInitialData() {
        db.diskDao().save(
            Disk("1", "Appetite for Destruction", "Guns 'N' Roses", "1987")
        )
        db.diskDao().save(
            Disk("2", "The Spaghetti Incident?", "Guns 'N' Roses", "1993")
        )
        db.diskDao().save(
            Disk("3", "Don't Cry", "Guns 'N' Roses", "1991")
        )
        db.diskDao().save(
            Disk("4", "No hay Imposibles", "Chayanne", "2009")
        )
        db.customerDao().save(
            Customer(1, "Paco", "Pérez", "pacop@gmail.com")
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_menu, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onResume() {
        super.onResume()

        val adapter = binding.disksRecyclerView.adapter as DiskAdapter

        adapter.disks = db.diskDao().list()

        adapter.notifyDataSetChanged()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater.inflate(R.menu.disk_context_menu, menu)
    }
}