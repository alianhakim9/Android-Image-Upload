package id.alianhakim.imageupload.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import dagger.hilt.android.AndroidEntryPoint
import id.alianhakim.imageupload.databinding.ActivityMainBinding
import id.alianhakim.imageupload.utils.getFileName
import id.alianhakim.imageupload.utils.snackbar
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<ImageViewModel>()
    private lateinit var binding: ActivityMainBinding
    private var selectedImage: Uri? = null

    companion object {
        private const val REQUEST_CODE = 200
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            uploadBtn.setOnClickListener {
                openImageChooser()
            }

            saveImage.setOnClickListener {
                uploadImage()
            }
        }

        viewModel.loading.observe(this) {
            binding.progressBar.isVisible = it
        }

        viewModel.success.observe(this) {
            binding.root.snackbar(message = it)
        }
    }

    private fun uploadImage() {
        if (selectedImage == null) {
            binding.root.snackbar(message = "select an image first")
            return
        }
        val parcelFileDescriptor =
            contentResolver.openFileDescriptor(selectedImage!!, "r", null) ?: return

        val file = File(cacheDir, contentResolver.getFileName(selectedImage!!))
        // copy file from external storage to android local storage
        val inputStream = FileInputStream(parcelFileDescriptor.fileDescriptor)
        val outputStream = FileOutputStream(file)
        inputStream.copyTo(outputStream)
        viewModel.uploadImage(file)
    }

    private fun openImageChooser() {
        Intent(Intent.ACTION_PICK).apply {
            type = "image/**"
            val mimeType = arrayOf("image/jpeg", "image/png")
            putExtra(Intent.EXTRA_MIME_TYPES, mimeType)
            startActivityForResult(this, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE -> {
                    selectedImage = data?.data
                    binding.imageView.setImageURI(selectedImage)
                }
            }
        }
    }
}