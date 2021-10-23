package com.forum.controller

import com.forum.dto.request.UsuarioRequest
import com.forum.dto.response.UsuarioResponse
import com.forum.mapper.impl.UsuarioMapperImpl
import com.forum.service.AutorService
import org.springframework.web.bind.annotation.*
import java.util.*
import kotlin.streams.toList


@RestController
@RequestMapping("autores")
class AutorController(

    private val autorService: AutorService,
    private val usuarioMapperImpl: UsuarioMapperImpl

) {


    @GetMapping
    fun getAll(): List<UsuarioResponse> {

        return this.autorService.getAll()
            .stream()
            .map(usuarioMapperImpl::toResponse)
            .toList()
    }


    @GetMapping("{id}")
    fun getById(@PathVariable id: Long): UsuarioResponse {

        return Optional.of(id)
            .map(autorService::findById)
            .map(usuarioMapperImpl::toResponse)
            .get()
    }


    @PostMapping
    fun create(@RequestBody usuarioRequest: UsuarioRequest): UsuarioResponse {

        return Optional.of(usuarioRequest)
            .map(autorService::create)
            .map(usuarioMapperImpl::toResponse)
            .get()
    }
}