package com.example.pr06_lazycomponents.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.BorderStroke
import com.example.pr06_lazycomponents.model.Pokemon
import com.example.pr06_lazycomponents.view.components.StatItem
import com.example.pr06_lazycomponents.ui.theme.getTypeColor

@Composable
fun PokemonDetailScreen(
    pokemon: Pokemon,
    onBackClick: () -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(modifier = Modifier.height(24.dp))
            Card(
                modifier = Modifier.size(250.dp),
                shape = CircleShape,
                border = BorderStroke(4.dp, getTypeColor(pokemon.type))

            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center //centramos la imagen de cada Pokemon en su circulo
                ) {
                    Image(
                        painter = painterResource(id = pokemon.image),
                        contentDescription = "Imagen de ${pokemon.name}",
                        modifier = Modifier.size(195.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = pokemon.name,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Tipo: ${pokemon.type}",
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

        }

        item {
            Text(
                text = "Estadísticas",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 8.dp)
            )
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatItem(statName = "HP", statValue = pokemon.stats.hp)
                StatItem(statName = "Ataque", statValue = pokemon.stats.attack)
                StatItem(statName = "Defensa", statValue = pokemon.stats.defense)
                StatItem(statName = "Ataque Especial", statValue = pokemon.stats.spAttack)
                StatItem(statName = "Defensa Especial", statValue = pokemon.stats.spDefense)
                StatItem(statName = "Velocidad", statValue = pokemon.stats.speed)

                // Total especial
                Spacer(modifier = Modifier.height(8.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "TOTAL",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = pokemon.stats.total.toString(),
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }

        item {
            Button(
                onClick = onBackClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text("Volver a la lista")
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}