package com.example.hnefatafl.context

object RequestContext {
    private val requestIdHolder = ThreadLocal<String>()
    @JvmStatic
    var clientId: String
        get() = requestIdHolder.get()
        set(clientId) {
            requestIdHolder.set(clientId)
        }

    @JvmStatic
    fun clear() {
        requestIdHolder.remove()
    }
}