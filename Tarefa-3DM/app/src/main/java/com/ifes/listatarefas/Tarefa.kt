package com.ifes.listatarefas

data class Tarefa(
    val id: Int,
    val nome: String,
    val descricao: String,
    val concluida: Boolean = false
)
