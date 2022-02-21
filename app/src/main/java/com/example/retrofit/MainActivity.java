package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.example.retrofit.Model.ModelApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String lat = "36.7201600";
        String lng = "-4.4203400";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.sunrise-sunset.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiCall apiCall = retrofit.create(ApiCall.class);
        Call<ModelApi> call = apiCall.getData(lat, lng);

        call.enqueue(new Callback<ModelApi>(){
            @Override
            public void onResponse(Call<ModelApi> call, Response<ModelApi> response) {
                if(response.code()!=200){
                    Log.i("testApi", "checkConnection");
                    return;
                }
                Log.i("testApi", response.body().getStatus() + " - " + response.body().getResults().getSunrise());
            }
            @Override
            public void onFailure(Call<ModelApi> call, Throwable t) {
            }
        });

    }
}