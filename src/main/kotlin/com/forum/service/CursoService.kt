package com.forum.service

import com.forum.dto.request.CursoRequest
import com.forum.dto.response.CursoResponse
import com.forum.mapper.Mapper
import com.forum.model.Curso
import org.springframework.stereotype.Service

@Service
class CursoService(

    private var cursos: List<Curso> = ArrayList(),

    private val cursoMapperImpl: Mapper<CursoRequest, Curso, CursoResponse>

) {


    fun getAll(): List<Curso> {
        return this.cursos
    }


    fun findById(id: Long): Curso {

        return this.cursos.stream()
            .filter { curso ->
                curso.id == id
            }
            .findFirst()
            .orElseGet { throw RuntimeException("Curso não encontrado. Id = $id") }
    }


    fun create(cursoRequest: CursoRequest): Curso {

        val newId = this.cursos.size + 1L

        this.cursos = this.cursos.plus(
            cursoMapperImpl.toEntity(cursoRequest, newId)
        )

        return this.cursos.last()
    }

}