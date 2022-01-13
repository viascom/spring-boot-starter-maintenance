package io.viascom.devutils.springbootstartermaintenance.security

import com.fasterxml.jackson.databind.ObjectMapper
import io.viascom.devutils.springbootstartermaintenance.core.Maintenance
import io.viascom.devutils.springbootstartermaintenance.core.model.MaintenanceError
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.web.access.AccessDeniedHandler
import java.time.Duration
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

open class DefaultMaintenanceAccessDeniedHandler(private val maintenance: Maintenance) : AccessDeniedHandler {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: org.springframework.security.access.AccessDeniedException
    ) {
        val jsonMapper = ObjectMapper()
        val responseJson = jsonMapper.writeValueAsString(
            MaintenanceError(
                HttpStatus.SERVICE_UNAVAILABLE.value(),
                "Service temporarily unavailable. Service is currently under maintenance! Please try again later ..."
            )
        )

        response.status = HttpStatus.SERVICE_UNAVAILABLE.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE

        setRetryAfterHeader(response)

        response.writer.print(responseJson)
        response.writer.flush()

        log.debug("Intercepted request to service.")
    }

    private fun setRetryAfterHeader(response: HttpServletResponse) {
        val startTime = maintenance.start
        val endTime = maintenance.end
        if (endTime != null) {
            val duration = Duration.between(startTime, endTime).toSeconds()
            response.addHeader("Retry-After", duration.toString())
        } else {
            response.addHeader("Retry-After", "60")
        }
    }
}