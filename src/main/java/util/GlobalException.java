package util;

public class GlobalException extends Exception {

    public GlobalException(Exception e) {
        super(e.getMessage());
    }
    
    public GlobalException(String message) {
        super(message);
    }
}