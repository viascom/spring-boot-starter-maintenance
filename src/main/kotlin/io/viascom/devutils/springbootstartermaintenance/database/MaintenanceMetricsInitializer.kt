package io.viascom.devutils.springbootstartermaintenance.database

import io.viascom.devutils.springbootstartermaintenance.autoconfiguration.MaintenanceConfigurationProperties
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.core.io.ClassPathResource
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator
import org.springframework.stereotype.Component
import java.sql.DatabaseMetaData
import javax.sql.DataSource

@Component
class MaintenanceMetricsInitializer(
    private val datasource: DataSource,
    private val properties: MaintenanceConfigurationProperties
) {
    @EventListener(ApplicationReadyEvent::class)
    fun initialize() {
        if (properties.metrics) {
            if (!checkIfMaintenanceTableExists()) {
                val resourceDatabasePopulator = ResourceDatabasePopulator(false, false, "UTF-8", ClassPathResource("database/init-maintenance.sql"))
                resourceDatabasePopulator.execute(datasource)
            } else {

            }


            // yes:
            // TODO: check for maintenance table version
            // update
        }
    }

    private fun checkIfMaintenanceTableExists(): Boolean {
        val databaseMetaData: DatabaseMetaData = datasource.connection.metaData
        val tables = databaseMetaData.getTables(null, null, null, arrayOf("TABLE"))

        var maintenanceTableExists = false
        while (tables.next()) {
            val tableName: String = tables.getString("TABLE_NAME")
            if (tableName == "MAINTENANCE") {
                maintenanceTableExists = true
                break
            }
        }

        return maintenanceTableExists
    }
}