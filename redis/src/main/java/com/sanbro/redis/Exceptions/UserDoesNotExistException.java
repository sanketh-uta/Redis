package com.sanbro.redis.Exceptions;

public class UserDoesNotExistException extends RuntimeException{
   public UserDoesNotExistException(String message){
        super(message);
    }
}
