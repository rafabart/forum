package com.forum.controller

import com.forum.dto.request.CursoRequest
import com.forum.dto.response.CursoResponse
import com.forum.mapper.Mapper
import com.forum.model.Curso
import com.forum.service.CursoService
import org.springframework.web.bind.annotation.*
import java.util.*
import kotlin.streams.toList

@RestController
@RequestMapping("cursos")
class CursoController(

    private val cursoService: CursoService,
    private val mapper: Mapper<CursoRequest, Curso, CursoResponse>

) {


    @GetMapping
    fun getAll(): List<CursoResponse> {

        return this.cursoService.getAll()
            .stream()
            .map(mapper::toResponse)
            .toList()
    }


    @GetMapping("{id}")
    fun getById(@PathVariable id: Long): CursoResponse {

        return Optional.of(id)
            .map(cursoService::findById)
            .map(mapper::toResponse)
            .get()
    }

    @PostMapping
    fun create(@RequestBody cursoRequest: CursoRequest): CursoResponse {

        return Optional.of(cursoRequest)
            .map(cursoService::create)
            .map(mapper::toResponse)
            .get()
    }
}