package in.zollet.abhilash.retrofitlibrary.Repos;

import java.util.List;
import java.util.Map;

import in.zollet.abhilash.retrofitlibrary.Model.Response;
import in.zollet.abhilash.retrofitlibrary.Model.ServerResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;



public interface RetrofitInterface {

    @GET("api/users/get-auth-token.json")
    Observable<Response> getUserDetails(@Query("primaryContact") String phone, @Query("passwd") String password);


    @GET("3/movie/top_d")
    Call<ServerResponse> error(@Query("api_key") String phon);


    @GET("api/v2/supplier/lity")
    Call<ServerResponse> getCenterDetails(@Query("androidToken") String sdf, @Query("centerId") String password,@Query("dateOfOccupancy") String phone);

    @GET("api/v2/supplier/center_availibility")
    Observable<Response> getCenterDetailsOBS(@Query("androidToken") String sdf, @Query("centerId") String password,@Query("dateOfOccupancy") String phone);


    @GET("api/users/getuth-token.json")
    Call<Response> getUserDetailsCall(@Query("primarContact") String phone, @Query("passwd") String password);

    @DELETE("roles/{roleId}")
    Observable<ServerResponse> deleteRole (@Path("roleId") String roleId, @Query("token") String token);

    @POST("roles")
    Observable<ServerResponse> postRole( @QueryMap Map<String, String> queries
                                         /*@Query("roleName") String role, @Query("androidToken") String token*/);

    @PUT("roles/{roleId}")
    Observable<ServerResponse> putRole(@Path("roleId") String roleId, @QueryMap Map<String, String> queries
                                       /*@Query("token") String token,@Query("roleName") String role*/);

    @PUT("roles/{roleId}")
    Observable<ServerResponse> putBodyRole(@Path("roleId") String roleId, @Body QueryBody body);

    //@Headers("token: W0JAMWEyNzE0NjQ")
    @Multipart
    @POST("api/users/{roleId}/kyc/proof/upload")
    Observable<ServerResponse> postAddressProof(@Path("roleId") String roleId, @Part/*("addressProofImage")*/ MultipartBody.Part image,
                                                @PartMap() Map<String, RequestBody> options
                                                /*@Part("addressProofType") RequestBody addressProofType,
                                                @Part("idProofType")RequestBody idProofType,@Part("token")RequestBody token*/
                                                /*@Query("addressProofType") String addressProofType,
                                                @Query("idProofType") String idProofType,@Query("token") String token*/);

    @GET("tasks")
    Observable<List<ServerResponse>> getTask(@Query("id") List<String> roleId);

    @GET("resource/example.zip")
    Call<ResponseBody> downloadFileWithFixedUrl();

    @GET("user")
    Call<List<ServerResponse>> getResponse(
            @HeaderMap Map<String, String> headers);

    @Headers("Cache-Control: max-age=640000")
    @GET("user")
    Call<ServerResponse> getHeaderResponse(@Query("user") String  user  );

    @GET("/tasks")
    Call<List<ServerResponse>> getTasks(@Header("Content-Range") String contentRange);

    // option 2: using a dynamic URL
    @Streaming
    @GET
    Call<ResponseBody> downloadFileWithDynamicUrlSync(@Url String fileUrl);

    //Activate Retrofit Logging

    @FormUrlEncoded
    @POST("user")
    Call<ServerResponse> createUser(@Field("user") String user);


    @FormUrlEncoded
    @POST("user")
    Call<ServerResponse> createUserEncoded(@Field(value = "user", encoded = true) String user);


    @FormUrlEncoded
    @PUT("user")
    Call<ServerResponse> update(@FieldMap Map<String, String> fields);


    @GET
    public Call<ResponseBody> profilePicture(@Url String url);



}
