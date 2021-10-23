package com.forum.service

import com.forum.dto.request.TopicoRequest
import com.forum.mapper.TopicoMapper
import com.forum.model.Topico
import org.springframework.stereotype.Service

@Service
class TopicoService(

    private var topicos: List<Topico> = ArrayList(),

    private val cursoService: CursoService,
    private val autorService: AutorService,
    private val topicoMapper: TopicoMapper

) {


    fun getAll(): List<Topico> {
        return this.topicos
    }


    fun findById(id: Long): Topico {

        return this.topicos.stream()
            .filter { topico ->
                topico.id == id
            }
            .findFirst()
            .orElseGet { throw RuntimeException("Tópico não encontrado. Id = $id") }
    }


    fun save(topicoRequest: TopicoRequest): Topico {

        val newId = this.topicos.size + 1L

        val topico = topicoMapper.toEntity(
            topicoRequest,
            cursoService.findById(topicoRequest.idCurso),
            autorService.findById(topicoRequest.idAutor),
            newId
        )

        this.topicos = this.topicos.plus(topico)
        return this.topicos.last()
    }

}