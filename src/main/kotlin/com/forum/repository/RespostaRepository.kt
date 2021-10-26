package com.forum.repository

import com.forum.model.Resposta
import org.springframework.data.jpa.repository.JpaRepository

interface RespostaRepository : JpaRepository<Resposta, Long> {
}