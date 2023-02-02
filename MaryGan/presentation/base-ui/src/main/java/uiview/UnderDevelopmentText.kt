package uiview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.base_ui.theme.AppTheme
import com.example.presentation.base_ui.R

@Composable
fun UnderDevelopmentText() {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.base_ui_under_develop),
            style = AppTheme.typography.h2,
            color = AppTheme.colors.systemTextPrimary
        )
    }
}

@Preview
@Composable
private fun UnderDevelopmentPreview() {
    UnderDevelopmentText()
}
