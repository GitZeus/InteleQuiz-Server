package model.application;

public class RestResponse<T> {

    private T data;
    private RestMessage message;
    
    public RestResponse(){};

    public RestResponse(T data) {
        this.data = data;
    }

    public RestResponse(T data, RestMessage message) {
        this.data = data;
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public RestMessage getMessage() {
        return message;
    }

    public void setMessage(RestMessage message) {
        this.message = message;
    }
}
