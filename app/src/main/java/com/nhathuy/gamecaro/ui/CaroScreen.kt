package com.nhathuy.gamecaro.ui

import android.widget.Button
import android.widget.Space
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.nhathuy.gamecaro.R
import com.nhathuy.gamecaro.model.GameMode
import com.nhathuy.gamecaro.model.GameState
import com.nhathuy.gamecaro.viewmodel.CaroViewModel

@Composable
fun CaroScreen(viewModel: CaroViewModel){

    val gameState by viewModel.gameState.collectAsState()
    val showPlayerNameDialog by viewModel.showPlayerNameDialog.collectAsState()

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFF0EAD6))) {

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
            GameTitle()
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.medium)))
            CurrentPlayerText(gameState)
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.medium)))
            CaroBoard(gameState,viewModel)
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.medium)))
            gameState.winner?.let {
                WinnerText(it,gameState)
            }
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.medium)))
            CaroButton(viewModel)
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.medium)))
            ResetCaroButton(viewModel)
        }
    }
    if(showPlayerNameDialog){
        PlayerNameDialog(onDimiss= {viewModel.dimissPlayerDialog()},
                onConfirm= { player1Name, player2Name -> viewModel.setPlayerNames(player1Name,player2Name)})
    }
}

@Composable
fun PlayerNameDialog(onDimiss: () -> Unit, onConfirm: (String,String) -> Unit) {

}

@Composable
fun ResetCaroButton(viewModel: CaroViewModel) {

}

@Composable
fun CaroButton(viewModel: CaroViewModel) {
    Row {
        Button(
            onClick = { viewModel.setGameMode(GameMode.PVP) },
            colors = ButtonDefaults.buttonColors(Color(0xFF4CAF50))
        ) {
            Text("PVP Mode", color = Color.White)
        }
        Spacer(modifier = Modifier.width(16.dp))
        Button(
            onClick = { viewModel.setGameMode(GameMode.AI) },
            colors = ButtonDefaults.buttonColors(Color(0xFF4CAF50))
        ) {
            Text("AI Mode", color = Color.White)
        }
    }
}

@Composable
fun WinnerText(winner: Char, gameState: GameState) {
    Text(text = "Winner: ${if (winner=='X') gameState.player1Name else gameState.player2Name }",
    fontSize = 20.sp,
    fontWeight = FontWeight.Bold,
    color = Color(0xFF1A237E)
    )
}



@Composable
fun CurrentPlayerText(gameState: GameState) {
    Text(
        "Current Player: ${if (gameState.currentPlayer == 'X') gameState.player1Name else gameState.player2Name}",
        fontSize = 18.sp,
        color = Color(0xFF03A9F4)
    )
}

@Composable
fun GameTitle() {
    Text(text = stringResource(id = R.string.nameapp), fontSize = 30.sp,
    fontWeight = FontWeight.Bold, color = Color(0xFF2B77B4)
    )
}
