package br.com.cotemig.socialcotemig.models

data class Post(

    var date: String = "",
    var image: String = "",
    var description: String = "",
    var id: String = "",
    var avatar: String = "",
    var user: String = "",
    var local: String = "",
    var gallery: List<PostImage>,
    var likes: List<String>

)