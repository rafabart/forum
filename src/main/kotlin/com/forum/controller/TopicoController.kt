package com.forum.controller

import com.forum.dto.request.TopicoRequest
import com.forum.dto.request.TopicoRequestUpdate
import com.forum.dto.response.TopicoResponse
import com.forum.mapper.impl.TopicoMapperImpl
import com.forum.service.TopicoService
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid
import kotlin.streams.toList

@RestController
@RequestMapping("topicos")
class TopicoController(

    private val topicoService: TopicoService,
    private val topicoMapperImpl: TopicoMapperImpl

) {


    @GetMapping
    fun getAll(): List<TopicoResponse> {

        return topicoService.getAll()
            .stream()
            .map(topicoMapperImpl::toResponse)
            .toList()
    }


    @GetMapping("{id}")
    fun findById(@PathVariable id: Long): TopicoResponse {
        return Optional.of(id)
            .map(topicoService::findById)
            .map(topicoMapperImpl::toResponse)
            .get()
    }


    @PostMapping
    fun create(@Valid @RequestBody topicoRequest: TopicoRequest): TopicoResponse {
        return Optional.of(topicoRequest)
            .map(topicoService::save)
            .map(topicoMapperImpl::toResponse)
            .get()
    }


    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Long) {
        Optional.of(id)
            .map(topicoService::deleteById)
    }


    @PutMapping("{id}")
    fun update(
        @PathVariable id: Long,
        @Valid @RequestBody topicoRequestUpdate: TopicoRequestUpdate
    ): TopicoResponse {

        return Optional.of(topicoRequestUpdate)
            .map { this.topicoService.update(id, topicoRequestUpdate) }
            .map(topicoMapperImpl::toResponse)
            .get()
    }

}
