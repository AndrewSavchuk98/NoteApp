package com.savchukandrew.noteapp.core

interface Mapper<T, R> {

    fun map(data: T): R
}