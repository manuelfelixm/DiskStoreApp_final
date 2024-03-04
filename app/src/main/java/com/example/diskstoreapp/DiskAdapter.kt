package com.example.diskstoreapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.diskstoreapp.database.AppDatabase
import com.example.diskstoreapp.databinding.DiskLayoutBinding
import com.example.diskstoreapp.model.Disk

class DiskAdapter(
    var disks: List<Disk>,
    val context: Context,
    val db: AppDatabase
) :

    Adapter<DiskAdapter.ItemViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            layoutInflater.inflate(R.layout.disk_layout, null)
        )
    }

    override fun getItemCount(): Int {
        return disks.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val disk = disks[position]
        val binding = DiskLayoutBinding.bind(holder.itemView)

        binding.titleTextView.text = disk.title

        binding.serialnumberTextView.text = disk.serialnumber

        binding.authorTextView.text = disk.author

        binding.yearTextView.text = disk.year

        binding.deleteDiskButton.setOnClickListener{
            val deletedRows = db.diskDao().delete(disk.serialnumber)

            disks = db.diskDao().list()

            notifyDataSetChanged()
            if(deletedRows == 0) {
                Toast.makeText(context, "Ningún disco fue eliminado", Toast.LENGTH_LONG).show()
            }
        }
        binding.editDiskButton.setOnClickListener {

            val intent = Intent(context, CreateDiskActivity::class.java)

            // Obtener los extras del Intent
            val serialNumber = intent.getStringExtra("disk_serial_number")
            val author = intent.getStringExtra("disk_author")
            val title = intent.getStringExtra("disk_title")
            val year = intent.getStringExtra("disk_year")

            // Configurar los valores en los campos del formulario
            binding.serialnumberTextView.setText(serialNumber)
            binding.authorTextView.setText(author)
            binding.titleTextView.setText(title)
            binding.yearTextView.setText(year)

            context.startActivity(intent)
        }
    }


}