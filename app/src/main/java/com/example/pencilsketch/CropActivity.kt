package com.example.pencilsketch


import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import com.example.pencilsketch.databinding.ActivityCropBinding
import com.yalantis.ucrop.UCrop
import jp.co.cyberagent.android.gpuimage.GPUImage
import jp.co.cyberagent.android.gpuimage.filter.*
import java.io.File
import java.util.*

class CropActivity : AppCompatActivity() {
    private var imageBitmap: Bitmap? = null
    private lateinit var binding: ActivityCropBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCropBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var fUri: Uri =Uri.parse(intent.getStringExtra("dat"))
        imageBitmap = MediaStore.Images.Media.getBitmap(
            getContentResolver(), fUri)
        binding.imageView2.setScaleType(GPUImage.ScaleType.CENTER_INSIDE)
        Thread {
            binding.imageView2.setImage(imageBitmap)
            //fotoyu aldık
        }.start()

        binding.eff1.setOnClickListener {
            var im=GPUImage(this)
            im.setImage(imageBitmap)
            var v=GPUImageSketchFilter()
            im.setFilter(v)
            im.setBackgroundColor(23f,23f,55f)
            binding.imageView2.setImage(im.bitmapWithFilterApplied)
        }
        binding.eff2.setOnClickListener {
            var im=GPUImage(this)
            im.setImage(imageBitmap)
            var v=GPUImageSubtractBlendFilter()
            im.setFilter(v)
            im.setBackgroundColor(23f,23f,55f)
            binding.imageView2.setImage(im.bitmapWithFilterApplied)
        }
        binding.eff3.setOnClickListener {
            var im=GPUImage(this)
            im.setImage(imageBitmap)
            im.setFilter(GPUImageLuminosityBlendFilter())
            im.setBackgroundColor(23f,23f,55f)
            binding.imageView2.setImage(im.bitmapWithFilterApplied)
        }
//        binding.eff4.setOnClickListener {
//            var im=GPUImage(this)
//            im.setImage(imageBitmap)
//            var v=GPUImageBrightnessFilter()
//            v.setBrightness(5.0F)
//            im.setFilter(v)
//            im.setBackgroundColor(23f,23f,55f)
//            binding.imageView2.setImage(im.bitmapWithFilterApplied)
//        }
        binding.eff4.setOnClickListener {
            var im=GPUImage(this)
            im.setImage(imageBitmap)
            im.setFilter(GPUImageSourceOverBlendFilter())
            im.setBackgroundColor(23f,23f,55f)
            binding.imageView2.setImage(im.bitmapWithFilterApplied)
        }
        binding.eff5.setOnClickListener {
            var im=GPUImage(this)
            im.setImage(imageBitmap)
            im.setFilter(GPUImageSolarizeFilter())
            im.setBackgroundColor(23f,23f,55f)
            binding.imageView2.setImage(im.bitmapWithFilterApplied)
        }
        binding.eff6.setOnClickListener {
            var im=GPUImage(this)
            im.setImage(imageBitmap)
            var v=GPUImageSphereRefractionFilter()
            v.setRadius(5F)
            im.setFilter(v)
            im.setBackgroundColor(23f,23f,55f)
            binding.imageView2.setImage(im.bitmapWithFilterApplied)
        }
        binding.eff7.setOnClickListener {
            var im=GPUImage(this)
            im.setImage(imageBitmap)
            im.setFilter(GPUImageSoftLightBlendFilter())
            im.setBackgroundColor(23f,23f,55f)
            binding.imageView2.setImage(im.bitmapWithFilterApplied)
        }

        binding.eff8.setOnClickListener {
            var im=GPUImage(this)
            im.setImage(imageBitmap)
            im.setFilter(GPUImageThresholdEdgeDetectionFilter())
            im.setBackgroundColor(23f,23f,55f)
            binding.imageView2.setImage(im.bitmapWithFilterApplied)
        }

        binding.eff9.setOnClickListener {
            var im=GPUImage(this)
            im.setImage(imageBitmap)
            im.setFilter(GPUImageToneCurveFilter())
            im.setBackgroundColor(23f,23f,55f)
            binding.imageView2.setImage(im.bitmapWithFilterApplied)
        }

