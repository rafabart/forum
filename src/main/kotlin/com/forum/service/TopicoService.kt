package com.forum.service

import com.forum.model.Curso
import com.forum.model.Topico
import com.forum.model.Usuario
import org.springframework.stereotype.Service

@Service
class TopicoService {


    fun getAll(): List<Topico> {
        return listOf(createTopic())
    }


    fun findById(id: Long): Topico {
        return listOf(createTopic()).stream().filter { topico ->
            topico.id == id
        }.findFirst().get()
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