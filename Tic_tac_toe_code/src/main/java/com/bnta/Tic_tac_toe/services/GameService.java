package com.bnta.Tic_tac_toe.services;

import com.bnta.Tic_tac_toe.models.*;
import com.bnta.Tic_tac_toe.repositories.CellRepository;
import com.bnta.Tic_tac_toe.repositories.GameRepository;
import com.bnta.Tic_tac_toe.repositories.PlayerRepository;
import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.event.CellEditorListener;
import java.lang.reflect.Array;
import java.util.*;

@Service
public class GameService {
    @Autowired
    GameRepository gameRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    CellRepository cellRepository;

    public List<Game> getAllGames(){
        return gameRepository.findAll();
    }

    public Optional<Game> getGameById(long id){
        return gameRepository.findById(id);
    }

    public void makeCells(int numberOfCells, Game game){
        // initialising
        for (int i = 0; i < numberOfCells; i++) {
            Cell cell = new Cell(Value.EMPTY);
            cell.setCellNumber(i+1);
            cell.setGame(game);
            cellRepository.save(cell);
            game.addCell(cell);
        }
    }

    @Transactional
    public Game startNewGame(long playerId) {
        Player player = playerRepository.findById(playerId).get();
        Game game = new Game(player);
        gameRepository.save(game);

        makeCells(9, game);
        gameRepository.save(game);
        return game;
    }

    public void deleteGameById(long gameId){
        Game game = gameRepository.findById(gameId).get();
        for (Cell cell : game.getCells()){
            cellRepository.delete(cell);
        }
        gameRepository.delete(game);
    }

    public boolean isCellFull(Cell cell){
        if (cell.getValue() == Value.EMPTY){
            return false;
        } else{return true;}
    }

    public boolean isBoardFull(List<Cell> cells){
        for (Cell cell : cells){
            if (!isCellFull(cell)){
                return false;
            }
        }
        return true;
    }

    public void makePlayerMove(Cell cell){
        cell.setValue(Value.X);
        cellRepository.save(cell);
    }

    public void makeComputerMove(List<Cell> cells){
        List<Integer> emptyCells = new ArrayList<>();
        for (int i = 0; i < cells.size(); i++){
            if (!isCellFull(cells.get(i))){
                emptyCells.add(i);
            }
        }
        Random random = new Random();
        int randomNumber = random.nextInt(emptyCells.size());
        int computerGuess = emptyCells.get(randomNumber);

        cells.get(computerGuess).setValue(Value.O);
        cellRepository.save(cells.get(computerGuess));

    }

    public boolean checkLine(List<Cell> cells) {

        Value cellValue = cells.get(0).getValue();

        if (!cellValue.equals(Value.EMPTY)){
            for (int i = 1; i < cells.size(); i++) {
                if (!cellValue.equals(cells.get(i).getValue())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean checkWinner(List<Cell> cells){
        // Create a var that stores the first cell's value

        List<Cell> cellsRow1 = new ArrayList<>(Arrays.asList(cells.get(0), cells.get(1), cells.get(2)));
        List<Cell> cellsRow2 = new ArrayList<>(Arrays.asList(cells.get(3), cells.get(4), cells.get(5)));
        List<Cell> cellsRow3 = new ArrayList<>(Arrays.asList(cells.get(6), cells.get(7), cells.get(8)));

        List<Cell> cellsDiagonal1  = new ArrayList<>(Arrays.asList(cells.get(0), cells.get(4), cells.get(8)));
        List<Cell> cellsDiagonal2 = new ArrayList<>(Arrays.asList(cells.get(2), cells.get(4), cells.get(6)));

        List<Cell> cellsColumn1 = new ArrayList<>(Arrays.asList(cells.get(0), cells.get(3), cells.get(6)));
        List<Cell> cellsColumn2 = new ArrayList<>(Arrays.asList(cells.get(1), cells.get(4), cells.get(7)));
        List<Cell> cellsColumn3 = new ArrayList<>(Arrays.asList(cells.get(2), cells.get(5), cells.get(8)));

        List<List<Cell>> cellCombinations = new ArrayList<>(Arrays.asList(cellsRow1, cellsRow2, cellsRow3, cellsDiagonal1, cellsDiagonal2, cellsColumn1, cellsColumn2, cellsColumn3));

        for (List<Cell> cellList : cellCombinations){
            if(checkLine(cellList)){
                return true;
            }
        }

        return false;
    }

    public List<List<Value>> getGameState(List<Cell> cells){
        List<Value> cellsRow1Values = new ArrayList<>(Arrays.asList(cells.get(0).getValue(), cells.get(1).getValue(), cells.get(2).getValue()));
        List<Value> cellsRow2Values = new ArrayList<>(Arrays.asList(cells.get(3).getValue(), cells.get(4).getValue(), cells.get(5).getValue()));
        List<Value> cellsRow3Values = new ArrayList<>(Arrays.asList(cells.get(6).getValue(), cells.get(7).getValue(), cells.get(8).getValue()));


        List<List<Value>> board = new ArrayList<>(Arrays.asList(cellsRow1Values, cellsRow2Values, cellsRow3Values));

        return board;
    }

    public ReplyDTO processTurn(GameDTO gameDTO, long gameId){
        Game game = gameRepository.findById(gameId).get();
        Cell chosenCell = cellRepository.findByCellNumberAndGameId(gameDTO.getPosition(), gameId);

        List<Cell> cells = game.getCells();

        if (isBoardFull(cells)){
            return new ReplyDTO("Invalid move, board is full",getGameState(cells), false);
        }
        if(isCellFull(chosenCell)){
            return new ReplyDTO("Invalid move, chosen cell is occupied", getGameState(cells), false);
        }
        else{
            makePlayerMove(chosenCell);
        }

        if(checkWinner(cells)){
            ReplyDTO replyDTO = new ReplyDTO("You won", getGameState(cells), true);
            replyDTO.setResult(Result.WIN);
            return replyDTO;
        }
        if(isBoardFull(cells)){
            ReplyDTO replyDTO = new ReplyDTO("Invalid move, chosen cell is occupied", getGameState(cells), true);
            replyDTO.setResult(Result.DRAW);
            return replyDTO;
        }
        else {
//            cells passed in may need updating with cell players put 'x' in
            makeComputerMove(cells);
        }
        if(checkWinner(cells)){
            ReplyDTO replyDTO = new ReplyDTO("You lost", getGameState(cells), true);
            replyDTO.setResult(Result.LOSS);
            return replyDTO;
        } else {
            return new ReplyDTO("turn processed", getGameState(cells), true);
        }





    }

}
