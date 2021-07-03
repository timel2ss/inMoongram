package com.team.exception;

public class IdNotFoundException extends RuntimeException{
    public IdNotFoundException(String msg){
        super(msg);
    }
    public IdNotFoundException(){
        this("존재하지 않는 ID 입니다.");
    }
}
