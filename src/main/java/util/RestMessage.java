package util;

public class RestMessage {

    private String text;
    private RestMessageType type;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public RestMessageType getType() {
        return type;
    }

    public void setType(RestMessageType type) {
        this.type = type;
    }
}