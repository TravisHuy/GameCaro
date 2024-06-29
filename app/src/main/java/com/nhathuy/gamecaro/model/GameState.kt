package com.nhathuy.gamecaro.model

data class GameState(
    val board: List<List<Char>> = List(15) {
        List(15) {
            ' '
        }},
    val currentPlayer: Char ='X',
    val winner: Char ? =null,
    val gameMode: GameMode= GameMode.PVP,
    val player1Name: String ="Player 1",
    val player2Name: String ="Player 2"
)
