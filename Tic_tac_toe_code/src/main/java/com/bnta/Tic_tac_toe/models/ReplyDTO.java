package com.bnta.Tic_tac_toe.models;

import java.util.List;

public class ReplyDTO {
    private  String message;
    private List<Cell> cells;
    private boolean isValidMove;
    private Result result;

    public ReplyDTO(String message, List<Cell> cells, boolean isValidMove) {
        this.message = message;
        this.cells = cells;
        this.isValidMove = isValidMove;
    }

    public ReplyDTO(){}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }

    public boolean isValidMove() {
        return isValidMove;
    }

    public void setValidMove(boolean validMove) {
        isValidMove = validMove;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
