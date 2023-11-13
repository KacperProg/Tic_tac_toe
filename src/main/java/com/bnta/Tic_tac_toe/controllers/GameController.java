package com.bnta.Tic_tac_toe.controllers;

import com.bnta.Tic_tac_toe.models.Game;
import com.bnta.Tic_tac_toe.models.GameDTO;
import com.bnta.Tic_tac_toe.models.PlayerDTO;
import com.bnta.Tic_tac_toe.repositories.GameRepository;
import com.bnta.Tic_tac_toe.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    GameService gameService;

    @GetMapping
    public ResponseEntity<List<Game>> getAllGames(){return null;}

    @GetMapping(value = "/{gameId}")
    public ResponseEntity<Game> getGameById(@PathVariable long gameId){return null;}

    @PostMapping
    public ResponseEntity<Game> startNewGame(@RequestParam long playerId){return null;}

    @PatchMapping(value = "/{gameId}")
    public ResponseEntity<Game> updateGame(@PathVariable long gameId, @RequestBody GameDTO gameDTO){return null;}

    @DeleteMapping
    public ResponseEntity<Game> deleteGame(){return null;}

}
