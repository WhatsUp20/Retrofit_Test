package com.example.gson_retrofit2_test;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewResult = findViewById(R.id.text_view_result);

        NetworkService.getInstance()
                .getJSONApi()
                .getPosts()
                .enqueue(new Callback<List<POJO>>() {
                    @Override
                    public void onResponse(Call<List<POJO>> call, Response<List<POJO>> response) {
                        if (!response.isSuccessful()) {
                            textViewResult.setText("Code " + response.code());
                            return;
                        }
                        List<POJO> pojos = response.body();

                        for (POJO pojo: pojos) {
                            String content = "";
                            content += "ID: " +pojo.getId() + "\n";
                            content += "User ID: " + pojo.getUserId() + "\n";
                            content += "Title: " + pojo.getTitle() + "\n";
                            content += "Text" + pojo.getBody() + "\n\n";

                            textViewResult.append(content);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<POJO>> call, Throwable t) {
                        textViewResult.setText(t.getMessage());
                    }
                });

    }
}