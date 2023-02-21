package com.cblanco.rickandmortychars.core.presentation.composables

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.DialogProperties
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.spec.DestinationStyle

object NonDismissableDialog : DestinationStyle.Dialog {
    override val properties = DialogProperties(
        dismissOnClickOutside = false,
        dismissOnBackPress = false,
    )
}

@Destination(
    style = NonDismissableDialog::class
)
@Composable
fun SimpleDialog(
    message: String,
    navigator: DestinationsNavigator
) {

    AlertDialog(
        onDismissRequest = {
        },
        confirmButton = {
            Button(
                onClick = {
                          navigator.navigateUp()
                },
            ) {
                Text("Aceptar")
            }
        },
        text = {
            Text(message)
        },
    )

}