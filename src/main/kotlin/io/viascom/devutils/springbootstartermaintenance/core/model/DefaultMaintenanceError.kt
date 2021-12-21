package io.viascom.devutils.springbootstartermaintenance.core.model

import com.fasterxml.jackson.annotation.JsonProperty

open class DefaultMaintenanceError(
    @JsonProperty("error_code")
    val errorCode: Int,
    @JsonProperty("error_message")
    val errorMessage: String
)