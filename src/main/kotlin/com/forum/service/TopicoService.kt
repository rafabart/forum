package com.forum.service

import com.forum.dto.request.TopicoRequest
import com.forum.dto.request.TopicoRequestUpdate
import com.forum.dto.response.TopicoPorCategoriaResponse
import com.forum.exception.NotFoundException
import com.forum.mapper.impl.TopicoMapperImpl
import com.forum.model.Topico
import com.forum.repository.TopicoRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*
import javax.persistence.EntityManager
import javax.transaction.Transactional

@Service
class TopicoService(

    private var topicoRepository: TopicoRepository,

    private val topicoMapperImpl: TopicoMapperImpl,


    //Caso seja necessário é possível injetar o EntityManager (DAO)
    private val entityManager: EntityManager

) {

    @Cacheable("topicos")
    fun getAll(nomeCurso: String?, paginacao: Pageable): Page<Topico> {

        if (nomeCurso == null) {
            return this.topicoRepository.findAll(paginacao)
        }

        return this.topicoRepository.findByCursoNome(nomeCurso, paginacao)
    }


    fun findById(id: Long): Topico {

        return this.topicoRepository.findById(id)
            .orElseThrow { NotFoundException("Tópico não encontrado. Id = $id") }
//            .orElseGet { throw RuntimeException("Tópico não encontrado. Id = $id") }
    }


    @Transactional
    @CacheEvict(value = ["topicos"], allEntries = true)
    fun save(topicoRequest: TopicoRequest): Topico {

        return Optional.of(topicoRequest)
            .map(topicoMapperImpl::toEntity)
            .map(topicoRepository::save)
            .get()
    }


    @Transactional
    @CacheEvict(value = ["topicos"], allEntries = true)
    fun deleteById(id: Long) {

        Optional.of(id)
            .map(this::findById)
            .map(topicoRepository::delete)
    }


    @Transactional
    @CacheEvict(value = ["topicos"], allEntries = true)
    fun update(id: Long, topicoRequestUpdate: TopicoRequestUpdate): Topico {

        return Optional.of(id)
            .map(this::findById)
            .map { t -> this.topicoMapperImpl.toEntityFromUpdate(t, topicoRequestUpdate) }
            .map(topicoRepository::save)
            .get()
    }


    fun relatorio(): List<TopicoPorCategoriaResponse> {
        return this.topicoRepository.relatorio()
    }

}
