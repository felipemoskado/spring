package br.com.example.extension

import br.com.example.exception.NotFoundException
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull

inline fun <reified T, ID> CrudRepository<T, ID>.findByIdOrThrow(id: ID): T =
  findByIdOrNull(id) ?: throw NotFoundException("${T::class.java.simpleName} not found. - [ID: $id]")

inline fun <reified T, ID> CrudRepository<T, ID>.deleteByIdIfExists(id: ID): Unit =
  if (existsById(id))
    deleteById(id)
  else
    throw NotFoundException("${T::class.java.simpleName} not found. - [ID: $id]")