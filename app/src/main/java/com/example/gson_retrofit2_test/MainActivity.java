package com.example.gson_retrofit2_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gson_retrofit2_test.adapter.POJOAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private POJOAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new POJOAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        getData();

    }

    private void getData() {
        NetworkService.getInstance()
                .getJSONApi()
                .getPosts()
                .enqueue(new Callback<List<POJO>>() {
                    @Override
                    public void onResponse(Call<List<POJO>> call, Response<List<POJO>> response) {
                        if (response.isSuccessful()) {
                            final List<POJO> pojos = response.body();
                            adapter.setPojos(pojos);
                            recyclerView.setAdapter(adapter);

                            ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
                                @Override
                                public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                                    return false;
                                }

                                @Override
                                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                                    pojos.remove(viewHolder.getAdapterPosition());
                                    adapter.notifyDataSetChanged();
                                }
                            });
                            helper.attachToRecyclerView(recyclerView);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<POJO>> call, Throwable t) {
                        Log.i("Warning!", "Ошибка получения данных");
                    }
                });

    }

}