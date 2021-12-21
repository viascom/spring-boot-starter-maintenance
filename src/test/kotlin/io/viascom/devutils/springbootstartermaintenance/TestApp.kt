package io.viascom.devutils.springbootstartermaintenance


import io.viascom.devutils.springbootstartermaintenance.core.Maintenance
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@EnableWebSecurity
@SpringBootApplication
open class TestApp : CommandLineRunner {

    private val log = LoggerFactory.getLogger(javaClass)

    @Autowired
    lateinit var maintenance: Maintenance

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<TestApp>(*args)
        }
    }

    override fun run(vararg args: String?) {
        maintenance.start()
//        maintenance.stop()
    }
}


@RestController
open class Controller {

    @GetMapping
    fun get(): String {
        return "Ich laufe"
    }

}
