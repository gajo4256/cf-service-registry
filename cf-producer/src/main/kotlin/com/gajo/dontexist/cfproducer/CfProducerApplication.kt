package com.gajo.dontexist.cfproducer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
@RestController
@EnableDiscoveryClient
class CfProducerApplication {

    @GetMapping("/producer")
    fun getMessage(): String = "hello from producer"

}

fun main(args: Array<String>) {
    runApplication<CfProducerApplication>(*args)
}
