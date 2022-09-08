package id.alianhakim.imageupload.api


import com.google.gson.annotations.SerializedName

data class ImageUploadResponse(
    @SerializedName("downloadUrl")
    val downloadUrl: String,
    @SerializedName("fileName")
    val fileName: String,
    @SerializedName("fileSize")
    val fileSize: Int,
    @SerializedName("fileType")
    val fileType: String
)