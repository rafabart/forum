package com.forum.controller

import com.forum.model.Topico
import com.forum.service.TopicoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("topicos")
class TopicoController(
        private val topicoService: TopicoService
) {


    @GetMapping
    fun getAll(): List<Topico> {
        return topicoService.getAll()
    }


    @GetMapping("{id}")
    fun findById(@PathVariable id: Long): Topico {
        return topicoService.findById(id)
    }
}
