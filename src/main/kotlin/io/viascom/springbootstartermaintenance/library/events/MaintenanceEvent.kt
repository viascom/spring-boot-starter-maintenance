package io.viascom.springbootstartermaintenance.library.events

import org.springframework.context.ApplicationEvent

open class MaintenanceEvent(
    source: Any,
    val action: MaintenanceAction
) : ApplicationEvent(source)