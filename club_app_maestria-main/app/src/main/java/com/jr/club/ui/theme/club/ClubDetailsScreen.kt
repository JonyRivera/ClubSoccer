package com.juanrolando.coche.ui.theme.coche

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.juanrolando.coche.CocheTopAppBar
import com.juanrolando.coche.R
import com.juanrolando.coche.data.Coche
import com.juanrolando.coche.ui.theme.AppViewModelProvider
import com.juanrolando.coche.ui.theme.navigation.NavigationDestination
import com.juanrolando.coche.ui.theme.CocheTheme
import androidx.compose.ui.Alignment
import coil.compose.rememberImagePainter

object ClubDetailsDestination : NavigationDestination {
    override val route = "club_details"
    override val titleRes = R.string.clubDetailTitle
    const val clubIdArg = "clubId"
    val routeWithArgs = "$route/{$clubIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClubDetailsScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ClubDetailsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState = viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            CocheTopAppBar(
                title = stringResource(ClubDetailsDestination.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        },  modifier = modifier
    ) { innerPadding ->
        CocheDetailsBody(
            cocheDetailsUiState = uiState.value,
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        )
    }
}

@Composable
private fun CocheDetailsBody(
    cocheDetailsUiState: ClubDetailsUiState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ) {

        CocheDetails(
            coche = cocheDetailsUiState.cocheDetails.toCoche(), modifier = Modifier.fillMaxWidth()
        )
    }
}


@Composable
fun CocheDetails(
    coche: Coche, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier, colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_medium)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
        ) {

           Image(
                //painter = painterResource(id =  R.drawable.honda),
               painter = rememberImagePainter(
                   data = coche.imagen,
                   builder = {

                   }
               ),
                contentDescription = null, // Descripción opcional para accesibilidad
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterHorizontally), // Centrar horizontalmente

                contentScale = ContentScale.Crop
            )

            CocheDetailsRow(
                labelResID = R.string.descripcionClub,
                cocheDetail = coche.name,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(
                        id = R.dimen
                            .padding_medium
                    )
                )
            )
            CocheDetailsRow(
                labelResID = R.string.paisClub,
                cocheDetail = coche.country,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(
                        id = R.dimen
                            .padding_medium
                    )
                )
            )

            CocheDetailsRow(
                labelResID = R.string.modelo_coche,
                cocheDetail = coche.city,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(
                        id = R.dimen
                            .padding_medium
                    )
                )
            )

            CocheDetailsRow(
                labelResID = R.string.yearFundation,
                cocheDetail = coche.yearFundation.toString(),
                modifier = Modifier.padding(
                    horizontal = dimensionResource(
                        id = R.dimen
                            .padding_medium
                    )
                )
            )

            CocheDetailsRow(
                labelResID = R.string.costPlayers,
                cocheDetail = coche.formatedPrice(),
                modifier = Modifier.padding(
                    horizontal = dimensionResource(
                        id = R.dimen
                            .padding_medium
                    )
                )
            )


        }

    }
}



@Composable
private fun CocheDetailsRow(
    @StringRes labelResID: Int, cocheDetail: String, modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(text = stringResource(labelResID))
        Spacer(modifier = Modifier.weight(1f))
        Text(text = cocheDetail, fontWeight = FontWeight.Bold)
    }
}
@Preview(showBackground = true)
@Composable
fun CocheDetailsScreenPreview() {
    CocheTheme {
        CocheDetailsBody(ClubDetailsUiState(
            outOfStock = true, cocheDetails = CocheDetails(1, "Camioneta", "Toyota", "Hilux", 5, "5000", 2000)
        ))
    }
}
