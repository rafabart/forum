package com.forum.service

import com.forum.dto.TopicoRequestDto
import com.forum.model.Topico
import org.springframework.stereotype.Service

@Service
class TopicoService(

    private var topicos: List<Topico> = ArrayList(),

    private val cursoService: CursoService,
    private val autorService: AutorService

) {


    fun getAll(): List<Topico> {
        return this.topicos
    }


    fun findById(id: Long): Topico {

        return this.topicos.stream()
            .filter { topico ->
                topico.id == id
            }
            .findFirst()
            .orElseGet { throw RuntimeException("Tópico não encontrado") }
    }


    fun save(topicoRequestDto: TopicoRequestDto): Topico {

        val topico = Topico(
            id = topicos.size + 1L,
            titulo = topicoRequestDto.titulo,
            mensagem = topicoRequestDto.mensagem,
            curso = cursoService.findById(topicoRequestDto.idCurso),
            autor = autorService.findById(topicoRequestDto.idAutor)
        )

        this.topicos = this.topicos.plus(topico)
        return this.topicos.last()
    }

}