package com.forum.controller

import com.forum.model.Topico
import com.forum.service.TopicoService
import org.springframework.web.bind.annotation.*

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


    @PostMapping
    fun create(@RequestBody topico: Topico): Topico {
        return topicoService.save(topico);
    }
}
