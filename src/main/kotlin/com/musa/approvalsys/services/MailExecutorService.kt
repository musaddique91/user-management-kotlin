package com.musa.approvalsys.services

import com.musa.approvalsys.mail.MailComposeDTO
import com.musa.approvalsys.mail.ASMailSender
import freemarker.template.Configuration
import org.springframework.stereotype.Service
import java.io.StringWriter

@Service
class MailExecutorService(
    private val configuration: Configuration,
    private val mailSender: ASMailSender
) {

    fun sendWelcomeMail(toEmail: String, name: String) {
        val modal = mutableMapOf<String, String>()
        modal["name"] = name
        val writer = StringWriter()
        configuration.getTemplate("welcome.ftlh").process(modal, writer)
        val data = MailComposeDTO(
            to = toEmail,
            subject = "Welcome To Approval Flow",
            templateContent = writer.buffer.toString()
        )
        mailSender.send(data)
    }

}