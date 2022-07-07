package com.musa.approvalsys.mail

data class MailComposeDTO(
    var to: String,
    var cc: MutableList<String>? = null,
    var subject: String,
    var text: String? = null,
    var attachmentsPath: MutableList<String>? = null,
    var templateContent: String? = null
)