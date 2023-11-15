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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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
    }

    public void makeComputerMove(List<Cell> cells){
        List<Cell> emptyCells = new ArrayList<>();
        for (Cell cell : cells){
            if (!isCellFull(cell)){
                emptyCells.add(cell);
            }
        }
        Random random = new Random();
        int randomNumber = random.nextInt(emptyCells.size());
        Cell computerGuess = emptyCells.get(randomNumber);
        computerGuess.setValue(Value.O);
    }

    public boolean checkWinner(){
//        write me
        return true;
    }

    public ReplyDTO processTurn(GameDTO gameDTO, long gameId){
        Game game = gameRepository.findById(gameId).get();
        Cell chosenCell = gameRepository.findByCellNumber(gameDTO.getPosition());
        List<Cell> cells = game.getCells();
        ReplyDTO replyDTO = new ReplyDTO("", cells, true);

        if (isBoardFull(cells)){
            replyDTO.setMessage("Invalid move, board is full");
            replyDTO.setValidMove(false);
            return replyDTO;
        }
        if(isCellFull(chosenCell)){
            replyDTO.setMessage("Invalid move, chosen cell is occupied");
            replyDTO.setValidMove(false);
            return replyDTO;
        }
        else{
            makePlayerMove(chosenCell);
        }
        if(checkWinner()){
            replyDTO.setMessage("Invalid move, chosen cell is occupied");
            replyDTO.setResult(Result.WIN);
            return replyDTO;
        }


    }

}
