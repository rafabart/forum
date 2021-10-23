package com.forum.mapper

import com.forum.dto.request.UsuarioRequest
import com.forum.dto.response.UsuarioResponse
import com.forum.model.Usuario
import org.springframework.stereotype.Component

@Component
class UsuarioMapper {

    fun toResponse(usuario: Usuario): UsuarioResponse {

        return UsuarioResponse(
            id = usuario.id,
            nome = usuario.nome,
            email = usuario.email
        )
    }


    fun toEntity(usuarioRequest: UsuarioRequest, newId: Long): Usuario {

        return Usuario(
            id = newId,
            nome = usuarioRequest.nome,
            email = usuarioRequest.email
        )
    }
}