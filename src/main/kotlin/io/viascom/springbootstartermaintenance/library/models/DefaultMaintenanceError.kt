package io.viascom.springbootstartermaintenance.library.models

import com.google.gson.annotations.SerializedName

class DefaultMaintenanceError(
    @SerializedName("error_code")
    val errorCode: Int,
    @SerializedName("error_message")
    val errorMessage: String
)