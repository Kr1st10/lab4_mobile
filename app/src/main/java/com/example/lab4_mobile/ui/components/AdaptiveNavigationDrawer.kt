package com.example.lab4_mobile.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.lab4_mobile.R
import com.example.lab4_mobile.data.model.PlaceCategory

@Composable
fun AdaptiveNavigationDrawer(
    usePermanentDrawer: Boolean,
    drawerState: DrawerState,
    onCategoryClick: (String) -> Unit,
    onAboutClick: () -> Unit,
    onSettingsClick: () -> Unit,
    content: @Composable () -> Unit
) {
    val drawerContent = @Composable {
        NavigationDrawerContent(
            onCategoryClick = onCategoryClick,
            onAboutClick = onAboutClick,
            onSettingsClick = onSettingsClick
        )
    }

    if (usePermanentDrawer) {
        PermanentNavigationDrawer(
            drawerContent = {
                PermanentDrawerSheet {
                    drawerContent()
                }
            },
            content = content
        )
    } else {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    drawerContent()
                }
            },
            content = content
        )
    }
}

@Composable
private fun NavigationDrawerContent(
    onCategoryClick: (String) -> Unit,
    onAboutClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    Column {
        Column(modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))) {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineSmall
            )
        }
        PlaceCategory.entries.forEach { category ->
            NavigationDrawerItem(
                label = { Text(stringResource(category.titleResId)) },
                selected = false,
                onClick = { onCategoryClick(category.name) },
                icon = { Icon(category.icon, contentDescription = null) }
            )
        }
        NavigationDrawerItem(
            label = { Text(stringResource(R.string.about)) },
            selected = false,
            onClick = onAboutClick,
            icon = { Icon(Icons.Default.Info, contentDescription = null) }
        )
        NavigationDrawerItem(
            label = { Text(stringResource(R.string.settings)) },
            selected = false,
            onClick = onSettingsClick,
            icon = { Icon(Icons.Default.Settings, contentDescription = null) }
        )
    }
}