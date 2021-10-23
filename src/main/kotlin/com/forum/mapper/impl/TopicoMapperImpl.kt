package com.forum.mapper.impl

import com.forum.dto.request.TopicoRequest
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


    override fun toEntity(r: TopicoRequest, id: Long): Topico {
        return Topico(
            id = id,
            titulo = r.titulo,
            mensagem = r.mensagem,
            curso = cursoService.findById(r.idCurso),
            autor = autorService.findById(r.idAutor)
        )
    }
}