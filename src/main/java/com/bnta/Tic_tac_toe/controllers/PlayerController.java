package com.bnta.Tic_tac_toe.controllers;

import com.bnta.Tic_tac_toe.models.Player;
import com.bnta.Tic_tac_toe.models.PlayerDTO;
import com.bnta.Tic_tac_toe.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayers(){
        return null;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable long id){
        return null;
    }

    @PostMapping
    public ResponseEntity<Player> addPlayer(@RequestBody Player player){
        return null;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Player> deletePlayerById(@PathVariable long id){
        return null;
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Player> updatePlayerName(@PathVariable long id, @RequestBody PlayerDTO playerDTO){
        return null;
    }


}
