package com.forum.dto.request

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class TopicoRequest(

//    @field: informa que a anotação esta no atributo e não no parametro do construto
    @field:NotEmpty
    @field:Size(min = 5, max = 100, message = "Titulo deve ter entre {min} e {max} caracteres")
    val titulo: String,

    @field:NotEmpty
    @field:Size(min = 5, max = 200, message = "Mensagem deve ter entre {min} e {max} caracteres")
    val mensagem: String,

    @field:NotNull(message = "Curso não deve estar vazio")
    val idCurso: Long,

    @field:NotNull(message = "Autor não deve estar vazio")
    val idAutor: Long

)