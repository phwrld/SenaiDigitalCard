package com.pdrinyo.thesenaidigitalcard.core.auth

/**
 * Mantem o token apenas durante a sessao ativa, como no app de referencia.
 * Nunca persiste uma senha nem um token no repositorio.
 */
class SessionTokenStore {
    @Volatile
    private var token: String? = null

    fun salvar(novoToken: String?) {
        token = novoToken?.takeIf { it.isNotBlank() }
    }

    fun obter(): String? = token

    fun limpar() {
        token = null
    }
}
