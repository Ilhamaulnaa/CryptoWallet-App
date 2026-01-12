package dev.coinroutine.app.core.util

import coinroutine.composeapp.generated.resources.Res
import coinroutine.composeapp.generated.resources.error_disk_full
import coinroutine.composeapp.generated.resources.error_insufficient_belance
import coinroutine.composeapp.generated.resources.error_no_internet
import coinroutine.composeapp.generated.resources.error_request_timeout
import coinroutine.composeapp.generated.resources.error_serialization
import coinroutine.composeapp.generated.resources.error_to_many_requests
import coinroutine.composeapp.generated.resources.error_unknown
import dev.coinroutine.app.core.domain.DataError
import org.jetbrains.compose.resources.StringResource

fun DataError.toUiText() : StringResource{

    val stringResource = when(this) {
        DataError.LOCAL.UNKNOWN -> Res.string.error_unknown
        DataError.LOCAL.DISK_FULL -> Res.string.error_disk_full
        DataError.LOCAL.INSUFFICIENT_FUNDS -> Res.string.error_insufficient_belance
        DataError.Remote.REQUEST_TIMEOUT -> Res.string.error_request_timeout
        DataError.Remote.TOO_MANY_REQUEST -> Res.string.error_to_many_requests
        DataError.Remote.SERIALIZATION -> Res.string.error_serialization
        DataError.Remote.SERVER -> Res.string.error_unknown
        DataError.Remote.NO_INTERNET -> Res.string.error_no_internet
        DataError.Remote.UNKNOWN -> Res.string.error_unknown
    }

    return stringResource

}