package com.forum.service

import com.forum.dto.UsuarioRequestDto
import com.forum.model.Usuario
import org.springframework.stereotype.Service

@Service
class AutorService(

    private var autores: List<Usuario> = ArrayList()

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
            .orElseGet { throw RuntimeException("Usuario não encontrado") }
    }


    fun create(usuarioRequestDto: UsuarioRequestDto): Usuario {

        this.autores = this.autores.plus(
            Usuario(
                id = this.autores.size + 1L,
                nome = usuarioRequestDto.nome,
                email = usuarioRequestDto.email
            )
        )

        return this.autores.last()
    }
}
