package com.forum.mapper.impl

import com.forum.dto.request.TopicoRequest
import com.forum.dto.request.TopicoRequestUpdate
import com.forum.dto.response.TopicoResponse
import com.forum.mapper.Mapper
import com.forum.model.Topico
import com.forum.service.AutorService
import com.forum.service.CursoService
import org.springframework.stereotype.Component

@Component
class TopicoMapperImpl(

    private val cursoService: CursoService,
    private val autorService: AutorService,

    ) : Mapper<TopicoRequest, Topico, TopicoResponse> {


    override fun toResponse(e: Topico): TopicoResponse {

        return TopicoResponse(
            id = e.id,
            titulo = e.titulo,
            mensagem = e.mensagem,
            dataCriacao = e.dataCriacao,
            status = e.status
        )
    }


    override fun toEntity(r: TopicoRequest): Topico {
        return Topico(
            titulo = r.titulo,
            mensagem = r.mensagem,
            curso = cursoService.findById(r.idCurso),
            autor = autorService.findById(r.idAutor)
        )
    }


    fun toEntityFromUpdate(e: Topico, u: TopicoRequestUpdate): Topico {
        return Topico(
            id = e.id,
            titulo = u.titulo,
            mensagem = u.mensagem,
            dataCriacao = e.dataCriacao,
            curso = e.curso,
            autor = e.autor,
            status = e.status,
            respostas = e.respostas
        )
    }
}