package util;

public class ITQException extends Exception {
    
    private final String message;
    
    public ITQException(String mensagem){
        super();
        this.message = mensagem;
    }

    @Override
    public String getMessage() {
        return message;
    }
    
    @Override
    public String toString() {
        return "ITQException{" + "message=" + message + '}';
    }
}