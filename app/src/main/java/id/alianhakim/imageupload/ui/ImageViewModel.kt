package id.alianhakim.imageupload.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.alianhakim.imageupload.data.repository.ImageRepository
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val repository: ImageRepository
) : ViewModel() {
    fun uploadImage(file: File) {
        viewModelScope.launch {
            repository.uploadImage(file)
        }
    }
}