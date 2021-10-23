package com.forum.controller

import com.forum.dto.request.TopicoRequest
import com.forum.dto.response.TopicoResponse
import com.forum.mapper.TopicoMapper
import com.forum.service.TopicoService
import org.springframework.web.bind.annotation.*
import java.util.*
import kotlin.streams.toList

@RestController
@RequestMapping("topicos")
class TopicoController(

    private val topicoService: TopicoService,
    private val topicoMapper: TopicoMapper

) {


    @GetMapping
    fun getAll(): List<TopicoResponse> {

        return topicoService.getAll()
            .stream()
            .map(topicoMapper::toResponse)
            .toList()
    }


    @GetMapping("{id}")
    fun findById(@PathVariable id: Long): TopicoResponse {
        return Optional.of(id)
            .map(topicoService::findById)
            .map(topicoMapper::toResponse)
            .get()
    }


    @PostMapping
    fun create(@RequestBody topicoRequest: TopicoRequest): TopicoResponse {
        return Optional.of(topicoRequest)
            .map(topicoService::save)
            .map(topicoMapper::toResponse)
            .get()
    }
}
