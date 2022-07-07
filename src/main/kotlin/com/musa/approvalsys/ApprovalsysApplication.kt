package com.musa.approvalsys

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.util.TimeZone
import javax.annotation.PostConstruct

@SpringBootApplication
@EnableTransactionManagement
class ApprovalsysApplication

fun main(args: Array<String>) {
    runApplication<ApprovalsysApplication>(*args)
}

@PostConstruct
fun init() {
    // Setting Spring Boot SetTimeZone
    TimeZone.setDefault(TimeZone.getTimeZone("IST"))
}
