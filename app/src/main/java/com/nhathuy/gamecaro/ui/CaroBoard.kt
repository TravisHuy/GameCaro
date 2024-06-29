package com.nhathuy.gamecaro.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.nhathuy.gamecaro.model.GameState
import com.nhathuy.gamecaro.viewmodel.CaroViewModel

@Composable
fun CaroBoard(gameState: GameState, viewModel: CaroViewModel) {
    Box(modifier = Modifier
        .aspectRatio(1f)
        .background(Color.White, RoundedCornerShape(8.dp))
        .padding(8.dp)) {
        Canvas(modifier = Modifier.fillMaxSize()){
            val cellSize = size.width/ 15

            for (i in 0..15){
                drawLine(
                    Color(0xFF4CAF50),
                    start = Offset(i*cellSize,0f),
                    end = Offset(i*cellSize,size.height),
                    strokeWidth = 1f
                )
                drawLine(
                    Color(0xFF4CAF50),
                    start = Offset(0f,i*cellSize),
                    end = Offset(size.width,i*cellSize),
                    strokeWidth = 1f
                )
            }
            gameState.board.forEachIndexed { rowIndex, row ->
                row.forEachIndexed { colIndex, cell ->
                    val center = Offset(
                        (colIndex+0.5f)*cellSize,
                        (rowIndex+0.5f)*cellSize
                    )
                    when(cell){
                        'X' ->{
                            drawLine(Color.Blue, center - Offset(cellSize * 0.3f, cellSize * 0.3f),
                                center + Offset(cellSize * 0.3f, cellSize * 0.3f), strokeWidth = 3f)
                            drawLine(Color.Blue, center + Offset(cellSize * 0.3f, -cellSize * 0.3f),
                                center - Offset(cellSize * 0.3f, -cellSize * 0.3f), strokeWidth = 3f)
                        }
                        'O' -> {
                            drawCircle(Color.Red, radius = cellSize * 0.3f, center = center, style = Stroke(3f))
                        }
                    }
                }
            }
        }
        ClickableOverlay(viewModel)
    }
}

@Composable
fun ClickableOverlay(viewModel: CaroViewModel) {
    Box(modifier = Modifier.fillMaxSize()){
        for (row in 0 until 15){
            for(col in 0 until 15){
                Box(modifier = Modifier.fillMaxSize().aspectRatio(1f).offset(x=(col*(1f/15)).dp,y=(row*(1f/15)).dp).size((1f/15).dp).clickable {
                    viewModel.makeMove(row,col)
                })
            }
        }
    }
}

