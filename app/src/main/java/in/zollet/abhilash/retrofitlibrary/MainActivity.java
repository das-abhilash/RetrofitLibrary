package in.zollet.abhilash.retrofitlibrary;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import in.zollet.abhilash.retrofitlibrary.Model.Response;
import in.zollet.abhilash.retrofitlibrary.Model.ServerResponse;
import in.zollet.abhilash.retrofitlibrary.Model.SeverNotifier;
import in.zollet.abhilash.retrofitlibrary.Repos.RetrofitInterface;
import in.zollet.abhilash.retrofitlibrary.Repos.SecondActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = ((LinearLayout) findViewById(R.id.container));
        container.removeAllViews();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        savePic();
    }

    private void savePic() {
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        File file = new File(getCacheDir() + "demo.png");
        OutputStream outStream = null;
        try {
            outStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        boolean saved = bm.compress(Bitmap.CompressFormat.PNG, 100, outStream);
    }


    private void showStatus(String txt) {
        TextView tv = new TextView(this);
        tv.setText(txt);
        container.addView(tv);
        //Toast.makeText(this, txt, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_get) {


          /*  Observable<Response> responseObservable = getRetrofitClient().getUserDetails("9035548758", "123456");
            responseObservable.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())

                    .map(Response::getMessage)
                    .subscribe(msg -> showStatus(msg),
                            error -> showStatus(error.toString())
                    );*/
SeverNotifier server;

            Call<Response> call = getRetrofitClient().getUserDetailsCall("908", "123456");
            call.enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    if (response.isSuccessful()) {
                        // use response data and do some fancy stuff :)

                        /*if(response.body().getError() != 0){
                            APIError error = ErrorUtils.parseError(response);
                            String er = error.message();
                            int eerr = 0;
                        }*/
                    } else {
                        // parse the response body …
                        /*APIError error = ErrorUtils.parseError(response);
                        String er = error.message();
                        int eerr = 0;*/
                        // … and use it to show error information

                        // … or just log the issue like we’re doing :)

                    }
                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {
                    int sdf = 0;
                }
            });
/*this::showStatus*/

            return true;
        }

        if (id == R.id.action_post) {
            Map<String, String> data = new HashMap<>();
            data.put("roleName", "testing10");
            data.put("androidToken", "W0JAMjFlMzdlY2U");
            Observable<ServerResponse> responseObservable = getRetrofitClient().postRole(data);
            responseObservable.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(ServerResponse::getMessage)
                    .subscribe(this::showStatus,
                            error -> showStatus(error.toString()));
            return true;
        }
        if (id == R.id.action_post_with_file) {

            File file = new File(getCacheDir() + "demo.png");

            RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("upload", file.getName(), reqFile);
            RequestBody addressProofType = RequestBody.create(MediaType.parse("text/plain"), "aadhaarCard");
            RequestBody idProofType = RequestBody.create(MediaType.parse("text/plain"), "aadhaarCard");
            RequestBody token = RequestBody.create(MediaType.parse("text/plain"), "W0JAMjFlMzdlY2U");
            Map<String, RequestBody> data = new HashMap<>();
            data.put("addressProofType", addressProofType);
            data.put("idProofType",idProofType);
            data.put("token",token);
            Observable<ServerResponse> responseObservable = getRetrofitClient().postAddressProof("5606537e7a6f6c42560d0000", body,
                    data
                    /*addressProofType, idProofType,token*/);
            responseObservable.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(ServerResponse::getMessage)
                    .subscribe(this::showStatus,
                            error -> showStatus(error.toString()));
            return true;
        }
        if (id == R.id.action_put) {
            /*Observable<ServerResponse> responseObservable = getRetrofitClient().putRole("58624215880bed16c64b73d3", "W0JAMWEyNzE0NjQ", "testing5");
            responseObservable.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(ServerResponse::getMessage)
                    .subscribe(this::showStatus,
                            error -> showStatus(error.toString()));*/
            Map<String, String> data = new HashMap<>();
            data.put("androidToken", "W0JAMjFlMzdlY2U");
            data.put("roleName", "testing32");

            Observable<ServerResponse> responseObservable = getRetrofitClient().putRole("58624215880bed16c64b73d3", data);
            responseObservable.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(ServerResponse::getMessage)
                    .subscribe(this::showStatus,
                            error -> showStatus(error.toString()));
            return true;
        }
        if (id == R.id.action_delete) {
            Observable<ServerResponse> responseObservable = getRetrofitClient().deleteRole("58624051880bed16c64b720e", "W0JAMjFlMzdlY2U");
            responseObservable.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(ServerResponse::getMessage)
                    .subscribe(this::showStatus,
                            error -> showStatus(error.toString()));
            responseObservable.retry();
            return true;
        }
        if (id == R.id.action_multiple_api_call) {
            Map<String, String> data = new HashMap<>();
            data.put("roleName", "testing5");
            data.put("androidToken", "W0JAMjFlMzdlY2U");
            Observable.zip(
                    getRetrofitClient().putRole("58624215880bed16c64b73d3",data),
                    getRetrofitClient().postRole(data),
                    (response, serverResponse) -> (response.getMessage() + " : " + serverResponse.getMessage()))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::showStatus,
                            error -> showStatus(error.toString()));

            return true;
        }
        if (id == R.id.action_nested_api_call) {
            /*Observable<ServerResponse> responseObservable =*/
            /*getRetrofitClient().postRole("testing41", "W0JANzM4NDU4ODM")
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext(serverResponse -> showStatus(serverResponse.getMessage()))
                    .filter(serverResponse -> serverResponse.getResult().size() != 0)
                    .flatMap(serverResponse -> getRetrofitClient().deleteRole
                            (serverResponse.getResult().get(0).getId(),
                                    "W0JANzM4NDU4ODM",
                                    serverResponse.getResult().get(0).getRoleName())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread()))
                    .map(ServerResponse::getMessage)
                    .subscribe(this::showStatus,
                            error -> showStatus(error.toString()));*/

            getRetrofitClient().getUserDetails("9035548758", "123456")
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext(response -> showStatus(response.getMessage()))
                    .flatMap(serverResponse -> getRetrofitClient().getUserDetails("9035548758", "123456")
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread()))
                    .map(Response::getMessage)
                    .subscribe(this::showStatus,
                            error -> showStatus(error.toString()));

            return true;
        }
        if (id == R.id.action_retrofit_tag) {

            Intent i = new Intent(this, SecondActivity.class);
            startActivity(i);


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static RetrofitInterface getRetrofitClient() {

        /*OkHttpClient client = new OkHttpClient();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.interceptors().add(interceptor);*/


         Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.1.10.241:9000/")
                //.client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

       /* RestAdapter.Builder builder = new RestAdapter.Builder()
                //.setEndpoint(API_LOCATION)
                .setLogLevel(RestAdapter.LogLevel.FULL) // this is the important line
                .setClient(new OkClient(new OkHttpClient()));*/
        /*Call<ResponseBody> originalCall =
                downloadService.downloadFileWithDynamicUrlSync(fileUrl);

        Call<ResponseBody> newCall = originalCall.clone();
        newCall.enqueue(downloadCallback);

        originalCall.request();*/

        return retrofit.create(RetrofitInterface.class);
    }

    public static Retrofit getRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.1.10.241:9000/") /*"http://10.1.10.241:9000/"*/
                //.client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    private void call (SeverNotifier server){
       /* Call<Response> call = getRetrofitClient().getUserDetailsCall();
        call.enqueue(server);*/
    }
}
