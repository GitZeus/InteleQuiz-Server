package model.application;

public class GlobalException extends Exception {

    public GlobalException(Exception e) {
        super(e.getMessage());
        e.printStackTrace();
    }
}