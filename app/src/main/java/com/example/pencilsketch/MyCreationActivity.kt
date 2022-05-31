package com.example.pencilsketch

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pencilsketch.databinding.ActivityMyCreationBinding
import java.io.File
import java.util.*

class MyCreationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyCreationBinding
    private lateinit var dataList: Array<File>
    private lateinit var adapter:AdapterFile
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMyCreationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.myCreationRecyclerview.layoutManager=LinearLayoutManager(this)
        dataList=getDownloadedFileList()
        adapter=AdapterFile(this,dataList,object :MyInterface{
            override fun onclick(model: File) {
                val intent = Intent(Intent.ACTION_VIEW)

                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                intent.addFlags(Intent.FLAG_RECEIVER_REGISTERED_ONLY)

                intent.setDataAndType(
                    Objects.requireNonNull(this@MyCreationActivity)?.let {
                        FileProvider.getUriForFile(
                            it,
                            BuildConfig.APPLICATION_ID + ".provider", model
                        )
                    }, "image/*"
                )
                try {
                    startActivity(
                        Intent.createChooser(
                            intent,
                            "Open File"
                        )
                    )
                } catch (unused: ActivityNotFoundException) {
                    Toast.makeText(
                        this@MyCreationActivity,
                        "No app to read File",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }, object : MyInterface {
            @SuppressLint("NotifyDataSetChanged")
            override fun onclick(model: File) {
                model.delete()
                dataList=getDownloadedFileList()
                adapter.getNew(dataList)
                binding.myCreationRecyclerview.adapter?.notifyDataSetChanged()
            }
        })
        binding.myCreationRecyclerview.adapter=adapter
    }

    fun getDownloadedFileList(): Array<File> {

        val newDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        val file = File(newDir, "Pencil Sketch")
        if (!file.exists()) file.mkdirs()
        val fileList= file.listFiles()
        return fileList

    }
}