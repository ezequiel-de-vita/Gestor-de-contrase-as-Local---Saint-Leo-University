package com.example.passwordmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.passwordmanager.data.*
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val db = DatabaseProvider.getDatabase(this)
        val passwordDao = db.passwordDao()
        val userDao = db.userDao()

        setContent {

            var loggedIn by remember { mutableStateOf(false) }

            if (!loggedIn) {

                LoginScreen(userDao) {
                    loggedIn = true
                }

            } else {

                PasswordScreen(passwordDao, lifecycleScope)
            }
        }
    }
}


@Composable
fun LoginScreen(userDao: UserDao, onLogin: () -> Unit) {

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {

            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Text(
                    text = "Password Manager",
                    style = MaterialTheme.typography.headlineMedium
                )

                Text(
                    text = "Login or create an account",
                    style = MaterialTheme.typography.bodyMedium
                )

                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Username") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {

                        scope.launch {

                            val user = userDao.login(username, password)

                            if (user != null) {
                                onLogin()
                            } else {
                                message = "Invalid login"
                            }
                        }

                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Login")
                }

                OutlinedButton(
                    onClick = {

                        scope.launch {

                            val exists = userDao.findUser(username)

                            if (exists == null) {

                                userDao.insert(
                                    UserEntity(
                                        username = username,
                                        password = password
                                    )
                                )

                                message = "User created"

                            } else {
                                message = "User already exists"
                            }
                        }

                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Create Account")
                }

                Text(
                    text = message,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun PasswordScreen(dao: PasswordDao, lifecycleScope: androidx.lifecycle.LifecycleCoroutineScope) {

    var site by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var list by remember { mutableStateOf(listOf<PasswordEntity>()) }

    fun load() {
        lifecycleScope.launch {
            list = dao.getAll()
        }
    }

    LaunchedEffect(Unit) { load() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            "Your Passwords",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            elevation = CardDefaults.cardElevation(8.dp)
        ) {

            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                OutlinedTextField(
                    value = site,
                    onValueChange = { site = it },
                    label = { Text("Site") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Username") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {

                        lifecycleScope.launch {

                            val encrypted = CryptoManager.encrypt(password)

                            dao.insert(
                                PasswordEntity(
                                    site = site,
                                    username = username,
                                    password = encrypted
                                )
                            )

                            site = ""
                            username = ""
                            password = ""

                            load()
                        }

                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Save Password")
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {

            items(list) { item ->

                val decryptedPassword = try {
                    CryptoManager.decrypt(item.password)
                } catch (e: Exception) {
                    "ERROR"
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text(
                            text = "Site: ${item.site}",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(
                            text = "User: ${item.username}"
                        )

                        Text(
                            text = "Password: $decryptedPassword"
                        )

                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )

                        Button(
                            onClick = {
                                lifecycleScope.launch {
                                    dao.delete(item)
                                    load()
                                }
                            }
                        ) {
                            Text("Delete")
                        }
                    }
                }
            }

        }
    }
}