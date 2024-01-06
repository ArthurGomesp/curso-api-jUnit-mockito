package br.com.gomes.api.exceptions;

public class DataIntegratyViolationException extends RuntimeException{
    public DataIntegratyViolationException(String message){
        super(message);
    }

}
