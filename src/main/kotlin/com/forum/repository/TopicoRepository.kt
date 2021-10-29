package com.forum.repository

import com.forum.dto.response.TopicoPorCategoriaResponse
import com.forum.model.Topico
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TopicoRepository : JpaRepository<Topico, Long> {

    fun findByCursoNome(nome: String, paginacao: Pageable): Page<Topico>


    @Query(
        "SELECT new com.forum.dto.response.TopicoPorCategoriaResponse(curso.categoria, count(t)) " +
                "FROM Topico t " +
                "JOIN t.curso curso " +
                "GROUP BY curso.categoria"
    )
    fun relatorio(): List<TopicoPorCategoriaResponse>

}