package in.zollet.abhilash.retrofitlibrary.Repos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import in.zollet.abhilash.retrofitlibrary.Model.Response;
import in.zollet.abhilash.retrofitlibrary.R;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public class SecondActivity extends AppCompatActivity {
    Call<Response> call;
    Dispatcher dispatcher;
    TextView tv;
    CompositeSubscription mSubscriptions = new CompositeSubscription();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        tv = (TextView) findViewById(R.id.demo);
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//        httpClient.addInterceptor(logging);
        httpClient.connectTimeout(10, TimeUnit.MINUTES)
                .readTimeout(10, TimeUnit.MINUTES);
        dispatcher=new Dispatcher();
        dispatcher.setMaxRequests(2);
        dispatcher.executorService();
        httpClient.retryOnConnectionFailure(true);
        httpClient.dispatcher(dispatcher);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://prodapi.livezelo.com/")
//                .baseUrl("http://api.themoviedb.org/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        RetrofitInterface ri = retrofit.create(RetrofitInterface.class);
        /*call = ri.getCenterDetails("W0JAN2VkNzQ0NjE","57623a7a63187745697ff55e","1483366677");
        Log.v("RETROFIT","response Started");
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    Log.v("RETROFIT","response successful");
                } else {
                    Log.v("RETROFIT","response unsuccessful");
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.v("RETROFIT","connection failed");
                Toast.makeText(SecondActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                if(call.isCanceled())
                    Log.v("RETROFIT","connection canceled");
            }
        });*/
        final int[] no = {0};
        Button start = (Button) findViewById(R.id.button);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("RETROFIT","loop started");

               for(int i = 0;i<10;i++,no[0]++) {
                   int sd = no[0];
                   Log.v("RETROFIT","loop added");
                   mSubscriptions.add(
                           ri.getCenterDetailsOBS("W0JAMjc0ZWY3MmE","57623a7a63187745697ff55e","1483366677")
                           .subscribeOn(Schedulers.computation())
                                   .observeOn(AndroidSchedulers.mainThread())
                                   .subscribe((response) ->  Log.v("RETROFIT","response successful - msg :" + response.getMessage() + sd),
                                          // () -> Log.v("RETROFIT","response successful - " + sd),
                                           error -> Log.v("RETROFIT",error.toString() + "connection failed")));
//                   Call<ServerResponse> call1 = ri.getCenterDetails("W0JAMjc0ZWY3MmE","57623a7a63187745697ff55e","1483366677");
                 /*  Call<ServerResponse> call1 = ri.error("ef3c1c58e12e6441d1f71be6ea987a36");

                   Log.v("RETROFIT","response Started");
                   call1.clone().enqueue(new Callback<ServerResponse>() {
                       @Override
                       public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {
                           if (response.isSuccessful()) {
                               Log.v("RETROFIT","response successful - " + sd);
                           } else {
                               getApplicationContext();
                           }



                       }

                       @Override
                       public void onFailure(Call<ServerResponse> call, Throwable t) {
                           Log.v("RETROFIT","connection failed" + t.getMessage());
                           tv.setText(call.toString());
                           start.setText("canceled");
                           //Toast.makeText(SecondActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                           if(call.isCanceled()) {
                               start.setText("canceled");
                               Log.v("RETROFIT", "connection canceled");
                           }

                        if (t instanceof IOException) {
                            errorType = “Timeout”;
                            errorDesc = String.valueOf(error.getThrowable().getCause());
                        }
                        else if (t instanceof IllegalStateException) {
                            errorType = “ConversionError”;
                            errorDesc = String.valueOf(error.getThrowable().getCause());
                        } else {
                            errorType = “Other Error”;
                            errorDesc = String.valueOf(error.getThrowable().getLocalizedMessage());
                        }
                       }
                   });*/
               }


            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (call != null)
            call.cancel();
        dispatcher.cancelAll();
        mSubscriptions.unsubscribe();


        /*
        * executor size**
        * dispathcer**
        * using observable
        * onfailure on UI**
        * when onFailure
        * custom tag and retry
        * mvvm architecture
        * bindActivity() in Rx
        * max retry limit : @Link https://github.com/square/retrofit/issues/828
        * error response
        *
        * rx thread pool
        * fixed header
        * fix header with exception
        *
        * */

    }
}
