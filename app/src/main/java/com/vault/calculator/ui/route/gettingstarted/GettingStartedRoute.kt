package com.vault.calculator.ui.route.gettingstarted

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.vault.calculator.R
import com.vault.calculator.ui.theme.AppDimensions
import com.vault.calculator.ui.theme.CalculatorTheme

@Composable
fun GettingStartedRoute(
    navHostController: NavHostController,
    viewModel: GettingStartedViewModel = hiltViewModel()
) {
    Scaffold { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(modifier = Modifier.fillMaxSize()) {

                }
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Text("Getting started screen")
    }
}

@Composable
fun OnboardingScreen(
    onGetStarted: () -> Unit
) {
    val typography = MaterialTheme.typography
    val colors = MaterialTheme.colorScheme

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(48.dp))

        // Top illustration
        Image(
            painter = painterResource(id = R.drawable.il_getting_started), // your SVG or PNG
            contentDescription = "Vault Illustration",
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .aspectRatio(1f)
        )

        // Title
        Text(
            text = "Calculator Vault",
            style = typography.titleLarge.copy(
                color = colors.onBackground
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(AppDimensions.marginMedium)
        )

        // Subtitle
        Text(
            text = "Hide your important files here!",
            style = typography.bodyMedium.copy(
                color = colors.onBackground.copy(alpha = 0.8f)
            ),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.weight(1f))
        // Get Started button
        Button(
            onClick = onGetStarted,
            modifier = Modifier
                .padding(vertical = AppDimensions.marginLarge),
            colors = ButtonDefaults.buttonColors(
                containerColor = colors.primary,
                contentColor = colors.onPrimary
            ),
            shape = RoundedCornerShape(50)
        ) {
            Text(
                text = "Get started",
                style = typography.labelLarge,
                modifier = Modifier.padding(horizontal = 40.dp)
            )
        }

        // Terms & Privacy text
        Text(
            text = buildAnnotatedString {
                append("By selecting “Get started” above, I have reviewed and agree to the ")
                pushStringAnnotation(tag = "terms", annotation = "terms")
                withStyle(style = SpanStyle(color = colors.primary)) {
                    append("Terms of Use")
                }
                pop()
                append(" and acknowledge the ")
                pushStringAnnotation(tag = "privacy", annotation = "privacy")
                withStyle(style = SpanStyle(color = colors.primary)) {
                    append("Privacy")
                }
                pop()
            },
            style = typography.labelSmall.copy(
                color = colors.onBackground.copy(alpha = 0.7f)
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 24.dp)
        )
    }
}

@Preview
@Composable
fun GettingStartedPreview() {
    CalculatorTheme {
        OnboardingScreen { }
    }
}