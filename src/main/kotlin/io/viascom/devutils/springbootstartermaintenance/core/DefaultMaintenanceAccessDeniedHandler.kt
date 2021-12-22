package io.viascom.devutils.springbootstartermaintenance.core

import com.fasterxml.jackson.databind.ObjectMapper
import io.viascom.devutils.springbootstartermaintenance.core.model.DefaultMaintenanceError
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.web.access.AccessDeniedHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

open class DefaultMaintenanceAccessDeniedHandler : AccessDeniedHandler {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: org.springframework.security.access.AccessDeniedException
    ) {
        val jsonMapper = ObjectMapper()
        val responseJson = jsonMapper.writeValueAsString(
            DefaultMaintenanceError(
                HttpStatus.SERVICE_UNAVAILABLE.value(),
                "Service temporarily unavailable. Service is currently under maintenance! Please try again later ..."
            )
        )

        response.status = HttpStatus.SERVICE_UNAVAILABLE.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.writer.print(responseJson)
        response.writer.flush()
    }
}