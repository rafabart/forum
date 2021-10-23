package com.forum.mapper

interface Mapper<R, E, RS> {

    fun toResponse(e: E): RS

    fun toEntity(r: R, id: Long): E
}