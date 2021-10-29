package com.forum.controller

import com.forum.dto.request.TopicoRequest
import com.forum.dto.request.TopicoRequestUpdate
import com.forum.dto.response.TopicoPorCategoriaResponse
import com.forum.dto.response.TopicoResponse
import com.forum.mapper.impl.TopicoMapperImpl
import com.forum.service.TopicoService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("topicos")
class TopicoController(

    private val topicoService: TopicoService,
    private val topicoMapperImpl: TopicoMapperImpl

) {


    @GetMapping
    fun getAll(
        @RequestParam(required = false) nomeCurso: String?,
        @PageableDefault(size = 3, sort = ["id"], direction = Sort.Direction.DESC) paginacao: Pageable
    ): Page<TopicoResponse> {

        return Optional.of(paginacao)
            .map { this.topicoService.getAll(nomeCurso, paginacao) }
            .get()
            .map(topicoMapperImpl::toResponse)
    }


    @GetMapping("{id}")
    fun findById(@PathVariable id: Long): TopicoResponse {
        return Optional.of(id)
            .map(topicoService::findById)
            .map(topicoMapperImpl::toResponse)
            .get()
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody topicoRequest: TopicoRequest): ResponseEntity<TopicoResponse> {
        return Optional.of(topicoRequest)
            .map(topicoService::save)
            .map(topicoMapperImpl::toResponse)
            .map(this::getUri)
            .get()
    }


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
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


    @GetMapping("relatorio")
    fun relatorio(): List<TopicoPorCategoriaResponse> {
        return this.topicoService.relatorio()
    }


    private fun getUri(topicoResponse: TopicoResponse): ResponseEntity<TopicoResponse> {

        var uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(topicoResponse.id)
            .toUri();

        return ResponseEntity.created(uri).body(topicoResponse);
    }

}
