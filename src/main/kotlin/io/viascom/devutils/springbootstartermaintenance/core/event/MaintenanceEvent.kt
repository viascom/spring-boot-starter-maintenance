package io.viascom.devutils.springbootstartermaintenance.core.event

import io.viascom.devutils.springbootstartermaintenance.core.model.MaintenanceState
import org.springframework.context.ApplicationEvent

class MaintenanceEvent(source: Any?, val state: MaintenanceState) : ApplicationEvent(source!!)
