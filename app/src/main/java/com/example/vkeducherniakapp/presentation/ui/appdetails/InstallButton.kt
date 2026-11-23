package com.example.vkeducherniakapp.presentation.ui.appdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vkeducherniakapp.R
import com.example.vkeducherniakapp.presentation.ui.theme.VkEduCherniakAppTheme

@Composable
fun InstallButton(
    installState: InstallState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    val buttonText = when (installState) {
        is InstallState.NotInstalled -> stringResource(R.string.install)
        is InstallState.Installing -> stringResource(R.string.cancel, installState.progress)
        is InstallState.Installed -> stringResource(R.string.installed)
    }

    Button(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(vertical = 12.dp),
        modifier = modifier,
    ) {
        Text(text = buttonText)
    }
}

@Preview
@Composable
private fun Preview() {
    VkEduCherniakAppTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            InstallButton(
                installState = InstallState.NotInstalled,
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(8.dp))
            InstallButton(
                installState = InstallState.Installing(45),
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(8.dp))
            InstallButton(
                installState = InstallState.Installed,
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}