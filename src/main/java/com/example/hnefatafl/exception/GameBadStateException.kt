package com.example.hnefatafl.exception

import com.example.generated.mongo.Game.State

class GameBadStateException(functionName: String, state: State) : RuntimeException("Can not proceed $functionName [state: $state]")
