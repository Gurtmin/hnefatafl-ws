package com.example.hnefatafl.log

import org.slf4j.LoggerFactory

abstract class BaseLoggable {
    @kotlin.jvm.JvmField
    protected val logger = LoggerFactory.getLogger(javaClass)
}