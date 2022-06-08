package com.fuad.mywasteappchanneling.ui.scanner

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.fuad.mywasteappchanneling.R
import com.fuad.mywasteappchanneling.databinding.ActivityScannerBinding
import com.fuad.mywasteappchanneling.utils.FileUtil
import com.fuad.mywasteappchanneling.utils.FileUtil.loadModelFile
import com.fuad.mywasteappchanneling.utils.MediaUtils
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.task.vision.detector.ObjectDetector
import java.io.File
import java.io.IOException
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScannerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnKamera.setOnClickListener { startTakePhoto() }
        binding.btnGaleri.setOnClickListener { startGallery() }

        // Step 2: Initialize the detector object
        val options = ObjectDetector.ObjectDetectorOptions.builder()
            .setMaxResults(5)
            .setScoreThreshold(0.5f)
            .build()
//        val detector = ObjectDetector.createFromFileAndOptions(
//            this, // the application context
//            "model_bangkit_v2.tflite", // must be same as the filename in assets folder
//            options
//        )

        val detector = ObjectDetector.createFromFileAndOptions(
            this, // the application context
            "model_bangkit_v2.tflite", // must be same as the filename in assets folder
            options
        )

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