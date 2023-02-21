package com.cblanco.rickandmortychars.features.characters.list.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.cblanco.rickandmortychars.R
import com.cblanco.rickandmortychars.core.presentation.composables.EventProcessor
import com.cblanco.rickandmortychars.core.presentation.theme.Purple500
import com.cblanco.rickandmortychars.core.presentation.theme.lightGreen
import com.cblanco.rickandmortychars.destinations.DetailDestination
import com.cblanco.rickandmortychars.destinations.SimpleDialogDestination
import com.cblanco.rickandmortychars.features.DefaultNavGraph
import com.cblanco.rickandmortychars.features.characters.list.domain.model.CharacterItemModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@DefaultNavGraph(
    start = true
)
@Destination
@Composable
fun CharacterList(
    viewModel: CharacterListViewModel = hiltViewModel(), navigator: DestinationsNavigator
) {
    LaunchedEffect(Unit){
        viewModel.sendIntent(CharacterListIntent.FetchData)
    }

    val state = viewModel.viewState


    Column() {
        AppBar(
            title = stringResource(id = R.string.character_list_view_title),
        )
        CharacterListContent(
            loading = state.loading,
            characterList = state.characterList,
            onGetData = {
                viewModel.sendIntent(CharacterListIntent.ClickCharacter(it))
            })
    }


    EventProcessor(viewModel) { event ->
        when (event) {
            is CharacterListEvent.Error -> navigator.navigate(SimpleDialogDestination(event.message))
            is CharacterListEvent.ErrorKey -> {
                navigator.navigate(SimpleDialogDestination(message = stringResource(event.idReference)))
            }
            is CharacterListEvent.NavigateToDetail -> navigator.navigate(DetailDestination(character = event.character))
            else -> {

            }
        }
    }

}

@Composable
fun CharacterListContent(
    loading: Boolean,
    characterList: List<CharacterItemModel>,
    onGetData: (CharacterItemModel) -> Unit
) {
    Row(modifier = Modifier.padding(8.dp)) {

        AnimatedVisibility(!loading) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(characterList) { character ->
                    ProfileCard(character) { characterClicked ->
                        onGetData(characterClicked)
                    }
                }
            }
        }
        AnimatedVisibility(loading) {
            Box(
                contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun AppBar(title: String) {
    TopAppBar(title = {
        Text(
            text = title, color = Color.White
        )
    }, backgroundColor = Purple500)
}

@Composable
fun ProfileCard(
    character: CharacterItemModel, clickAction: (CharacterItemModel) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top)
            .clickable { clickAction(character) },
        elevation = 8.dp,
        backgroundColor = Color.White,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            ProfilePicture(character, 70.dp)
            ProfileContent(character, Alignment.Start)
        }
    }
}

@Composable
fun ProfilePicture(character: CharacterItemModel, profilePicSize: Dp) {
    Card(
        shape = CircleShape, border = BorderStroke(
            width = 1.dp, color = MaterialTheme.colors.lightGreen
        ), modifier = Modifier.padding(16.dp), elevation = 4.dp
    ) {

        AsyncImage(
            model = character.image,
            contentDescription = "",
            modifier = Modifier.size(profilePicSize),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun ProfileContent(character: CharacterItemModel, alignment: Alignment.Horizontal) {
    Column(
        modifier = Modifier.padding(8.dp), horizontalAlignment = alignment
    ) {
        CompositionLocalProvider(
            LocalContentAlpha provides 1f
        ) {
            Text(text = character.name ?: "", style = MaterialTheme.typography.h6)
        }
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = character.species ?: "",
                style = MaterialTheme.typography.body2,
            )
        }
    }
}