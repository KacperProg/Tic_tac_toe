package com.bnta.Tic_tac_toe.services;

import com.bnta.Tic_tac_toe.models.Game;
import com.bnta.Tic_tac_toe.models.Player;
import com.bnta.Tic_tac_toe.models.PlayerDTO;
import com.bnta.Tic_tac_toe.repositories.GameRepository;
import com.bnta.Tic_tac_toe.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    GameRepository gameRepository;

    public List<Player> getAllPlayers(){
        return playerRepository.findAll();
    }

    public Optional<Player> getPlayerById(long id){
        return playerRepository.findById(id);
    }

    public Player addPlayer(Player player){
        playerRepository.save(player);
        return player;
    }

    public void deleteById(long id){
        Player player = playerRepository.findById(id).get();
        for (Game game : player.getGames()){
            game.setPlayer(null);
        }
        playerRepository.deleteById(id);
    }

    public Player updatePlayerName(Player updatedPlayer){
        Player player = playerRepository.findById(updatedPlayer.getId()).get();
        player.setPlayerName(updatedPlayer.getPlayerName());
        playerRepository.save(updatedPlayer);
        return updatedPlayer;
    }

}
