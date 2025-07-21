package com.example.sevenwindstest.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

private val ButtonColor = Color(0xFF342D1A)
private val ButtonTextColor = Color(0xFFEEDDC9)
private val buttonTextColor = Color(0xFF886947)
private val elevatedCardBackground = Color(0xFFF4E3CF)
private val textColor = Color(0xFF83623F)

@Composable
fun AppButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = ButtonColor,
            contentColor = ButtonTextColor
        )
    ) {
        content()
    }
}

@Composable
fun AppOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label, color = buttonTextColor) },
        textStyle = androidx.compose.ui.text.TextStyle(color = buttonTextColor),
        modifier = modifier,
        enabled = enabled,
        visualTransformation = visualTransformation
    )
}

@Composable
fun AppElevatedCard(
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = elevatedCardBackground
        )
    ) {
        content()
    }
}

@Composable
fun AppText(
    text: String,
    style: TextStyle,
    color: Color = textColor
) {
    Text(
        text = text,
        style = style.copy(color = color)
    )
}

@Composable
fun AppIcon(
    icon: ImageVector,
    contentDescription: String
) {
    Icon(
        icon,
        contentDescription,
        tint = Color(0xFF83623F)
    )
}

