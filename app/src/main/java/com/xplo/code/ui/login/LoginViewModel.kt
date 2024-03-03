package com.xplo.code.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xplo.code.core.TestConfig
import com.xplo.code.data_module.core.DispatcherProvider
import com.xplo.code.data_module.core.Resource
import com.xplo.code.data_module.fake.Fake
import com.xplo.code.data_module.model.user.LoginRqb
import com.xplo.code.data_module.repo.UserRepo
import com.xplo.code.ui.login.model.LoginCredentials
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repo: UserRepo,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    companion object {
        private const val TAG = "LoginViewModel"
    }

    sealed class Event {

        object Loading : Event()
        object Empty : Event()

        class LoginSuccess(val token: String, val id: String?) : Event()
        class LoginFailure(val msg: String?) : Event()

    }

    private val _event = MutableStateFlow<Event>(Event.Empty)
    val event: StateFlow<Event> = _event

    fun clearEvent() {
        _event.value = Event.Empty
    }

    fun passwordLogin(credentials: LoginCredentials) {
        Log.d(TAG, "passwordLogin() called with: loginCredentials = $credentials")

        if (TestConfig.isFakeLoginEnabled) {
            _event.value = Event.LoginSuccess(Fake.token, null)
            return
        }

        val body = LoginRqb(credentials.userId, credentials.password)
        viewModelScope.launch(dispatchers.io) {
            _event.value = Event.Loading
            when (val response = repo.passwordLogin(body)) {

                is Resource.Failure -> {
                    _event.value = Event.LoginFailure(response.callInfo?.msg)
                }

                is Resource.Success -> {
                    val token = response.data?.token
                    if (token.isNullOrEmpty()) {
                        _event.value = Event.LoginFailure(response.callInfo?.msg)
                    } else {
                        _event.value = Event.LoginSuccess(token, null)
                    }
                }

                else -> {}
            }
        }


    }


}