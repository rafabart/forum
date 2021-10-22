package com.forum.service

import com.forum.dto.CursoRequestDto
import com.forum.model.Curso
import org.springframework.stereotype.Service

@Service
class CursoService(

    private var cursos: List<Curso> = ArrayList()

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
            .orElseGet { throw RuntimeException("Curso não encontrado") }
    }


    fun create(cursoRequestDto: CursoRequestDto): Curso {

        this.cursos = this.cursos.plus(
            Curso(
                id = this.cursos.size + 1L,
                nome = cursoRequestDto.nome,
                categoria = cursoRequestDto.categoria
            )
        )

        return this.cursos.last()
    }

}