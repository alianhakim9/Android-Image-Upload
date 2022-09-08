package id.alianhakim.imageupload.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.alianhakim.imageupload.data.repository.ImageRepository
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val repository: ImageRepository
) : ViewModel() {
    private var _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
    private var _success = MutableLiveData<String>()
    val success: LiveData<String> = _success

    fun uploadImage(file: File) {
        _loading.value = true
        viewModelScope.launch {
            val res = repository.uploadImage(file)
            if (res) {
                _loading.value = false
                _success.value = "Upload Success"
            } else {
                _loading.value = false
            }
        }
    }
}