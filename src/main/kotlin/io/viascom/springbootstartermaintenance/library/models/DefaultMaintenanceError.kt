package io.viascom.springbootstartermaintenance.library.models

import com.fasterxml.jackson.annotation.JsonProperty

class DefaultMaintenanceError(
    @JsonProperty("error_code")
    val errorCode: Int,
    @JsonProperty("error_message")
    val errorMessage: String
)