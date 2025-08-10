package com.vault.calculator.ui.route.maincalculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.vault.calculator.R
import com.vault.calculator.ui.theme.AppDimensions
import com.vault.calculator.ui.theme.CalculatorTheme

@Composable
fun MainCalculatorRoute(
    navHostController: NavHostController,
    showEnterPassword: Boolean,
    viewModel: MainCalculatorViewModel = hiltViewModel()
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text("Main Calculator screen")
    }
}

const val KEY_SHOW_ENTER_PASSWORD = "showEnterPassword"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorWithPinScreen() {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showPinSheet by remember { mutableStateOf(true) }
    var pin by remember { mutableStateOf("") }
    var reEnterMode by remember { mutableStateOf(false) }

    // Main calculator screen
    Scaffold(
        topBar = { /* Calculator top UI */ }
    ) { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            CalculatorContent()
        }
    }

    // PIN Entry Bottom Sheet
    if (showPinSheet) {
        ModalBottomSheet(
            onDismissRequest = { showPinSheet = false },
            sheetState = sheetState
        ) {
            PinEntryContent(
                pin = pin,
                onPinChange = { pin = it },
                reEnter = reEnterMode,
                onConfirm = {
                    if (!reEnterMode) {
                        reEnterMode = true
                        pin = ""
                    } else {
                        // Save PIN logic here
                        showPinSheet = false
                    }
                }
            )
        }
    }
}

