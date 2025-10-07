package com.example.application_1.fragment_1

import android.os.Bundle
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.application_1.MainActivity

class FirstFragment : Fragment() {

    // initialisation ViewModel
    private val viewModel: FirstFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                FirstFragmentCompose(viewModel = viewModel)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.navigationEvent.observe(viewLifecycleOwner) { shouldNavigate ->
            if (shouldNavigate) {
                (requireActivity() as? MainActivity)?.showSecondFragment()
                viewModel.navigationHandled()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstFragmentCompose(viewModel: FirstFragmentViewModel) {

    // Collect state from ViewModel
    val showError by viewModel.showError.observeAsState(initial = false)
    val loginState by viewModel.loginState.observeAsState(initial = "")
    val passwordState by viewModel.passwordState.observeAsState(initial = "")

    // Local state for text fields (optional - can use ViewModel state directly)
    var login by remember { mutableStateOf(loginState) }
    var password by remember { mutableStateOf(passwordState) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Your are in the screen one",
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6750A4),
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        LoginScreenContent(
            modifier = Modifier.padding(innerPadding),
            login = login,
            password = password,
            showError = showError,
            onLoginChange = { newLogin ->
                login = newLogin
                viewModel.updateLogin(newLogin)
            },
            onPasswordChange = { newPassword ->
                password = newPassword
                viewModel.updatePassword(newPassword)
            },
            onLoginClick = {
                viewModel.validateCredentials(login, password)
            }
        )
    }
}

@Composable
fun LoginScreenContent(
    modifier: Modifier = Modifier,
    login: String,
    password: String,
    showError: Boolean,
    onLoginChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Entrance in the app",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = login,
            onValueChange = onLoginChange,
            label = { Text("Login") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            isError = showError
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            isError = showError
        )

        if (showError) {
            Text(
                text = "Wrong login or password!",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onLoginClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6750A4),
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text("Go!")
        }
    }
}