package com.forum.service

import com.forum.dto.request.UsuarioRequest
import com.forum.mapper.UsuarioMapper
import com.forum.model.Usuario
import org.springframework.stereotype.Service

@Service
class AutorService(

    private var autores: List<Usuario> = ArrayList(),

    private val usuarioMapper: UsuarioMapper

) {


    fun getAll(): List<Usuario> {
        return this.autores
    }


    fun findById(id: Long): Usuario {

        return this.autores.stream()
            .filter { usuario ->
                usuario.id == id
            }
            .findFirst()
            .orElseGet { throw RuntimeException("Usuário não encontrado. Id = $id") }
    }


    fun create(usuarioRequest: UsuarioRequest): Usuario {

        val newId = this.autores.size + 1L

        this.autores = this.autores.plus(
            usuarioMapper.toEntity(usuarioRequest, newId)
        )

        return this.autores.last()
    }
}
