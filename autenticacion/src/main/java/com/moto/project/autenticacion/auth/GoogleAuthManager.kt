package com.moto.project.autenticacion.auth

import com.google.android.gms.auth.api.identity.GoogleSignIn
import com.google.android.gms.auth.api.identity.GoogleSignInClient
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GoogleAuthManager @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val googleSignInClient: GoogleSignInClient
) {
    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: Flow<AuthState> = _authState

    suspend fun signInWithGoogle(idToken: String): Result<Unit> {
        return try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            firebaseAuth.signInWithCredential(credential).await()
            _authState.value = AuthState.Authenticated
            Result.success(Unit)
        } catch (e: Exception) {
            _authState.value = AuthState.Error(e.message ?: "Unknown error")
            Result.failure(e)
        }
    }

    suspend fun signOut() {
        firebaseAuth.signOut()
        googleSignInClient.signOut().await()
        _authState.value = AuthState.Idle
    }

    fun getCurrentUser(): com.google.firebase.auth.FirebaseUser? = firebaseAuth.currentUser
}

enum class AuthState {
    Idle,
    Authenticated,
    Error(String? = null),
    Loading
}
