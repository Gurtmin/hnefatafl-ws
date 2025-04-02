package com.example.hnefatafl.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseLoggable {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
}