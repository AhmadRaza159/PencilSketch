package com.example.pencilsketch

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import com.example.pencilsketch.databinding.ActivityMainBinding
import com.yalantis.ucrop.UCrop
import jp.co.cyberagent.android.gpuimage.filter.*
import java.io.File
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private  var imageUri: Uri?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val builder = VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())

        binding.galleryBtn.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent, "Select Picture"),
                1313
            )
        }
        binding.camBtn.setOnClickListener {
//            binding.img.setImage(binding.img.capture())
            var values = ContentValues()
            values.put(MediaStore.Images.Media.TITLE, "New Picture")
            values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera")
           imageUri= contentResolver.insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values
            )
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
            startActivityForResult(intent, 1212)

//            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//            imageFile = File(
//                Environment.getExternalStorageDirectory(),
//                "file" + System.currentTimeMillis().toString() + ".jpg"
//            )
//            imageUri = Uri.fromFile(imageFile)
//            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
//            takePictureIntent.putExtra("return-data", true)
//            startActivityForResult(takePictureIntent, 1212)
//
            

//            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//            try {
//                startActivityForResult(takePictureIntent, 1212)
//            } catch (e: ActivityNotFoundException) {
//                // display error state to the user
//                e.printStackTrace()
//            }
        }
        //binding.img.
//
//
////
//        findViewById<Button>(R.id.btn).setOnClickListener{
//
//            var im=GPUImage(this)
//            im.setImage(imageBitmap)
//            im.setFilter(GPUImageSketchFilter())
//            im.setBackgroundColor(23f,23f,55f)
//            binding.gpuimageview.setImage(im.bitmapWithFilterApplied)
//        }
//        findViewById<Button>(R.id.btn2).setOnClickListener{
//            var im=GPUImage(this)
//            im.setImage(imageBitmap)
//            im.setFilter(GPUImageExposureFilter())
//            im.setBackgroundColor(23f,23f,55f)
//            binding.gpuimageview.setImage(im.bitmapWithFilterApplied)
//        }
//        findViewById<Button>(R.id.btn3).setOnClickListener{
//            var im=GPUImage(this)
//            im.setImage(imageBitmap)
//            im.setFilter(GPUImageBrightnessFilter())
//            im.setBackgroundColor(23f,23f,55f)
//            binding.gpuimageview.setImage(im.bitmapWithFilterApplied)
//        }
//        findViewById<Button>(R.id.btn4).setOnClickListener{
//            var im=GPUImage(this)
//            im.setImage(imageBitmap)
//            im.setFilter(GPUImageMonochromeFilter())
//            im.setBackgroundColor(23f,23f,55f)
//            binding.gpuimageview.setImage(im.bitmapWithFilterApplied)
//        }
//        findViewById<Button>(R.id.btn5).setOnClickListener{
//            var im=GPUImage(this)
//            im.setImage(imageBitmap)
//            im.setFilter(GPUImageFalseColorFilter())
//            im.setBackgroundColor(23f,23f,55f)
//            binding.gpuimageview.setImage(im.bitmapWithFilterApplied)
//        }
//        findViewById<Button>(R.id.btn6).setOnClickListener{
//            var im=GPUImage(this)
//            im.setImage(imageBitmap)
//            im.setFilter(GPUImageColorBurnBlendFilter())
//            im.setBackgroundColor(23f, 23f,55f)
//            binding.gpuimageview.setImage(im.bitmapWithFilterApplied)
//        }
//        findViewById<Button>(R.id.btn7).setOnClickListener{
//            var im=GPUImage(this)
//            im.setImage(imageBitmap)
//            im.setFilter(GPUImageSaturationFilter())
//            im.setBackgroundColor(23f,23f,55f)
//            binding.gpuimageview.setImage(im.bitmapWithFilterApplied)
//        }
//        findViewById<Button>(R.id.btn8).setOnClickListener{
//            var im=GPUImage(this)
//            im.setImage(imageBitmap)
//            im.setFilter(GPUImageHueFilter())
//            im.setBackgroundColor(23f,23f,55f)
//            binding.gpuimageview.setImage(im.bitmapWithFilterApplied)
//        }
//        findViewById<Button>(R.id.btn9).setOnClickListener{
//            var im=GPUImage(this)
//            im.setImage(imageBitmap)
//            im.setFilter(GPUImageAddBlendFilter())
//            im.setBackgroundColor(23f,23f,55f)
//            binding.gpuimageview.setImage(im.bitmapWithFilterApplied)
//        }
//        findViewById<Button>(R.id.btn10).setOnClickListener{
//            var im=GPUImage(this)
//            im.setImage(imageBitmap)
//            im.setFilter(GPUImagePosterizeFilter())
//            im.setBackgroundColor(23f,23f,55f)
//            binding.gpuimageview.setImage(im.bitmapWithFilterApplied)
//        }
    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
//        Toast.makeText(this,"Reach $resultCode",Toast.LENGTH_SHORT).show()

        if (resultCode == RESULT_OK) {

            if (requestCode==UCrop.REQUEST_CROP){
                var fUri: Uri? =UCrop.getOutput(data!!)
                var intnt=Intent(this,CropActivity::class.java)
                intnt.putExtra("dat",fUri.toString())
                startActivity(intnt)
//                imageBitmap = MediaStore.Images.Media.getBitmap(
//                    getContentResolver(), fUri)
//                val matrix = Matrix()
//                matrix.postRotate(90f)
//
//                imageBitmap=Bitmap.createBitmap(imageBitmap!!, 0, 0, imageBitmap!!.getWidth(), imageBitmap!!.getHeight(),
//                    matrix, true)
                //photo's height and width gets rescale because ML Tensorflow models only accept image with 256x256 dimention
//                binding.gpuimageview.setImage(imageBitmap) //fotoyu aldık
//                binding.gpuimageview.setScaleType(GPUImage.ScaleType.CENTER_INSIDE)
//                binding.gpuimageview.requestRender()
//                Thread {
//                    binding.gpuimageview.setImage(imageBitmap)
//                    // binding.gpuimageview.setImage(binding.gpuimageview.capture()) //fotoyu aldık
//                    //fotoyu aldık
//                }.start()
            }
            //if result come from camera
            if (requestCode==1313){
                var dest=StringBuilder(UUID.randomUUID().toString()).append(".jpg").toString()
                UCrop.of(data?.data!!, Uri.fromFile(File(cacheDir,dest)))
                    .withAspectRatio(0F, 0F)
                    .useSourceImageAspectRatio()
                    .withMaxResultSize(2000, 2000)
                    .start(this);
            }
            if (requestCode == 1212) {
//                Toast.makeText(this,"Reach ${data!!.extras}",Toast.LENGTH_SHORT).show()
//                imageBitmap = data!!.getExtras()!!.get("data") as Bitmap
//                val imagebytes = ByteArrayOutputStream()
//                imageBitmap!!.compress(Bitmap.CompressFormat.JPEG, 90, imagebytes)
    
//                var inten=Intent(this,CropActivity::class.java)
//                inten.putExtra("dat",imageUri.toString())
//                startActivityForResult(inten,1001)
                var dest=StringBuilder(UUID.randomUUID().toString()).append(".jpg").toString()
                UCrop.of(imageUri!!, Uri.fromFile(File(cacheDir,dest)))
                    .withAspectRatio(0F, 0F)
                    .useSourceImageAspectRatio()
                    .withMaxResultSize(2000, 2000)
                    .start(this);
////                val extras = data!!.extras


                // performAction() //this method perform ML Tensorflow related functionality
            }
        }
    }



}