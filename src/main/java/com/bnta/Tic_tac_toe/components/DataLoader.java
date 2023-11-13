package com.bnta.Tic_tac_toe.components;

import com.bnta.Tic_tac_toe.models.Player;
import com.bnta.Tic_tac_toe.repositories.GameRepository;
import com.bnta.Tic_tac_toe.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    GameRepository gameRepository;

    public DataLoader(){

    }

    public void run(ApplicationArguments args){
        Player zsolt = new Player("Zsolt");
        playerRepository.save(zsolt);
    }

}
