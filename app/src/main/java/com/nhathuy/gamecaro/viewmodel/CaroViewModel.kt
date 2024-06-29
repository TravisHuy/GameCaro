package com.nhathuy.gamecaro.viewmodel

import androidx.lifecycle.ViewModel
import com.nhathuy.gamecaro.model.GameMode
import com.nhathuy.gamecaro.model.GameState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CaroViewModel: ViewModel() {
    private val _gameState= MutableStateFlow(GameState())
    val gameState : StateFlow<GameState> = _gameState.asStateFlow()

    private val _showPlayerNameDialog= MutableStateFlow(false)
    val showPlayerNameDialog:StateFlow<Boolean>  = _showPlayerNameDialog.asStateFlow()

    fun makeMove(row:Int, col:Int){

    }

    fun dimissPlayerDialog() {
        TODO("Not yet implemented")
    }

    fun setPlayerNames(player1Name: String, player2Name: String) {

    }

    fun setGameMode(pvp: GameMode) {

    }
}