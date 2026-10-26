package org.ucb.appp1.log

import com.develoop.logs.LogRequest
import com.develoop.logs.LogResponse
import com.squareup.wire.GrpcCall

expect class LogService(serverUrl: String) {
    fun sendLogs(): GrpcCall<LogRequest, LogResponse>
}