        binding.eff10.setOnClickListener {
            var im=GPUImage(this)
            im.setImage(imageBitmap)
            im.setFilter(GPUImageWeakPixelInclusionFilter())
            im.setBackgroundColor(23f,23f,55f)
            binding.imageView2.setImage(im.bitmapWithFilterApplied)
        }

    }

    private fun factory(effect:Int,value:Float){
        when(effect){
            1->{
                var im=GPUImage(this)
                im.setImage(imageBitmap)
                var v=GPUImageSketchFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f,23f,55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            2->{
                var im=GPUImage(this)
                im.setImage(imageBitmap)
                var v=GPUImageAlphaBlendFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f,23f,55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            3->{
                var im=GPUImage(this)
                im.setImage(imageBitmap)
                var v=GPUImageBrightnessFilter()
                v.setBrightness(value)
                im.setFilter(v)
                im.setBackgroundColor(23f,23f,55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            4->{
                var im=GPUImage(this)
                im.setImage(imageBitmap)
                var v=GPUImageSaturationFilter()
                v.setSaturation(value)
                im.setFilter(v)
                im.setBackgroundColor(23f,23f,55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            5->{
                var im=GPUImage(this)
                im.setImage(imageBitmap)
                var v=GPUImageHueFilter()
                v.setHue(value)
                im.setFilter(v)
                im.setBackgroundColor(23f,23f,55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            6->{
                var im=GPUImage(this)
                im.setImage(imageBitmap)
                var v=GPUImageHazeFilter()
                v.setDistance(value)
                v.setSlope(value)
                im.setFilter(v)
                im.setBackgroundColor(23f,23f,55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            7->{
                var im=GPUImage(this)
                im.setImage(imageBitmap)
                var v=GPUImageSolarizeFilter()
                v.setThreshold(value)
                im.setFilter(v)
                im.setBackgroundColor(23f,23f,55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            8->{
                var im=GPUImage(this)
                im.setImage(imageBitmap)
                var v=GPUImagePosterizeFilter()
                v.setColorLevels(value.toInt())
                im.setFilter(v)
                im.setBackgroundColor(23f,23f,55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            9->{
                var im=GPUImage(this)
                im.setImage(imageBitmap)
                var v=GPUImageGrayscaleFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f,23f,55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            10->{
                var im=GPUImage(this)
                im.setImage(imageBitmap)
                var v=GPUImageThresholdEdgeDetectionFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f,23f,55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            11->{
                var im=GPUImage(this)
                im.setImage(imageBitmap)
                var v=GPUImageSharpenFilter()
                v.setSharpness(value)
                im.setFilter(v)
                im.setBackgroundColor(23f,23f,55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            12->{
                var im=GPUImage(this)
                im.setImage(imageBitmap)
                var v=GPUImageBulgeDistortionFilter()
                v.setRadius(value)
                im.setFilter(v)
                im.setBackgroundColor(23f,23f,55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            13->{
                var im=GPUImage(this)
                im.setImage(imageBitmap)
                var v=GPUImageCGAColorspaceFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f,23f,55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            14->{
                var im=GPUImage(this)
                im.setImage(imageBitmap)
                var v=GPUImageCrosshatchFilter()
                v.setCrossHatchSpacing(value)
                im.setFilter(v)
                im.setBackgroundColor(23f,23f,55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            15->{
                var im=GPUImage(this)
                im.setImage(imageBitmap)
                var v=GPUImageColorInvertFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f,23f,55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            16->{
                var im=GPUImage(this)
                im.setImage(imageBitmap)
                var v=GPUImageDilationFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f,23f,55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            17->{
                var im=GPUImage(this)
                im.setImage(imageBitmap)
                var v=GPUImageZoomBlurFilter()
                v.setBlurSize(value)
                im.setFilter(v)
                im.setBackgroundColor(23f,23f,55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            18->{
                var im=GPUImage(this)
                im.setImage(imageBitmap)
                var v=GPUImageHalftoneFilter()
                v.setAspectRatio(value)
                im.setFilter(v)
                im.setBackgroundColor(23f,23f,55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            19->{
                var im=GPUImage(this)
                im.setImage(imageBitmap)
                var v=GPUImageDissolveBlendFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f,23f,55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            20->{
                var im=GPUImage(this)
                im.setImage(imageBitmap)
                var v=GPUImageSaturationBlendFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f,23f,55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            21->{
                var im=GPUImage(this)
                im.setImage(imageBitmap)
                var v=GPUImageToonFilter()
                v.setThreshold(value)
                im.setFilter(v)
                im.setBackgroundColor(23f,23f,55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            22->{
                var im=GPUImage(this)
                im.setImage(imageBitmap)
                var v=GPUImageSepiaToneFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f,23f,55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            23->{
                var im=GPUImage(this)
                im.setImage(imageBitmap)
                var v=GPUImageLookupFilter()
                v.setIntensity(value)
                im.setFilter(v)
                im.setBackgroundColor(23f,23f,55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            24->{
                var im=GPUImage(this)
                im.setImage(imageBitmap)
                var v=GPUImageSwirlFilter()
                v.setRadius(value)
                im.setFilter(v)
                im.setBackgroundColor(23f,23f,55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            25->{
                var im=GPUImage(this)
                im.setImage(imageBitmap)
                var v=GPUImageLaplacianFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f,23f,55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            26->{
                var im=GPUImage(this)
                im.setImage(imageBitmap)
                var v=GPUImageLuminanceFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f,23f,55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            27->{
                var im=GPUImage(this)
                im.setImage(imageBitmap)
                var v=GPUImageRGBDilationFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f,23f,55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            28->{
                var im=GPUImage(this)
                im.setImage(imageBitmap)
                var v=GPUImageVibranceFilter()
                v.setVibrance(value)
                im.setFilter(v)
                im.setBackgroundColor(23f,23f,55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            29->{
                var im=GPUImage(this)
                im.setImage(imageBitmap)
                var v=GPUImageEmbossFilter()
                v.intensity=value
                im.setFilter(v)
                im.setBackgroundColor(23f,23f,55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            30->{
                var im=GPUImage(this)
                im.setImage(imageBitmap)
                var v=GPUImageKuwaharaFilter()
                v.setRadius(value.toInt())
                im.setFilter(v)
                im.setBackgroundColor(23f,23f,55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }

                31->{
            var im=GPUImage(this)
            im.setImage(imageBitmap)
            var v=GPUImageSphereRefractionFilter()
            v.setRadius(value)
            im.setFilter(v)
            im.setBackgroundColor(23f,23f,55f)
            binding.imageView2.setImage(im.bitmapWithFilterApplied)
        }
            32->{
                var im=GPUImage(this)
                im.setImage(imageBitmap)
                var v=GPUImageWhiteBalanceFilter()
                v.setTint(value)
                v.setTemperature(value)
                im.setFilter(v)
                im.setBackgroundColor(23f,23f,55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            33->{
                var im=GPUImage(this)
                im.setImage(imageBitmap)
                var v=GPUImageSobelThresholdFilter()
                v.setThreshold(value)
                im.setFilter(v)
                im.setBackgroundColor(23f,23f,55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            34->{
                var im=GPUImage(this)
                im.setImage(imageBitmap)
                var v=GPUImageGaussianBlurFilter()
                v.setBlurSize(value)
                im.setFilter(v)
                im.setBackgroundColor(23f,23f,55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            35->{
                var im=GPUImage(this)
                im.setImage(imageBitmap)
                var v=GPUImageVignetteFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f,23f,55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            36->{
                var im=GPUImage(this)
                im.setImage(imageBitmap)
                var v=GPUImageSoftLightBlendFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f,23f,55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            37->{
                var im=GPUImage(this)
                im.setImage(imageBitmap)
                var v=GPUImagePixelationFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f,23f,55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }
            else{
                var im=GPUImage(this)
                im.setImage(imageBitmap)
                var v=GPUImageSketchFilter()
                im.setFilter(v)
                im.setBackgroundColor(23f,23f,55f)
                binding.imageView2.setImage(im.bitmapWithFilterApplied)
            }

        }
    }

}