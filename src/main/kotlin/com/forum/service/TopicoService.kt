package com.forum.service

import com.forum.model.Curso
import com.forum.model.Topico
import com.forum.model.Usuario
import org.springframework.stereotype.Service

@Service
class TopicoService(

    private var topicos: List<Topico> = ArrayList()

) {


    fun getAll(): List<Topico> {
        this.topicos.plus(createTopic())
        return this.topicos
    }


    fun findById(id: Long): Topico {
        return topicos.stream().filter { topico ->
            topico.id == id
        }.findFirst().get()
    }


    fun save(topico: Topico): Topico {
        this.topicos.plus(topico)
        return this.topicos.last()
    }


    private fun createTopic(): Topico {

        val curso = Curso(
            1,
            "Curso de Spring",
            "JAVA"
        )

        val usuario = Usuario(
            1,
            "Bart Simpsons",
            "bart@email.com"
        )

        val topico = Topico(
            id = 1,
            titulo = "Teste de tópico",
            mensagem = "Esse é a mensagem do teste do tópico",
            curso = curso,
            autor = usuario,
        )

        return topico
    }
}