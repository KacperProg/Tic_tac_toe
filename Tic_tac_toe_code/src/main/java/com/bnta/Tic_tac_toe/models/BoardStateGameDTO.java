package com.bnta.Tic_tac_toe.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class BoardStateGameDTO {

    private Long id;
    private Player player;
    private boolean isComplete;
    private Result result;

    privt

}
