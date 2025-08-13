package com.vault.calculator.ui.route.maincalculator

import android.annotation.SuppressLint
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.vault.calculator.R
import com.vault.calculator.ui.Screen
import com.vault.calculator.ui.theme.AppDimensions
import com.vault.calculator.ui.theme.CalculatorTheme
import javax.inject.Inject

@Composable
fun MainCalculatorRoute(
    navHostController: NavHostController,
    viewModel: MainCalculatorViewModel = hiltViewModel()
) {
    val uiState by viewModel.stateFlow.collectAsState()
    LaunchedEffect(uiState.navigateToPrivateScreen) {
        if (uiState.navigateToPrivateScreen) {
            navHostController.navigate(Screen.MainVault.route)
        }
    }

    Scaffold { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            CalculatorContent(
                uiState = uiState,
                onPinSetup = viewModel::onSetPin,
                onLoginTry = viewModel::onLoginTry,
            )
        }
    }
}

const val KEY_PIN = "userPin"

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun CalculatorContent(
    uiState: MainCalculatorViewModel.UiState,
    onPinSetup: (String) -> Unit,
    onLoginTry: (String) -> Unit
) {
    val mode = uiState.mode
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        var calculatorText by remember { mutableStateOf("") }
        if (mode == MainCalculatorViewModel.Mode.SETUP) {
            val confirmingPin = uiState.firstTimeEnteredPin != null
            val textToDisplay = if (confirmingPin) {
                stringResource(R.string.confirmSetupYourPin)
            } else {
                stringResource(R.string.setupYourPin)
            }
            Text(
                text = textToDisplay,
                style = MaterialTheme.typography.bodyLarge
                    .copy(color = MaterialTheme.colorScheme.onSurfaceVariant),
                modifier = Modifier.padding(20.dp)
            )
        }
        Spacer(Modifier.weight(1f))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = calculatorText,
                style = MaterialTheme.typography.displayLarge
                    .copy(color = MaterialTheme.colorScheme.onBackground)
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
                                onClick = { calculatorText += i.toString() },
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
                            onClick = { calculatorText = calculatorText.dropLast(1) },
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
                                onClick = { calculatorText += i.toString() },
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
                            onClick = { calculatorText += Operator.Minus.icon },
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
                                onClick = { calculatorText += i.toString() },
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
                            onClick = { calculatorText += Operator.Plus.icon },
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
                            onClick = { calculatorText += "0" },
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
                            onClick = { calculatorText += "." },
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
                            onClick = { calculatorText += Operator.Multiply.icon },
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
                            onClick = { calculatorText += Operator.Divide.icon },
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
                            onClick = {
                                if (mode == MainCalculatorViewModel.Mode.SETUP) {
                                    onPinSetup(calculatorText)
                                    calculatorText = ""
                                } else {
                                    onLoginTry(calculatorText)
                                }
                            },
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

sealed class Operator(val icon: String) {
    object Plus : Operator("+")
    object Minus : Operator("-")
    object Multiply : Operator("x")
    object Divide : Operator("÷")
    object Equal : Operator("=")
}