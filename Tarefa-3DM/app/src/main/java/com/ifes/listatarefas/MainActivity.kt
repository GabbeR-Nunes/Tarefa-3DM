package com.ifes.listatarefas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ifes.listatarefas.telas.TelaCadastro
import com.ifes.listatarefas.telas.TelaConcluidas
import com.ifes.listatarefas.telas.TelaDetalhe
import com.ifes.listatarefas.telas.TelaPrincipal
import com.ifes.listatarefas.ui.theme.ListaTarefasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ListaTarefasTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val tarefas = remember { mutableStateListOf<Tarefa>() }
    var proximoId by remember { mutableIntStateOf(1) }

    NavHost(
        navController = navController,
        startDestination = "principal"
    ) {
        composable("principal") {
            TelaPrincipal(
                tarefas = tarefas,
                onNavigateToCadastro = {
                    navController.navigate("cadastro")
                },
                onNavigateToDetalhe = { id ->
                    navController.navigate("detalhe/$id")
                },
                onNavigateToConcluidas = {
                    navController.navigate("concluidas")
                }
            )
        }

        composable("cadastro") {
            TelaCadastro(
                onSalvar = { novaTarefa ->
                    tarefas.add(
                        novaTarefa.copy(id = proximoId)
                    )
                    proximoId++
                },
                onVoltar = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = "detalhe/{tarefaId}",
            arguments = listOf(
                navArgument("tarefaId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val tarefaId = backStackEntry.arguments?.getInt("tarefaId")
            val tarefa = tarefas.find { it.id == tarefaId }

            TelaDetalhe(
                tarefa = tarefa,
                onVoltar = {
                    navController.popBackStack()
                },
                onMarcarConcluida = { id ->
                    val index = tarefas.indexOfFirst { it.id == id }
                    if (index != -1) {
                        val atual = tarefas[index]
                        tarefas[index] = atual.copy(concluida = true)
                    }
                }
            )
        }

        composable("concluidas") {
            TelaConcluidas(
                tarefas = tarefas,
                onNavigateToDetalhe = { id ->
                    navController.navigate("detalhe/$id")
                },
                onVoltar = {
                    navController.popBackStack()
                }
            )
        }
    }
}
