package util;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ITQException.class)
    public @ResponseBody RestResponse<String> tratar(ITQException ex) {
        RestMessage message = new RestMessage();
        message.setText(ex.getMessage());
        message.setType(RestMessageType.ERROR);
        RestResponse response = new RestResponse(message);
        ex.printStackTrace();
        return response;
    }
}