package com.cblanco.rickandmortychars.features.characters.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.cblanco.rickandmortychars.core.presentation.theme.Purple500
import com.cblanco.rickandmortychars.features.characters.list.domain.model.CharacterItemModel
import com.cblanco.rickandmortychars.features.characters.list.ui.ProfileContent
import com.cblanco.rickandmortychars.features.characters.list.ui.ProfilePicture
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.result.ResultBackNavigator

@Destination
@Composable
fun Detail(
    character: CharacterItemModel, resultNavigator: ResultBackNavigator<String>
) {
    Column(modifier = Modifier.fillMaxSize()) {
        AppBar(
            title = "${character.name}",
            imageVector = Icons.Filled.ArrowBack,
        ) {
            resultNavigator.navigateBack()
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfilePicture(character, 200.dp)
            ProfileContent(character, Alignment.CenterHorizontally)
            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
            Divider(
                modifier = Modifier
                    .width(140.dp)
                    .height(1.dp)
                    .background(color = Color.Gray)
            )
            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
            Text(
                text = character.gender ?: "",
                style = MaterialTheme.typography.body2,
            )
            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
            Divider(
                modifier = Modifier
                    .width(140.dp)
                    .height(1.dp)
                    .background(color = Color.Gray)
            )
            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
            Text(
                text = character.locationName ?: "",
                style = MaterialTheme.typography.body2,
            )
        }
    }

}

@Composable
fun AppBar(title: String, imageVector: ImageVector, iconClickAction: () -> Unit) {
    TopAppBar(title = {
        Text(
            text = title, color = Color.White
        )
    }, navigationIcon = {
        IconButton(onClick = { iconClickAction.invoke() }) {
            Icon(imageVector = imageVector, contentDescription = "", tint = Color.White)
        }
    }, backgroundColor = Purple500
    )
}