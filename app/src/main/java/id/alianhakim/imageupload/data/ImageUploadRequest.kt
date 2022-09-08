package id.alianhakim.imageupload.data

import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okio.BufferedSink
import java.io.File
import java.io.FileInputStream

data class ImageUploadRequest(
    val file: File,
    val contentType: String,
) : RequestBody() {

    companion object {
        const val DEFAULT_BUFFER_SIZE = 1048
    }

    override fun contentType(): MediaType? {
        return "$contentType/*".toMediaTypeOrNull()
    }

    override fun writeTo(sink: BufferedSink) {
        val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
        val fileInputStream = FileInputStream(file)
        var uploaded = 0L
        fileInputStream.use {
            var read: Int
            while (it.read(buffer).apply {
                    read = this
                } != -1) {
                uploaded += read
                sink.write(buffer, 0, read)
            }
        }
    }
}