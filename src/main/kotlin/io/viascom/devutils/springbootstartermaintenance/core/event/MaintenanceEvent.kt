package io.viascom.devutils.springbootstartermaintenance.core.event

import org.springframework.context.ApplicationEvent

open class MaintenanceEvent(
    source: Any,
    val action: MaintenanceAction
) : ApplicationEvent(source)