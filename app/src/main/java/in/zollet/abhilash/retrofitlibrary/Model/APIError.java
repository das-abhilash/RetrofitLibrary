package in.zollet.abhilash.retrofitlibrary.Model;

public class APIError {

    private int statusCode;
    private String message = " nope ";

    public APIError() {
    }

    public int status() {
        return statusCode;
    }

    public String message() {
        return message;
    }
}
