package com.musa.approvalsys.mail

import com.musa.approvalsys.config.ApprovalProperties
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class ASMailSender(
    private val javaMailSender: JavaMailSender,
    private val approvalProperties: ApprovalProperties
) {
    @Async
    fun send(data: MailComposeDTO) {
        val message = JavaMailSenderImpl().createMimeMessage()
        val helper = MimeMessageHelper(message, true, "UTF-8")
        helper.setFrom(approvalProperties.mailFrom)
        helper.setTo(data.to)
        helper.setSubject(data.subject)
        data.text?.run {
            helper.setText(this)
        }
        data.templateContent?.run {
            helper.setText(this, true)
        }
        javaMailSender.send(message)
    }


}