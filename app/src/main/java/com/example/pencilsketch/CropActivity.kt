package com.example.pencilsketch


import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
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


class CropActivity : AppCompatActivity() {
    private var imageBitmap: Bitmap? = null
    var someActivityResultLauncher = registerForActivityResult(
        StartActivityForResult()) { result: ActivityResult ->
        run {
            val s=result?.data?.getStringExtra(ImageEditorIntentBuilder.OUTPUT_PATH)
//            imageBitmap = MediaStore.Images.Media.getBitmap(
//                getContentResolver(), Uri.parse(s)
//            )
            binding.imageView2.setScaleType(GPUImage.ScaleType.CENTER_INSIDE)
            Thread {
                binding.imageView2.setImage(Uri.parse(s))
                //fotoyu aldık
            }.start()
        }
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
        binding.canvasBtn.setOnClickListener {

            var dest: File = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"rnd")
            dest= File(dest,"images.jpeg")
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

        binding.eff1.setOnClickListener {
            factory(1,0F)
        }
        binding.eff2.setOnClickListener {
            factory(2,0F)
        }
        binding.eff3.setOnClickListener {
            factory(3,0F)
        }

        binding.eff4.setOnClickListener {
            factory(4,1.0F)
        }
        binding.eff5.setOnClickListener {
            factory(5,90.0F)
        }
        binding.eff6.setOnClickListener {
            factory(6,0F)
        }
        binding.eff7.setOnClickListener {
            factory(7,0.5F)
        }

        binding.eff8.setOnClickListener {
            factory(8,10F)
        }

        binding.eff9.setOnClickListener {
            factory(9,0F)
        }

        binding.eff10.setOnClickListener {
            factory(10,0F)
        }

        binding.eff11.setOnClickListener {
            factory(11,0F)
        }
        binding.eff12.setOnClickListener {
            factory(12,0.25F)
        }
        binding.eff13.setOnClickListener {
            factory(13,0F)
        }
        binding.eff14.setOnClickListener {
            factory(14,0.03F)
        }
        binding.eff15.setOnClickListener {
            factory(15,0F)
        }
        binding.eff16.setOnClickListener {
            factory(16,0F)
        }
        binding.eff17.setOnClickListener {
            factory(17,1F)
        }
        binding.eff18.setOnClickListener {
            factory(18,0.01F)
        }
        binding.eff19.setOnClickListener {
            factory(19,0F)
        }
        binding.eff20.setOnClickListener {
            factory(20,0F)
        }
        binding.eff21.setOnClickListener {
            factory(21,0.2F)
        }
        binding.eff22.setOnClickListener {
            factory(22,0F)
        }
        binding.eff23.setOnClickListener {
            factory(23,1F)
        }
        binding.eff24.setOnClickListener {
            factory(24,0.5F)
        }
        binding.eff25.setOnClickListener {
            factory(25,0F)
        }
        binding.eff26.setOnClickListener {
            factory(26,0F)
        }
        binding.eff27.setOnClickListener {
            factory(27,0F)
        }
        binding.eff28.setOnClickListener {
            factory(28,0F)
        }
        binding.eff29.setOnClickListener {
            factory(29,1.0F)
        }
        binding.eff30.setOnClickListener {
            factory(30,3F)
        }
        binding.eff31.setOnClickListener {
            factory(31,0.5F)
        }
        binding.eff32.setOnClickListener {
            factory(32,0F)
        }
        binding.eff33.setOnClickListener {
            factory(33,0.9F)
        }
        binding.eff34.setOnClickListener {
            factory(34,1F)
        }
        binding.eff35.setOnClickListener {
            factory(35,0F)
        }
        binding.eff36.setOnClickListener {
            factory(36,0F)
        }
        binding.eff37.setOnClickListener {
            factory(37,1F)
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
            }
            2 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageAlphaBlendFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            3 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageBrightnessFilter()
                v.setBrightness(value)
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            4 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageSaturationFilter()
                v.setSaturation(value)
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            5 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageHueFilter()
                v.setHue(value)
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            6 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageHazeFilter()
                v.setSlope(value)
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            7 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageSolarizeFilter()
                v.setThreshold(value)
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            8 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImagePosterizeFilter()
                v.setColorLevels(value.toInt())
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            9 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageGrayscaleFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            10 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageThresholdEdgeDetectionFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            11 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageSharpenFilter()
                v.setSharpness(value)
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            12 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageBulgeDistortionFilter()
                v.setRadius(value)
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            13 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageCGAColorspaceFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            14 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageCrosshatchFilter()
                v.setCrossHatchSpacing(value)
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            15 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageColorInvertFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            16 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageDilationFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            17 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageZoomBlurFilter()
                v.setBlurSize(value)
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            18 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageHalftoneFilter()
                v.setAspectRatio(value)
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            19 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageDissolveBlendFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            20 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageSaturationBlendFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            21 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageToonFilter()
                v.setThreshold(value)
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            22 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageSepiaToneFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            23 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageLookupFilter()
                v.setIntensity(value)
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            24 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageSwirlFilter()
                v.setRadius(value)
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            25 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageLaplacianFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            26 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageLuminanceFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            27 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageRGBDilationFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            28 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageVibranceFilter()
                v.setVibrance(value)
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            29 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageEmbossFilter()
                v.intensity = value
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            30 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageKuwaharaFilter()
                v.setRadius(value.toInt())
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }

            31 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageSphereRefractionFilter()
                v.setRadius(value)
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            32 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageWhiteBalanceFilter()
                v.setTint(value)
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            33 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageSobelThresholdFilter()
                v.setThreshold(value)
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            34 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageGaussianBlurFilter()
                v.setBlurSize(value)
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            35 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageVignetteFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            36 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageSoftLightBlendFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            37 -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImagePixelationFilter()
                v.setPixel(value)
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            else -> {
                var im = GPUImage(this)
                im.setImage(imageBitmap)
                var v = GPUImageSketchFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f, 23f, 55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }

        }
    }

}