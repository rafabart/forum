package com.forum.controller

import com.forum.dto.UsuarioRequestDto
import com.forum.model.Usuario
import com.forum.service.AutorService
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("autores")
class AutorController(

    private val autorService: AutorService

) {


    @GetMapping
    fun getAll(): List<Usuario> {
        return this.autorService.getAll()
    }


    @PostMapping
    fun create(@RequestBody usuarioRequestDto: UsuarioRequestDto): Usuario {
        return this.autorService.create(usuarioRequestDto)
    }
}