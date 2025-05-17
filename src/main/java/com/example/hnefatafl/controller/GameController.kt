package com.example.hnefatafl.controller

import com.example.api.model.*
import com.example.hnefatafl.model.MongoGame
import com.example.hnefatafl.service.GameService
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/games")
class GameController(private val gameService: GameService) {
    @PatchMapping
    fun renameGame(
        @RequestBody request: GameChangeNameRequest
    ): ResponseEntity<MongoGame> {
        val updated = gameService.changeGameName(request.id, request.type)
        return ResponseEntity.ok(updated)
    }

    @PostMapping
    fun createNewGame(@RequestBody request: GameCreateRequest): MongoGame {
        return gameService.createNewGame(request.type)
    }

    @GetMapping
    fun getAllGames(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): ResponseEntity<PagedGameResponse> {
        val response = gameService.getAllGames(PageRequest.of(page, size))
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun getGameById(@PathVariable id: String): ResponseEntity<Game> {
        val game = gameService.getGameById(id)
        return ResponseEntity.ok(game)
    }

    @PostMapping("/{id}/board/select-tile")
    fun selectTile(
        @RequestBody request: SelectTileRequest,
        @PathVariable id: String
    ): ResponseEntity<Game> {
        val game = gameService.selectTile(id, request.x, request.y)
        return ResponseEntity.ok(game)
    }

    @PostMapping("/{id}/join")
    fun joinPlayer(
        @RequestBody request: JoinPlayerRequest?,
        @PathVariable id: String?
    ): ResponseEntity<JoinPlayerResponse> {
        val response = JoinPlayerResponse()
        response.player = gameService.join(id!!, request!!)
        return ResponseEntity.ok(response)
    }
}