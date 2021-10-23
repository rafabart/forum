package com.forum.mapper

import com.forum.dto.request.CursoRequest
import com.forum.dto.response.CursoResponse
import com.forum.model.Curso
import org.springframework.stereotype.Component

@Component
class CursoMapper {

    fun toReponse(curso: Curso): CursoResponse {

        return CursoResponse(
            id = curso.id,
            nome = curso.nome,
            categoria = curso.categoria
        )
    }


    fun toEntity(cursoRequest: CursoRequest, newId: Long): Curso {

        return Curso(
            id = newId,
            nome = cursoRequest.nome,
            categoria = cursoRequest.categoria
        )
    }
}