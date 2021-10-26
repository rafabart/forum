package com.forum.service

import com.forum.dto.request.TopicoRequest
import com.forum.dto.request.TopicoRequestUpdate
import com.forum.exception.NotFoundException
import com.forum.mapper.impl.TopicoMapperImpl
import com.forum.model.Topico
import com.forum.repository.TopicoRepository
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
class TopicoService(

    private var topicoRepository: TopicoRepository,

    private val topicoMapperImpl: TopicoMapperImpl

) {


    fun getAll(): List<Topico> {
        return this.topicoRepository.findAll()
    }


    fun findById(id: Long): Topico {

        return this.topicoRepository.findById(id)
            .orElseThrow { NotFoundException("Tópico não encontrado. Id = $id") }
//            .orElseGet { throw RuntimeException("Tópico não encontrado. Id = $id") }
    }

    @Transactional
    fun save(topicoRequest: TopicoRequest): Topico {

        return Optional.of(topicoRequest)
            .map(topicoMapperImpl::toEntity)
            .map(topicoRepository::save)
            .get()
    }


    @Transactional
    fun deleteById(id: Long) {

        Optional.of(id)
            .map(this::findById)
            .map(topicoRepository::delete)
    }


    @Transactional
    fun update(id: Long, topicoRequestUpdate: TopicoRequestUpdate): Topico {

        return Optional.of(id)
            .map(this::findById)
            .map { t -> this.topicoMapperImpl.toEntityFromUpdate(t, topicoRequestUpdate) }
            .map(topicoRepository::save)
            .get()
    }

}