package com.forum.dto.request

data class TopicoRequest(

    val titulo: String,
    val mensagem: String,
    val idCurso: Long,
    val idAutor: Long

)