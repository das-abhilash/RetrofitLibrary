package in.zollet.abhilash.retrofitlibrary.Repos;


public class QueryBody {
    public String role;
    public String token;

    public QueryBody(String role, String token) {
        this.token = token;
        this.role = role;
    }
}
