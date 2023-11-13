package com.bnta.Tic_tac_toe.repositories;

import com.bnta.Tic_tac_toe.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository <Player, Long> {
}
