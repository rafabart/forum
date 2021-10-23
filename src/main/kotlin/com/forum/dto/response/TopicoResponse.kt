package com.forum.dto.response

import com.forum.model.StatusTopico
import java.time.LocalDateTime

data class TopicoResponse(

    val id: Long?,
    val titulo: String,
    val mensagem: String,
    val dataCriacao: LocalDateTime,
    val status: StatusTopico
)
