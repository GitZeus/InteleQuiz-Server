package config;

import model.application.GlobalException;
import model.application.RestMessage;
import model.application.RestMessageType;
import model.application.RestResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    public @ResponseBody RestResponse<String> tratar(Exception ex) {
        RestMessage message = new RestMessage();
        message.setText(ex.getMessage());
        message.setType(RestMessageType.ERROR);
        RestResponse response = new RestResponse(null, message);
        return response;
    }
}