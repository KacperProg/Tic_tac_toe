package com.bnta.Tic_tac_toe.services;

import com.bnta.Tic_tac_toe.models.Cell;
import com.bnta.Tic_tac_toe.models.Game;
import com.bnta.Tic_tac_toe.models.Player;
import com.bnta.Tic_tac_toe.models.Value;
import com.bnta.Tic_tac_toe.repositories.CellRepository;
import com.bnta.Tic_tac_toe.repositories.GameRepository;
import com.bnta.Tic_tac_toe.repositories.PlayerRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Cell> makeCells(int numberOfCells){
        // initialising
        List<Cell> cells = new ArrayList<>();
        int i;
        for (i = 0; i < numberOfCells; i++) {
            Cell cell = new Cell(Value.EMPTY);
            cells.add(cell);
            cellRepository.save(cell);
        }

        return cells;
    }

    public Game startNewGame(long playerId) {
        Player player = playerRepository.findById(playerId).get();
        Game game = new Game(player);
        game.setCells(makeCells(9));
        gameRepository.save(game);

        return game;
    }

}
