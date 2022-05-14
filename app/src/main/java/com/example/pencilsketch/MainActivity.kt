package com.example.pencilsketch

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import com.devs.sketchimage.SketchImage

class MainActivity : AppCompatActivity() {
    private var imageBitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<ImageView>(R.id.img).setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            try {
                startActivityForResult(takePictureIntent, 1212)
            } catch (e: ActivityNotFoundException) {
                // display error state to the user
                e.printStackTrace()
            }
        }


        findViewById<Button>(R.id.btn).setOnClickListener{
            var skim:SketchImage=SketchImage.Builder(this,imageBitmap).build()
            findViewById<ImageView>(R.id.img).setImageBitmap(skim.getImageAs(SketchImage.ORIGINAL_TO_COLORED_SKETCH,100))
        }
    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            //if result come from camera
            if (requestCode == 1212) {
                val extras = data!!.extras
                imageBitmap = extras!!["data"] as Bitmap? //bit işlem
                //photo's height and width gets rescale because ML Tensorflow models only accept image with 256x256 dimention
                findViewById<ImageView>(R.id.img).setImageBitmap(imageBitmap) //fotoyu aldık
                // performAction() //this method perform ML Tensorflow related functionality
            }
        }
    }
}