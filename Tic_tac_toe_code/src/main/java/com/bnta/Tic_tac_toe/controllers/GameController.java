package com.bnta.Tic_tac_toe.controllers;

import com.bnta.Tic_tac_toe.models.Game;
import com.bnta.Tic_tac_toe.models.GameDTO;
import com.bnta.Tic_tac_toe.models.Player;
import com.bnta.Tic_tac_toe.models.PlayerDTO;
import com.bnta.Tic_tac_toe.repositories.GameRepository;
import com.bnta.Tic_tac_toe.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    GameService gameService;

    @GetMapping
    public ResponseEntity<List<Game>> getAllGames(){
        List<Game> allGames = gameService.getAllGames();
        return new ResponseEntity<>(allGames, HttpStatus.OK);
    }

    @GetMapping(value = "/{gameId}")
    public ResponseEntity<Game> getGameById(@PathVariable long gameId){
        Optional<Game> optionalGame = gameService.getGameById(gameId);

        if(optionalGame.isPresent()){
            return new ResponseEntity<>(optionalGame.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @PostMapping
    public ResponseEntity<Game> startNewGame(@RequestParam long playerId){
        Game game = gameService.startNewGame(playerId);
        return new ResponseEntity<>(game, HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{gameId}")
    public ResponseEntity<Game> updateGame(@PathVariable long gameId, @RequestBody GameDTO gameDTO){
        return null;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteGameById(@PathVariable long id){
        Optional<Game> optionalGame = gameService.getGameById(id);

        if (optionalGame.isPresent()){
            gameService.deleteGameById(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    }
}
