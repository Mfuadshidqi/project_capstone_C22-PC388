package com.fuad.mywasteappchanneling.ui.scanner

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.graphics.convertTo
import androidx.core.graphics.drawable.toBitmap
import com.fuad.mywasteappchanneling.R
import com.fuad.mywasteappchanneling.databinding.ActivityScannerBinding
import com.fuad.mywasteappchanneling.ml.ModelBangkitV2
import com.fuad.mywasteappchanneling.utils.MediaUtils
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.File
import java.nio.ByteBuffer

class ScannerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScannerBinding
    private lateinit var currentPhotoPath: String
    private var getFile: File? = null

//    private val tflite by lazy {
//        Interpreter(
//            FileUtil.loadMappedFile(this, MODEL_PATH),
//            Interpreter.Options().addDelegate(nnApiDelegate))
//    }


    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScannerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnKamera.setOnClickListener { startTakePhoto() }
        binding.btnGaleri.setOnClickListener { startGallery() }

        val model = ModelBangkitV2.newInstance(this)
        var bitmap = binding.previewImageView.drawable.toBitmap()

        //converting bitmap into tensor flow image
        val newBitmap = Bitmap.createScaledBitmap(
            bitmap, 224, 224, true
        )
        val byteBuffer = ByteBuffer.allocate(448 * 448 * 3)
        byteBuffer.rewind()
        newBitmap.copyPixelsToBuffer(byteBuffer)
        // Creates inputs for reference.
        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.FLOAT32)

        inputFeature0.loadBuffer(byteBuffer)

        // Runs model inference and gets result.
        val outputs = model.process(inputFeature0)
        val outputFeature0 = outputs.outputFeature0AsTensorBuffer

//        for (i in 0 until ){
            Log.d("shape","$outputFeature0")
//        }
        // Releases model resources if no longer used.
        model.close()



//        val wasteModel = ModelBangkitV2.newInstance(this)
//        var bitmap = binding.previewImageView.drawable.toBitmap()
//
//        //converting bitmap into tensor flow image
//        val newBitmap = Bitmap.createScaledBitmap(
//            bitmap, 224, 224, true
//        )
//
//        val buffer = ByteBuffer.allocate(1024 * 1024 * 3)
//        buffer.rewind()
//        newBitmap.copyPixelsToBuffer(buffer)
//
//        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.FLOAT32)
////        inputFeature0.loadBuffer(loadModelFile(assets,modelPath = "model_bangkit_v2.tflite"))
//
//        Log.d("shape", buffer.toString())
//        Log.d("shape", inputFeature0.buffer.toString())
//
//
//        inputFeature0.loadBuffer(buffer)
//
//        val output = wasteModel.process(inputFeature0)
//        val outputFeature0 = output.outputFeature0AsTensorBuffer
//
//        Log.d("output",output.toString())

//        bitmap = Bitmap.createScaledBitmap(bitmap, 32, 32, true)
//        val model = ModelBangkitV2.newInstance(this)
//
//// Creates inputs for reference.
//        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 32, 32, 1), DataType.FLOAT32)
//        inputFeature0.loadBuffer(loadModelFile(assets,modelPath = "model_bangkit_v2.tflite"))
//
//        val tensorImage = TensorImage(DataType.FLOAT32)
//        tensorImage.load(bitmap)
//        val byteBuffer = tensorImage.buffer
//
//// Runs model inference and gets result.
//        val outputs = model.process(inputFeature0)
//        val outputFeature0 = outputs.outputFeature0AsTensorBuffer

// Releases model resources if no longer used.
//        model.close()

        // Step 2: Initialize the detector object
//        val options = ObjectDetector.ObjectDetectorOptions.builder()
//            .setMaxResults(5)
//            .setScoreThreshold(0.5f)
//            .build()
//        val detector = ObjectDetector.createFromFileAndOptions(
//            this, // the application context
//            "model_bangkit_v2.tflite", // must be same as the filename in assets folder
//            options
//        )

//        val detector = ObjectDetector.createFromFileAndOptions(
//            this, // the application context
//            "model_bangkit_v2.tflite", // must be same as the filename in assets folder
//            options
//        )

//            try {
//                val buffer: ByteBuffer = loadModelFile(this.assets, "model_bangkit_v2.tflite")
//                val tflite = Interpreter(buffer)
//                Log.v(TAG, "TFLite model loaded.")
//            } catch (ex: IOException) {
//                Log.e(TAG, ex.message!!)
//            }


        // get model from firebase
//        val conditions = CustomModelDownloadConditions.Builder()
//            .build()
//        val localModel = FirebaseModelDownloader.getInstance()
//            .getModel("waste_recog", DownloadType.LOCAL_MODEL_UPDATE_IN_BACKGROUND, conditions)
//            .addOnSuccessListener {model: CustomModel? ->
//                // Download complete. Depending on your app, you could enable the ML
//                // feature, or switch from the local model to the remote model, etc.
//
//                // The CustomModel object contains the local path of the model file,
//                // which you can use to instantiate a TensorFlow Lite interpreter.
//                val modelFile = model?.file
//                if (modelFile != null) {
//                    val interpreter = Interpreter(modelFile)
//                }
//            }
//            .addOnFailureListener {
//                Log.d(TAG, it.message.toString())
//            }
    }

    // Intent gallery
    private fun startGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, resources.getString(R.string.choose_picture))
        launcherIntentGallery.launch(chooser)
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedImg: Uri = result.data?.data as Uri
            val myFile = MediaUtils.uriToFile(selectedImg, this@ScannerActivity)
            getFile = myFile
            binding.previewImageView.setImageURI(selectedImg)
        }
    }

    private fun startTakePhoto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.resolveActivity(packageManager)
        MediaUtils.createTempFile(application).also {
            val photoURI: Uri = FileProvider.getUriForFile(
                this@ScannerActivity,
                "com.fuad.mywasteappchanneling.ui.scanner",
                it
            )
            currentPhotoPath = it.absolutePath
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            launcherIntentCamera.launch(intent)
        }
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            val myFile = File(currentPhotoPath)
            getFile = myFile
            val result = BitmapFactory.decodeFile(myFile.path)
            binding.previewImageView.setImageBitmap(result)
        }
    }

    companion object {
        private val TAG = ScannerActivity::class.java.simpleName

        private const val ACCURACY_THRESHOLD = 0.5f
        private const val MODEL_PATH = "model_bangkit_v2.tflite"
        private const val LABELS_PATH = "coco_ssd_mobilenet_v1_1.0_labels.txt"
    }
}