@Composable
fun CalculatorContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colorScheme.background)
    ) {
        val confirmingPin = false
        val textToDisplay = if (confirmingPin) {
            stringResource(R.string.confirmSetupYourPin)
        } else {
            stringResource(R.string.setupYourPin)
        }
        Text(
            text = textToDisplay,
            style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
        )
        Spacer(Modifier.weight(1f))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "value",
                style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.onBackground)
            )
        }
        Box(
            modifier = Modifier
                .padding(AppDimensions.marginLarge)
        ) {
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(AppDimensions.marginSmall)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    for (i in 7..9) {
                        BoxWithConstraints(modifier = Modifier.weight(1f)) {
                            TextButton(
                                onClick = {},
                                shape = CircleShape,
                                colors = ButtonDefaults.textButtonColors(
                                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                    contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                                ),
                                modifier = Modifier.size(maxWidth)
                            ) {
                                Text(
                                    text = i.toString(),
                                    style = MaterialTheme.typography.headlineLarge
                                )
                            }
                        }
                        Spacer(Modifier.width(AppDimensions.marginMedium))

                    }
                    BoxWithConstraints(modifier = Modifier.weight(1f)) {
                        TextButton(
                            onClick = {},
                            shape = CircleShape,
                            colors = ButtonDefaults.textButtonColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                            ),
                            modifier = Modifier.size(maxWidth)
                        ) {
                            Text(
                                text = "Back",
                                style = MaterialTheme.typography.headlineLarge
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    for (i in 4..6) {
                        BoxWithConstraints(modifier = Modifier.weight(1f)) {
                            TextButton(
                                onClick = {},
                                shape = CircleShape,
                                colors = ButtonDefaults.textButtonColors(
                                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                    contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                                ),
                                modifier = Modifier.size(maxWidth)
                            ) {
                                Text(
                                    text = i.toString(),
                                    style = MaterialTheme.typography.headlineLarge
                                )
                            }
                        }
                        Spacer(Modifier.width(AppDimensions.marginMedium))

                    }
                    BoxWithConstraints(modifier = Modifier.weight(1f)) {
                        TextButton(
                            onClick = {},
                            shape = CircleShape,
                            colors = ButtonDefaults.textButtonColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                            ),
                            modifier = Modifier.size(maxWidth)
                        ) {
                            Text(
                                text = Operator.Minus.icon,
                                style = MaterialTheme.typography.headlineLarge
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    for (i in 1..3) {
                        BoxWithConstraints(modifier = Modifier.weight(1f)) {
                            TextButton(
                                onClick = {},
                                shape = CircleShape,
                                colors = ButtonDefaults.textButtonColors(
                                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                    contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                                ),
                                modifier = Modifier.size(maxWidth)
                            ) {
                                Text(
                                    text = i.toString(),
                                    style = MaterialTheme.typography.headlineLarge
                                )
                            }
                        }
                        Spacer(Modifier.width(AppDimensions.marginMedium))

                    }
                    BoxWithConstraints(modifier = Modifier.weight(1f)) {
                        TextButton(
                            onClick = {},
                            shape = CircleShape,
                            colors = ButtonDefaults.textButtonColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                            ),
                            modifier = Modifier.size(maxWidth)
                        ) {
                            Text(
                                text = Operator.Plus.icon,
                                style = MaterialTheme.typography.headlineLarge
                            )
                        }
                    }

                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    BoxWithConstraints(modifier = Modifier.weight(1f)) {
                        TextButton(
                            onClick = {},
                            shape = CircleShape,
                            colors = ButtonDefaults.textButtonColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                            ),
                            modifier = Modifier.size(maxWidth)
                        ) {
                            Text(
                                text = 0.toString(),
                                style = MaterialTheme.typography.headlineLarge
                            )
                        }
                    }
                    Spacer(Modifier.width(AppDimensions.marginMedium))

                    BoxWithConstraints(modifier = Modifier.weight(1f)) {
                        TextButton(
                            onClick = {},
                            shape = CircleShape,
                            colors = ButtonDefaults.textButtonColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                            ),
                            modifier = Modifier.size(maxWidth)
                        ) {
                            Text(
                                text = ".",
                                style = MaterialTheme.typography.headlineLarge
                            )
                        }
                    }
                    Spacer(Modifier.width(AppDimensions.marginMedium))

                    BoxWithConstraints(modifier = Modifier.weight(1f)) {
                        TextButton(
                            onClick = {},
                            shape = CircleShape,
                            colors = ButtonDefaults.textButtonColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                            ),
                            modifier = Modifier.size(maxWidth)
                        ) {
                            Text(
                                text = Operator.Multiply.icon,
                                style = MaterialTheme.typography.headlineLarge
                            )
                        }
                    }
                    Spacer(Modifier.width(AppDimensions.marginMedium))

                    BoxWithConstraints(modifier = Modifier.weight(1f)) {
                        TextButton(
                            onClick = {},
                            shape = CircleShape,
                            colors = ButtonDefaults.textButtonColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                            ),
                            modifier = Modifier.size(maxWidth)
                        ) {
                            Text(
                                text = Operator.Divide.icon,
                                style = MaterialTheme.typography.headlineLarge
                            )
                        }
                    }
                    Spacer(Modifier.width(AppDimensions.marginMedium))

                    BoxWithConstraints(modifier = Modifier.weight(1f)) {
                        TextButton(
                            onClick = {},
                            shape = CircleShape,
                            colors = ButtonDefaults.textButtonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.onPrimary
                            ),
                            modifier = Modifier.size(maxWidth)
                        ) {
                            Text(
                                text = "=",
                                style = MaterialTheme.typography.headlineLarge
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EnterPinComponent(
    modifier: Modifier,
    value: String,
    confirmingPin: Boolean
) {
    Column(
        modifier = modifier
    ) {

    }
}

@Composable
fun PinEntryContent(
    pin: String,
    onPinChange: (String) -> Unit,
    reEnter: Boolean,
    onConfirm: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = if (reEnter) "Please re-enter your pin" else "Please setup your pin",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(Modifier.height(16.dp))

        // Display entered PIN
        Row(horizontalArrangement = Arrangement.Center) {
            repeat(6) { index ->
                Text(
                    text = if (index < pin.length) "*" else "_",
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }

        Spacer(Modifier.height(32.dp))
        // Numeric keypad simulation
        for (row in listOf(
            listOf("7", "8", "9"),
            listOf("4", "5", "6"),
            listOf("1", "2", "3"),
            listOf("0")
        )) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                row.forEach { num ->
                    Button(
                        onClick = {
                            if (pin.length < 6) onPinChange(pin + num)
                        }
                    ) { Text(num) }
                }
            }
        }
        Spacer(Modifier.height(16.dp))
        Button(onClick = onConfirm) { Text("=") }
    }
}

@Preview
@Composable
fun CalcPreview() {
    CalculatorTheme(darkTheme = true) {
        CalculatorContent()
    }
}

sealed class Operator(val icon: String) {
    object Plus : Operator("+")
    object Minus : Operator("-")
    object Multiply : Operator("x")
    object Divide : Operator("/")
    object Equal : Operator("=")
}