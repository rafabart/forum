package com.forum.mapper.impl

import com.forum.dto.request.UsuarioRequest
import com.forum.dto.response.UsuarioResponse
import com.forum.mapper.Mapper
import com.forum.model.Usuario
import org.springframework.stereotype.Component

@Component
class UsuarioMapperImpl : Mapper<UsuarioRequest, Usuario, UsuarioResponse> {


    override fun toResponse(e: Usuario): UsuarioResponse {

        return UsuarioResponse(
            id = e.id,
            nome = e.nome,
            email = e.email
        )
    }


    override fun toEntity(r: UsuarioRequest): Usuario {

        return Usuario(
            nome = r.nome,
            email = r.email
        )
    }
}