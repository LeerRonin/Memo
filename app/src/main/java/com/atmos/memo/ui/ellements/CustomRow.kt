package com.atmos.memo.ui.ellements

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.atmos.memo.R
import com.atmos.memo.ui.theme.MemoTheme
import com.atmos.memo.utils.MyPreview

@Composable
fun CustomRow(
    title: String,
    iconResource: Int,
    buttonText: String,
    buttonAction: () -> Unit
) {
    MemoTheme {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .clip(shape = RoundedCornerShape(40.dp))
                .border(2.dp, colorScheme.secondary, shape = RoundedCornerShape(40.dp))
                .padding(10.dp)
        ) {
            Text(
                text = title,
                textAlign = TextAlign.Start,
                fontSize = 40.sp,
                color = colorScheme.primary,
                modifier = Modifier.fillMaxWidth(0.7f)
            )
            TextButton(
                onClick = buttonAction,
                modifier = Modifier.size(80.dp).fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(iconResource),
                    contentDescription = buttonText,
                    colorFilter = ColorFilter.tint(colorScheme.primary)
                )
            }
        }
    }
}

@Preview
@Composable
fun MyScreenPreview() {
    MyPreview(screenContent = { navController ->
        CustomRow("Test", R.drawable.baseline_home_24, "Description") {

        }
    })
}