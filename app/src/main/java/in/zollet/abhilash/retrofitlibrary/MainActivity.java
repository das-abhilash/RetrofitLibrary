package in.zollet.abhilash.retrofitlibrary;

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
import in.zollet.abhilash.retrofitlibrary.Repos.RetrofitInterface;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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


            Observable<Response> responseObservable = getRetrofitClient().getUserDetails("9035548758", "123456");
            responseObservable.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(Response::getMessage)
                    .subscribe(msg -> showStatus(msg),
                            error -> showStatus(error.toString())
                    );

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
            Map<String, String> data = new HashMap<>();
            data.put("addressProofType", "aadhaarCard");
            data.put("idProofType","aadhaarCard");
            data.put("token", "W0JAMjFlMzdlY2U");
            Observable<ServerResponse> responseObservable = getRetrofitClient().postAddressProof("5606537e7a6f6c42560d0000", body,
                   // data
                    addressProofType, idProofType,token);
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

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public RetrofitInterface getRetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.1.10.241:9000/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(RetrofitInterface.class);
    }
}
