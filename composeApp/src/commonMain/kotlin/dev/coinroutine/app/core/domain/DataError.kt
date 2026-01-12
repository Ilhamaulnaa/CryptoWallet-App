package dev.coinroutine.app.core.domain

import dev.coinroutine.app.core.domain.Result.Error

sealed interface DataError : dev.coinroutine.app.core.domain.Error {

    enum class Remote: DataError {
        REQUEST_TIMEOUT,
        TOO_MANY_REQUEST,
        NO_INTERNET,
        SERVER,
        SERIALIZATION,
        UNKNOWN
    }

    enum class LOCAL: DataError {
        DISK_FULL,
        INSUFFICIENT_FUNDS,
        UNKNOWN
    }

}