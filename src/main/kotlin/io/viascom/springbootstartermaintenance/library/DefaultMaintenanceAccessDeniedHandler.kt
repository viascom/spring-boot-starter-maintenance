package io.viascom.springbootstartermaintenance.library

import com.google.gson.Gson
import io.viascom.springbootstartermaintenance.library.models.DefaultMaintenanceError
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.web.access.AccessDeniedHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

open class DefaultMaintenanceAccessDeniedHandler() : AccessDeniedHandler {

    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: org.springframework.security.access.AccessDeniedException
    ) {

        val responseJson = Gson().toJson(
            DefaultMaintenanceError(
                HttpStatus.SERVICE_UNAVAILABLE.value(),
                "Service unavailable. Service is currently under maintenance! Please try again later ..."
            )
        )

        response.status = HttpStatus.SERVICE_UNAVAILABLE.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.writer.print(responseJson)
        response.writer.flush()
    }
}