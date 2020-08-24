package br.com.mygym.core.data

sealed class Error {
    object DatabaseException : Error()
}
