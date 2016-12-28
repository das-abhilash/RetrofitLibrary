package in.zollet.abhilash.retrofitlibrary.Model;

import java.util.List;

/**
 * Created by Abhilash on 12/27/2016.
 */

public class Response {

    private Integer error;
    private String message;
    private Integer count;
    private String api_element;
    private List<Result> result = null;

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getApiElement() {
        return api_element;
    }

    public void setApiElement(String api_element) {
        this.api_element = api_element;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }
}