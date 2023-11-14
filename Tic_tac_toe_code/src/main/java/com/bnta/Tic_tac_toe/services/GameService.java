package com.bnta.Tic_tac_toe.services;

import com.bnta.Tic_tac_toe.models.Cell;
import com.bnta.Tic_tac_toe.models.Game;
import com.bnta.Tic_tac_toe.models.Player;
import com.bnta.Tic_tac_toe.models.Value;
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

}
