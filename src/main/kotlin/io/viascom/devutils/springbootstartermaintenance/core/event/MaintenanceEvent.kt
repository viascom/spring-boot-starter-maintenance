package io.viascom.devutils.springbootstartermaintenance.core.event

import org.springframework.context.ApplicationEvent

class MaintenanceEvent(source: Any?, val state: MaintenanceState): ApplicationEvent(source!!)