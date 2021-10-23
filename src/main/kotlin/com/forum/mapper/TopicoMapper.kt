package com.forum.mapper

import com.forum.dto.request.TopicoRequest
import com.forum.dto.response.TopicoResponse
import com.forum.model.Curso
import com.forum.model.Topico
import com.forum.model.Usuario
import org.springframework.stereotype.Component

@Component
class TopicoMapper {

    fun toResponse(topico: Topico): TopicoResponse {

        return TopicoResponse(
            id = topico.id,
            titulo = topico.titulo,
            mensagem = topico.mensagem,
            dataCriacao = topico.dataCriacao,
            status = topico.status
        )
    }


    fun toEntity(
        topicoRequest: TopicoRequest,
        curso: Curso,
        usuario: Usuario,
        newId: Long

    ): Topico {

        return Topico(
            id = newId,
            titulo = topicoRequest.titulo,
            mensagem = topicoRequest.mensagem,
            curso = curso,
            autor = usuario
        )
    }
}