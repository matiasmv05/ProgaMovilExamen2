package org.ucb.appp1.log

import com.develoop.logs.LogRequest
import com.develoop.logs.LogResponse
import com.develoop.logs.LogServiceClient
import com.squareup.wire.GrpcCall
import com.squareup.wire.GrpcClient
import okhttp3.OkHttpClient
import okhttp3.Protocol

actual class LogService actual constructor(
    serverUrl: String
) {
    private val httpClient = OkHttpClient.Builder()
        .protocols(listOf(Protocol.HTTP_2))
        .build()

    private val grpcClient = GrpcClient.Builder()
        .client(httpClient)
        .baseUrl(serverUrl)
        .build()

    private val logService =
        grpcClient.create(LogServiceClient::class)

    actual fun sendLogs(): GrpcCall<LogRequest, LogResponse> {
        return logService.send()
    }
}
