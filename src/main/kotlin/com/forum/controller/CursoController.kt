package com.forum.controller

import com.forum.dto.CursoRequestDto
import com.forum.model.Curso
import com.forum.service.CursoService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("cursos")
class CursoController(

    private val cursoService: CursoService

) {


    @GetMapping
    fun getAll(): List<Curso> {
        return this.cursoService.getAll()
    }


    @PostMapping
    fun create(@RequestBody cursoRequestDto: CursoRequestDto): Curso {
        return this.cursoService.create(cursoRequestDto)
    }
}