package br.com.cotemig.socialcotemig.models

data class Direct(
    var name: String = "",
    var messages: List<Message>,
    var avatar: String = "",
    var user: String = ""
)