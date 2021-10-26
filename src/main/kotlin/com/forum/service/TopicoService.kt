package com.forum.service

import com.forum.dto.request.TopicoRequest
import com.forum.dto.request.TopicoRequestUpdate
import com.forum.exception.NotFoundException
import com.forum.mapper.impl.TopicoMapperImpl
import com.forum.model.Topico
import org.springframework.stereotype.Service
import java.util.*

@Service
class TopicoService(

    private var topicos: List<Topico> = ArrayList(),

    private val topicoMapperImpl: TopicoMapperImpl

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
            .orElseThrow { NotFoundException("Tópico não encontrado. Id = $id") }
//            .orElseGet { throw RuntimeException("Tópico não encontrado. Id = $id") }
    }


    fun save(topicoRequest: TopicoRequest): Topico {

        val newId = this.topicos.size + 1L

        val topico = topicoMapperImpl.toEntity(topicoRequest, newId)

        this.topicos = this.topicos.plus(topico)
        return this.topicos.last()
    }


    fun deleteById(id: Long) {

        this.topicos = Optional.of(id)
            .map(this::findById)
            .map(topicos::minus)
            .get()
    }


    fun update(id: Long, topicoRequestUpdate: TopicoRequestUpdate): Topico {

        val topicoToUpdate = this.findById(id)

        this.topicos = Optional.of(id)
            .map { this.deleteById(id) }
            .map { this.topicoMapperImpl.toEntityFromUpdate(topicoToUpdate, topicoRequestUpdate) }
            .map(topicos::plus)
            .get()

        return this.topicos.last()
    }

}