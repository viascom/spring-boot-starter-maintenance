package io.viascom.springbootstarterwebmaintenance

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class MaintenanceLibrary {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<MaintenanceLibrary>(*args)
        }
    }

}


