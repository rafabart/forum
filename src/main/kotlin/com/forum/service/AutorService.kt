package com.forum.service

import com.forum.dto.request.UsuarioRequest
import com.forum.mapper.impl.UsuarioMapperImpl
import com.forum.model.Usuario
import com.forum.repository.UsuarioRepository
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
class AutorService(

    private var usuarioRepository: UsuarioRepository,

    private val usuarioMapperImpl: UsuarioMapperImpl

) {


    fun getAll(): List<Usuario> {
        return this.usuarioRepository.findAll()
    }


    fun findById(id: Long): Usuario {

        return this.usuarioRepository
            .findById(id)
            .orElseGet { throw RuntimeException("Usuário não encontrado. Id = $id") }
    }


    @Transactional
    fun create(usuarioRequest: UsuarioRequest): Usuario {

        return Optional.of(usuarioRequest)
            .map(usuarioMapperImpl::toEntity)
            .map(usuarioRepository::save)
            .get()
    }
}
