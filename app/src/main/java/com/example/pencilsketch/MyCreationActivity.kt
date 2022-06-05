package com.example.pencilsketch

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pencilsketch.databinding.ActivityMyCreationBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import java.io.File
import java.util.*

class MyCreationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyCreationBinding
    private lateinit var dataList: Array<File>
    private lateinit var adapter:AdapterFile
    private var selectedFile:File?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMyCreationBinding.inflate(layoutInflater)

        setContentView(binding.root)
        BottomSheetBehavior.from(binding.bottomSheet.bottomSheetFrame).apply {
            peekHeight = 0
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        binding.myCreationRecyclerview.layoutManager=GridLayoutManager(this,3)
        dataList=getDownloadedFileList()
        if(dataList.size>0){
            binding.lottieMain.visibility= View.GONE
            Toast.makeText(this,"Long click on any image to get more options",Toast.LENGTH_SHORT).show()

        }
        else{
            binding.lottieMain.visibility=View.VISIBLE
        }
        adapter=AdapterFile(this,dataList,object :MyInterface{
            override fun onclick(model: File) {
                BottomSheetBehavior.from(binding.bottomSheet.bottomSheetFrame).state = BottomSheetBehavior.STATE_COLLAPSED

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
        },object:MyInterface{
            override fun onclick(model: File) {
                BottomSheetBehavior.from(binding.bottomSheet.bottomSheetFrame).state = BottomSheetBehavior.STATE_COLLAPSED
                selectedFile=model
                BottomSheetBehavior.from(binding.bottomSheet.bottomSheetFrame).state = BottomSheetBehavior.STATE_EXPANDED

            }

        },object :MyInterface{
            override fun onclick(model: File) {
                BottomSheetBehavior.from(binding.bottomSheet.bottomSheetFrame).state = BottomSheetBehavior.STATE_COLLAPSED
                selectedFile=model
                BottomSheetBehavior.from(binding.bottomSheet.bottomSheetFrame).state = BottomSheetBehavior.STATE_EXPANDED

            }

        })
        binding.myCreationRecyclerview.adapter=adapter

        binding.bottomSheet.btnDelete.setOnClickListener {
            if (selectedFile!=null){
                BottomSheetBehavior.from(binding.bottomSheet.bottomSheetFrame).state = BottomSheetBehavior.STATE_COLLAPSED

                var dailog=AlertDialog.Builder(this)
                dailog.setMessage("Deleting ${selectedFile?.name}")
                dailog.setPositiveButton("Confirm",DialogInterface.OnClickListener { dialogInterface, i ->
                    selectedFile?.delete()
                    dataList=getDownloadedFileList()
                    adapter.getNew(dataList)
                    binding.myCreationRecyclerview.adapter?.notifyDataSetChanged()
                    selectedFile=null
                    if(dataList.size>0){
                        binding.lottieMain.visibility= View.GONE
                    }
                    else{
                        binding.lottieMain.visibility=View.VISIBLE
                    }
                })
                dailog.show()
            }
        }
        binding.bottomSheet.closeBtm.setOnClickListener {
            BottomSheetBehavior.from(binding.bottomSheet.bottomSheetFrame).state = BottomSheetBehavior.STATE_COLLAPSED
            selectedFile=null
        }

        binding.bottomSheet.btnShare.setOnClickListener {
            if (selectedFile!=null){
                BottomSheetBehavior.from(binding.bottomSheet.bottomSheetFrame).state = BottomSheetBehavior.STATE_COLLAPSED

                val intent = Intent("android.intent.action.SEND")
                intent.type = "image/*"
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)


                intent.putExtra(
                    "android.intent.extra.STREAM", Objects.requireNonNull(this)?.let {
                        FileProvider.getUriForFile(
                            it,
                            BuildConfig.APPLICATION_ID + ".provider", selectedFile!!
                        )
                    }
                )
                try {
                    startActivity(
                        Intent.createChooser(
                            intent,
                            "Share File "
                        )
                    )
                } catch (unused: ActivityNotFoundException) {
                    Toast.makeText(
                        this,
                        "No app to read File",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
                selectedFile=null

            }
        }
        binding.bak.setOnClickListener {
            finish()
        }
    }

    fun getDownloadedFileList(): Array<File> {

        val newDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        val file = File(newDir, "Pencil Sketch")
        if (!file.exists()) file.mkdirs()
        val fileList= file.listFiles()
        return fileList

    }
}