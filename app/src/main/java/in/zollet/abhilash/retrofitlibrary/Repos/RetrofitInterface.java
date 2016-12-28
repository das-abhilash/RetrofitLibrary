package in.zollet.abhilash.retrofitlibrary.Repos;

import java.util.Map;

import in.zollet.abhilash.retrofitlibrary.Model.Response;
import in.zollet.abhilash.retrofitlibrary.Model.ServerResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;



public interface RetrofitInterface {

    @GET("api/users/get-auth-token.json")
    Observable<Response> getUserDetails(@Query("primaryContact") String phone, @Query("passwd") String password);

    @DELETE("roles/{roleId}")
    Observable<ServerResponse> deleteRole (@Path("roleId") String roleId, @Query("token") String token);

    @POST("roles")
    Observable<ServerResponse> postRole( @QueryMap Map<String, String> queries
                                         /*@Query("roleName") String role, @Query("androidToken") String token*/);

    @PUT("roles/{roleId}")
    Observable<ServerResponse> putRole(@Path("roleId") String roleId, @QueryMap Map<String, String> queries
                                       /*@Query("token") String token,@Query("roleName") String role*/);

    //@Headers("token: W0JAMWEyNzE0NjQ")
    @Multipart
    @POST("api/users/{roleId}/kyc/proof/upload")
    Observable<ServerResponse> postAddressProof(@Path("roleId") String roleId, @Part/*("addressProofImage")*/ MultipartBody.Part image,
                                                //@PartMap Map<String, String> options
                                                @Part("addressProofType") RequestBody addressProofType,
                                                @Part("idProofType")RequestBody idProofType,@Part("token")RequestBody token
                                                /*@Query("addressProofType") String addressProofType,
                                                @Query("idProofType") String idProofType,@Query("token") String token*/);

}
