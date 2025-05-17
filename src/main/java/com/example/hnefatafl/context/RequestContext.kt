package com.example.hnefatafl.context

object RequestContext {
    private val requestIdHolder = ThreadLocal<String>()
    private val requestCorrelationIdHolder = ThreadLocal<String>()
    @JvmStatic
    var clientId: String
        get() = requestIdHolder.get()
        set(clientId) {
            requestIdHolder.set(clientId)
        }

    @JvmStatic
    var correlationId: String
        get() = requestCorrelationIdHolder.get()
        set(correlationId) {
            requestCorrelationIdHolder.set(correlationId)
        }

    @JvmStatic
    fun clear() {
        requestIdHolder.remove()
        requestCorrelationIdHolder.remove()
    }
}