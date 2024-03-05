package com.atmos.memo.ui.ellements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.atmos.memo.R

@Composable
fun TerminDescriptionColumn(
    leftTextFieldState: MutableState<TextFieldValue>,
    rightTextFieldState: MutableState<TextFieldValue>,
    onDelete: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextField(
            value = leftTextFieldState.value,
            onValueChange = { leftTextFieldState.value = it },
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        )

        TextButton(
            onClick = onDelete,
            modifier = Modifier.size(80.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.baseline_close_24),
                contentDescription = "Delete",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
            )
        }

        TextField(
            value = rightTextFieldState.value,
            onValueChange = { rightTextFieldState.value = it },
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        )
    }
}