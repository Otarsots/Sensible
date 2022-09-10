package com.example.sensible.ui.product.editor

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.sensible.R
import com.example.sensible.data.repository.ProductRepository
import com.example.sensible.ui.components.SensibleTopBar
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun ProductEditor(
    productId: Long,
    popBackStack: () -> Unit,
    viewModel: ProductEditorViewModel = getViewModel { parametersOf(productId) }
) {
    Scaffold(
        topBar = {
            SensibleTopBar(
                navigationIcon = {
                    IconButton(onClick = popBackStack) {
                        Icon(Icons.Default.ArrowBack, stringResource(R.string.btn_pop_back))
                    }
                },
                title = stringResource(R.string.product_editor_name),
            )
        }
    ) { padding ->
        Text(viewModel.productName(), Modifier.padding(padding))
    }
}
