package com.example.pencilsketch


import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import androidx.activity.result.ActivityResult
import com.example.pencilsketch.databinding.ActivityCropBinding
import com.yalantis.ucrop.UCrop
import iamutkarshtiwari.github.io.ananas.editimage.EditImageActivity
import iamutkarshtiwari.github.io.ananas.editimage.ImageEditorIntentBuilder
import jp.co.cyberagent.android.gpuimage.GPUImage
import jp.co.cyberagent.android.gpuimage.filter.*
import java.io.File
import java.lang.Exception
import java.util.*
import androidx.activity.result.contract.ActivityResultContracts

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.core.content.FileProvider
import java.io.FileOutputStream


class CropActivity : AppCompatActivity() {
    private var imageBitmap: Bitmap? = null
    private var imageBitmapSecond: Bitmap? = null
    private var code:Int=1
    var someActivityResultLauncher = registerForActivityResult(
        StartActivityForResult()) { result: ActivityResult ->
        run {
            val s=result?.data?.getStringExtra(ImageEditorIntentBuilder.OUTPUT_PATH)
            imageBitmap = BitmapFactory.decodeFile(s)
            imageBitmapSecond= BitmapFactory.decodeFile(s)
            Log.d("filefile",s.toString())
            binding.imageView2.setScaleType(GPUImage.ScaleType.CENTER_INSIDE)
            Thread {
                binding.imageView2.setImage(BitmapFactory.decodeFile(s))
                //fotoyu aldık
            }.start()
        }
    }
    fun getDir(): String {
        val newDir =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        val file = File(newDir, "Pencil Sketch")
        if (!file.exists()) file.mkdirs()
        return file.path
    }
    private lateinit var binding: ActivityCropBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCropBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var fUri: Uri = Uri.parse(intent.getStringExtra("dat"))
        imageBitmap = MediaStore.Images.Media.getBitmap(
            getContentResolver(), fUri
        )
        imageBitmapSecond = MediaStore.Images.Media.getBitmap(
            getContentResolver(), fUri
        )
        binding.seekbr.animate().translationY(200f)

