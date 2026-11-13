package com.ifes.listatarefas.telas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ifes.listatarefas.Tarefa

@Composable
fun TelaCadastro(
    onSalvar: (Tarefa) -> Unit,
    onVoltar: () -> Unit
) {
    var tarefa by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var tarefaConcluida by remember { mutableStateOf(false) }
    var mensagem by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Cadastro de Tarefa",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Cadastre uma tarefa e acompanhe seu status.",
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = tarefa,
                onValueChange = { tarefa = it },
                label = { Text("Nome da tarefa") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = descricao,
                onValueChange = { descricao = it },
                label = { Text("Descrição") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = tarefaConcluida,
                    onCheckedChange = { tarefaConcluida = it }
                )

                Text(text = "Tarefa concluída")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (tarefa.isNotBlank() && descricao.isNotBlank()) {
                        onSalvar(
                            Tarefa(
                                id = 0,
                                nome = tarefa,
                                descricao = descricao,
                                concluida = tarefaConcluida
                            )
                        )
                        mensagem = if (tarefaConcluida) {
                            "Tarefa salva como concluída"
                        } else {
                            "Tarefa salva como pendente"
                        }
                        tarefa = ""
                        descricao = ""
                        tarefaConcluida = false
                    } else {
                        mensagem = "Preencha todos os campos"
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Salvar tarefa")
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onVoltar,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Voltar")
            }

            if (mensagem.isNotBlank()) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = mensagem,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
