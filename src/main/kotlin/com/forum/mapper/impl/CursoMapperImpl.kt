package com.forum.mapper.impl

import com.forum.dto.request.CursoRequest
import com.forum.dto.response.CursoResponse
import com.forum.mapper.Mapper
import com.forum.model.Curso
import org.springframework.stereotype.Component

@Component
class CursoMapperImpl : Mapper<CursoRequest, Curso, CursoResponse> {


    override fun toResponse(e: Curso): CursoResponse {

        return CursoResponse(
            id = e.id,
            nome = e.nome,
            categoria = e.categoria
        )
    }


    override fun toEntity(r: CursoRequest): Curso {
        return Curso(
            nome = r.nome,
            categoria = r.categoria
        )
    }
}