package com.juanrolando.coche.ui.theme.home

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.juanrolando.coche.CocheTopAppBar
import com.juanrolando.coche.R
import com.juanrolando.coche.data.Coche
import com.juanrolando.coche.ui.theme.AppViewModelProvider
import com.juanrolando.coche.ui.theme.navigation.NavigationDestination
import com.juanrolando.coche.ui.theme.CocheTheme
import androidx.compose.foundation.Image

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.appName
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navigateToCocheDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val homeUiState by viewModel.homeUiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CocheTopAppBar(
                title = stringResource(HomeDestination.titleRes),
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },

    ) { innerPadding ->
        HomeBody(
            cocheList = homeUiState.cocheList,
            onCocheClick = navigateToCocheDetail,
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}

@Composable
private fun HomeBody(
    cocheList: List<Coche>, onCocheClick: (Int) -> Unit, modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (cocheList.isEmpty()) {
            Text(
                text = stringResource(R.string.no_coche_description),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        } else {
            CocheList(
                cocheList = cocheList,
                onCocheClick = { onCocheClick(it.id) },
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small))
            )
        }
    }
}

@Composable
private fun CocheList(
    cocheList: List<Coche>, onCocheClick: (Coche) -> Unit, modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(items = cocheList, key = { it.id }) { coche ->
            CocheItem(coche = coche,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .clickable { onCocheClick(coche) })
        }
    }
}

@Composable
private fun CocheItem(
    coche: Coche, modifier: Modifier = Modifier
) {
    Card(
        /*modifier = modifier, elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)*/
        modifier = modifier, colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
        ) {

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = coche.imagen),
                    contentDescription = null, // Descripción opcional para accesibilidad
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(Modifier.weight(1f))
                Column {
                    Text(
                        text = coche.name,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = coche.country,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = coche.city,
                        style = MaterialTheme.typography.titleMedium
                    )

                }
            }



        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeBodyPreview() {
    CocheTheme {
        HomeBody(listOf(
            Coche(1, "Camioneta", "Toyota", "Hilux", 5, 5000.99,2)

        ), onCocheClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun HomeBodyEmptyListPreview() {
    CocheTheme {
        HomeBody(listOf(), onCocheClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun CocheItemPreview() {
    CocheTheme {
        CocheItem(
            Coche(1, "Camioneta", "Toyota", "Hilux", 5, 5000.0,2000)
        )
    }
}