        binding.bak.setOnClickListener {
            finish()
        }
        binding.saveBtn.setOnClickListener {
            val filePath2Front = File(getDir(), "Pencil_Sketch_${Calendar.getInstance().timeInMillis}.jpeg")
            val streamFront = FileOutputStream(filePath2Front)
            imageBitmapSecond!!.compress(Bitmap.CompressFormat.JPEG, 100, streamFront)
            streamFront.close()

            Toast.makeText(this,"Successfully Saved!",Toast.LENGTH_SHORT).show()

            val intent = Intent(Intent.ACTION_VIEW)

            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            intent.addFlags(Intent.FLAG_RECEIVER_REGISTERED_ONLY)

            intent.setDataAndType(
                FileProvider.getUriForFile(
                    Objects.requireNonNull(getApplicationContext()),
                    BuildConfig.APPLICATION_ID + ".provider", filePath2Front
                ), "image/*"
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
                    applicationContext,
                    "No app to read File",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


        binding.canvasBtn.setOnClickListener {
//            var dest: File = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"rnd")
//            dest= File(dest,"imagess.jpeg")
            try {
                val intent: Intent = ImageEditorIntentBuilder(this, fUri.path, fUri.path)
                    .withAddText() // Add the features you need
                    .withPaintFeature()
                    .withFilterFeature()
                    .withRotateFeature()
                    .withCropFeature()
                    .withBrightnessFeature()
                    .withSaturationFeature()
                    .withBeautyFeature()
                    .withStickerFeature()
                    .forcePortrait(true) // Add this to force portrait mode (It's set to false by default)
                    .setSupportActionBarVisibility(false) // To hide app's default action bar
                    .build()
                EditImageActivity.start(someActivityResultLauncher, intent, this)
            } catch (e: Exception) {
                e.message?.let {
                    Log.e(
                        "Demo App",
                        it
                    )
                } // This could throw if either `sourcePath` or `outputPath` is blank or Null
            }
        }


        binding.imageView2.setScaleType(GPUImage.ScaleType.CENTER_INSIDE)
        Thread {
            binding.imageView2.setImage(imageBitmap)
            //fotoyu aldık
        }.start()

        binding.seekbr.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                factory(code,p1.toFloat())
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                p0?.progress?.toFloat()?.let { factory(code, it) }
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                p0?.progress?.toFloat()?.let { factory(code, it) }
            }

        })

        binding.eff1.setOnClickListener {
            factory(1,0F)
            code=1
            binding.seekbr.animate().translationY(200f)

        }
        binding.eff2.setOnClickListener {
            factory(2,0F)
            code=2
            binding.seekbr.animate().translationY(200f)

        }
        binding.eff3.setOnClickListener {
            factory(3,0F)
            code=3
            binding.seekbr.animate().translationY(0f).duration=300
            binding.seekbr.progress=55
        }

        binding.eff4.setOnClickListener {
            factory(4,1.0F)
            code=4
            binding.seekbr.animate().translationY(0f).duration=300
            binding.seekbr.progress=60
        }
        binding.eff5.setOnClickListener {
            factory(5,90.0F)
            code=5
            binding.seekbr.animate().translationY(0f).duration=300
            binding.seekbr.progress=80
        }
        binding.eff6.setOnClickListener {
            factory(6,0F)
            code=6
            binding.seekbr.animate().translationY(200f)
        }
        binding.eff7.setOnClickListener {
            factory(7,0.5F)
            code=7
            binding.seekbr.animate().translationY(200f)
        }

        binding.eff8.setOnClickListener {
            factory(8,10F)
            code=8
            binding.seekbr.animate().translationY(0f).duration=300
            binding.seekbr.progress=80
        }

        binding.eff9.setOnClickListener {
            factory(9,0F)
            code=9
            binding.seekbr.animate().translationY(200f)
        }

        binding.eff10.setOnClickListener {
            factory(10,0F)
            code=10
            binding.seekbr.animate().translationY(200f)
        }

        binding.eff11.setOnClickListener {
            factory(11,0F)
            code=11
            binding.seekbr.animate().translationY(0f).duration=300
            binding.seekbr.progress=50
        }
        binding.eff12.setOnClickListener {
            factory(12,0.25F)
            code=12
            binding.seekbr.animate().translationY(0f).duration=300
            binding.seekbr.progress=60
        }
        binding.eff13.setOnClickListener {
            factory(13,0F)
            code=13
            binding.seekbr.animate().translationY(200f)
        }
        binding.eff14.setOnClickListener {
            factory(14,0.03F)
            code=14
            binding.seekbr.animate().translationY(0f).duration=300
            binding.seekbr.progress=10
        }
        binding.eff15.setOnClickListener {
            factory(15,0F)
            code=15
            binding.seekbr.animate().translationY(200f)
        }
        binding.eff16.setOnClickListener {
            factory(16,0F)
            code=16
            binding.seekbr.animate().translationY(200f)
        }
        binding.eff17.setOnClickListener {
            factory(17,1F)
            code=17
            binding.seekbr.animate().translationY(0f).duration=300
            binding.seekbr.progress=15
        }
        binding.eff18.setOnClickListener {
            factory(18,0.01F)
            code=18
            binding.seekbr.animate().translationY(200f)
        }
        binding.eff19.setOnClickListener {
            factory(19,0F)
            code=19
            binding.seekbr.animate().translationY(200f)
        }
        binding.eff20.setOnClickListener {
            factory(20,0F)
            code=20
            binding.seekbr.animate().translationY(200f)
        }
        binding.eff21.setOnClickListener {
            factory(21,0.2F)
            code=21
            binding.seekbr.animate().translationY(0f).duration=300
            binding.seekbr.progress=20
            binding.seekbr.animate().translationY(200f)
        }
        binding.eff22.setOnClickListener {
            factory(22,0F)
            code=22
            binding.seekbr.animate().translationY(200f)
        }
        binding.eff23.setOnClickListener {
            factory(23,1F)
            code=23
            binding.seekbr.animate().translationY(0f).duration=300
            binding.seekbr.progress=80
        }
        binding.eff24.setOnClickListener {
            factory(24,0.5F)
            code=24
            binding.seekbr.animate().translationY(0f).duration=300
            binding.seekbr.progress=50
        }
        binding.eff25.setOnClickListener {
            factory(25,0F)
            code=25
            binding.seekbr.animate().translationY(200f)
        }
        binding.eff26.setOnClickListener {
            factory(26,0F)
            code=26
            binding.seekbr.animate().translationY(200f)
        }
        binding.eff27.setOnClickListener {
            factory(27,0F)
            code=27
            binding.seekbr.animate().translationY(200f)
        }
        binding.eff28.setOnClickListener {
            factory(28,0F)
            code=28
            binding.seekbr.animate().translationY(0f).duration=300
            binding.seekbr.progress=100
        }
        binding.eff29.setOnClickListener {
            factory(29,1.0F)
            code=29
            binding.seekbr.animate().translationY(0f).duration=300
            binding.seekbr.progress=100
        }
        binding.eff30.setOnClickListener {
            factory(30,3F)
            code=30
            binding.seekbr.animate().translationY(0f).duration=300
            binding.seekbr.progress=100
        }
        binding.eff31.setOnClickListener {
            factory(31,0.5F)
            code=31
            binding.seekbr.animate().translationY(0f).duration=300
            binding.seekbr.progress=50
        }
        binding.eff32.setOnClickListener {
            factory(32,0F)
            code=32
            binding.seekbr.animate().translationY(0f).duration=300
            binding.seekbr.progress=100
        }
        binding.eff33.setOnClickListener {
            factory(33,0.9F)
            code=33
            binding.seekbr.animate().translationY(0f).duration=300
            binding.seekbr.progress=70
        }
        binding.eff34.setOnClickListener {
            factory(34,1F)
            code=34
            binding.seekbr.animate().translationY(0f).duration=300
            binding.seekbr.progress=40
        }
        binding.eff35.setOnClickListener {
            factory(35,0F)
            code=35

            binding.seekbr.animate().translationY(200f)
        }
        binding.eff36.setOnClickListener {
            factory(36,0F)
            code=36
            binding.seekbr.animate().translationY(200f)
        }
        binding.eff37.setOnClickListener {
            factory(37,1F)
            code=37
            binding.seekbr.animate().translationY(0f).duration=300
            binding.seekbr.progress=10
        }
    }

    private fun factory(effect: Int, value: Float) {
        when (effect) {
            1 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageSketchFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
                imageBitmapSecond=im.bitmapWithFilterApplied
            }
            2 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageAlphaBlendFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
                imageBitmapSecond=im.bitmapWithFilterApplied
            }
            3 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageBrightnessFilter()
                v.setBrightness(value/100)
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
                imageBitmapSecond=im.bitmapWithFilterApplied
            }
            4 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageSaturationFilter()
                v.setSaturation(value/10)
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
                imageBitmapSecond=im.bitmapWithFilterApplied
            }
            5 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageHueFilter()
                v.setHue(value)
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
                imageBitmapSecond=im.bitmapWithFilterApplied
            }
            6 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageHazeFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
                imageBitmapSecond=im.bitmapWithFilterApplied
            }
            7 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageSolarizeFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
                imageBitmapSecond=im.bitmapWithFilterApplied
            }
            8 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImagePosterizeFilter()
                v.setColorLevels(value.toInt()/10)
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
                imageBitmapSecond=im.bitmapWithFilterApplied
            }
            9 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageGrayscaleFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
                imageBitmapSecond=im.bitmapWithFilterApplied
            }
            10 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageThresholdEdgeDetectionFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
                imageBitmapSecond=im.bitmapWithFilterApplied
            }
            11 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageSharpenFilter()
                v.setSharpness(value)
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
                imageBitmapSecond=im.bitmapWithFilterApplied
            }
            12 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageBulgeDistortionFilter()
                v.setRadius(value/100)
                    v.setScale(value/100)
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
                imageBitmapSecond=im.bitmapWithFilterApplied
            }
            13 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageCGAColorspaceFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
                imageBitmapSecond=im.bitmapWithFilterApplied
            }
            14 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageCrosshatchFilter()
                v.setCrossHatchSpacing(value/600)
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
                imageBitmapSecond=im.bitmapWithFilterApplied
            }
            15 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageColorInvertFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
                imageBitmapSecond=im.bitmapWithFilterApplied
            }
            16 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageDilationFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
                imageBitmapSecond=im.bitmapWithFilterApplied
            }
            17 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageZoomBlurFilter()
                v.setBlurSize(value)
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
                imageBitmapSecond=im.bitmapWithFilterApplied
            }
            18 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageHalftoneFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
                imageBitmapSecond=im.bitmapWithFilterApplied
            }
            19 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageDissolveBlendFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
                imageBitmapSecond=im.bitmapWithFilterApplied
            }
            20 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageSaturationBlendFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
                imageBitmapSecond=im.bitmapWithFilterApplied
            }
            21 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageToonFilter()
                v.setThreshold(value/200)
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
                imageBitmapSecond=im.bitmapWithFilterApplied
            }
            22 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageSepiaToneFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
                imageBitmapSecond=im.bitmapWithFilterApplied
            }
            23 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageLookupFilter()
                v.setIntensity(value/100)
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
                imageBitmapSecond=im.bitmapWithFilterApplied
            }
            24 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageSwirlFilter()
                v.setRadius(value/100)
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
                imageBitmapSecond=im.bitmapWithFilterApplied
            }
            25 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageLaplacianFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
                imageBitmapSecond=im.bitmapWithFilterApplied
            }
            26 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageLuminanceFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
                imageBitmapSecond=im.bitmapWithFilterApplied
            }
            27 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageRGBDilationFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
                imageBitmapSecond=im.bitmapWithFilterApplied
            }
            28 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageVibranceFilter()
                v.setVibrance(value/40)
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
                imageBitmapSecond=im.bitmapWithFilterApplied
            }
            29 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageEmbossFilter()
                v.intensity = value/30
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
                imageBitmapSecond=im.bitmapWithFilterApplied
            }
            30 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageKuwaharaFilter()
                v.setRadius(value.toInt()/6)
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
                imageBitmapSecond=im.bitmapWithFilterApplied
            }

            31 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageSphereRefractionFilter()
                v.setRadius(value/100)
                v.setRefractiveIndex(value/100)
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
                imageBitmapSecond=im.bitmapWithFilterApplied
            }
            32 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageWhiteBalanceFilter()
                v.setTint(value)
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
                imageBitmapSecond=im.bitmapWithFilterApplied
            }
            33 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageSobelThresholdFilter()
                v.setThreshold(value/100)
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
                imageBitmapSecond=im.bitmapWithFilterApplied
            }
            34 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageGaussianBlurFilter()
                v.setBlurSize(value/5)
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
                imageBitmapSecond=im.bitmapWithFilterApplied
            }
            35 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageVignetteFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
                imageBitmapSecond=im.bitmapWithFilterApplied
            }
            36 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageSoftLightBlendFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
                imageBitmapSecond=im.bitmapWithFilterApplied
            }
            37 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImagePixelationFilter()
                v.setPixel(value)
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
                imageBitmapSecond=im.bitmapWithFilterApplied
            }
            else -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageSketchFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
                imageBitmapSecond=im.bitmapWithFilterApplied
            }

        }
    }

}