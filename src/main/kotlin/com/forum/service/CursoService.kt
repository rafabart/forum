package com.forum.service

import com.forum.dto.request.CursoRequest
import com.forum.dto.response.CursoResponse
import com.forum.mapper.Mapper
import com.forum.model.Curso
import com.forum.repository.CursoRepository
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
class CursoService(

    private var cursoRepository: CursoRepository,

    private val cursoMapperImpl: Mapper<CursoRequest, Curso, CursoResponse>

) {


    fun getAll(): List<Curso> {
        return this.cursoRepository.findAll()
    }


    fun findById(id: Long): Curso {

        return this.cursoRepository.findById(id)
            .orElseGet { throw RuntimeException("Curso não encontrado. Id = $id") }
    }


    @Transactional
    fun create(cursoRequest: CursoRequest): Curso {

        return Optional.of(cursoRequest)
            .map(cursoMapperImpl::toEntity)
            .map(cursoRepository::save)
            .get()
    }

}