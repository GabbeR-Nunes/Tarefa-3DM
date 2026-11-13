package com.ifes.listatarefas.telas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ifes.listatarefas.Tarefa

@Composable
fun TelaDetalhe(
    tarefa: Tarefa?,
    onVoltar: () -> Unit,
    onMarcarConcluida: (Int) -> Unit
) {
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
                text = "Detalhes da Tarefa",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            if (tarefa == null) {
                Text(
                    text = "Tarefa não encontrada",
                    fontSize = 16.sp
                )
            } else {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Informações da tarefa",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Tarefa: ${tarefa.nome}"
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Descrição: ${tarefa.descricao}"
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = if (tarefa.concluida) {
                                "Status: concluída"
                            } else {
                                "Status: pendente"
                            },
                            color = if (tarefa.concluida) {
                                Color(0xFF1B5E20)
                            } else {
                                Color(0xFFE65100)
                            },
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                if (!tarefa.concluida) {
                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { onMarcarConcluida(tarefa.id) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Marcar como concluída")
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onVoltar,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Voltar")
            }
        }
    }
}
