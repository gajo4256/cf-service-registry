package com.gajo.dontexist.cfconsumer

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
@RestController
@EnableDiscoveryClient
class CfConsumerApplication {

	@Autowired
	private lateinit var restTemplate: RestTemplate

	@GetMapping("/consumer")
	fun getConsumerMessage(): String {
		val uri = UriComponentsBuilder.fromUriString("//producer-gajo/producer").build().toUri()
		return restTemplate.getForObject(uri, String::class.java)!!
	}

	@Bean
	@LoadBalanced
	fun restTemplate(): RestTemplate = RestTemplate()

}

fun main(args: Array<String>) {
	runApplication<CfConsumerApplication>(*args)
}
