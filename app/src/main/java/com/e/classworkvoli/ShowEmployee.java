package com.e.classworkvoli;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.e.classworkvoli.API.EmployeeAPI;
import com.e.classworkvoli.Recycler.EmployeeAdapter;
import com.e.classworkvoli.model.Employee;
import com.e.classworkvoli.url.URL;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowEmployee extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_employee);

        recyclerView = findViewById(R.id.recyclerView);

        //instance for interface
        EmployeeAPI employeeAPI = URL.CreateInstance().create(EmployeeAPI.class);
        Call<List<Employee>> listCall = employeeAPI.getAllEmployees();

        //asynchronous
        listCall.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(ShowEmployee.this, "Error code"+response.code(), Toast.LENGTH_SHORT).show();
                    Log.d("msg","onFailure"+ response.code());

                    return;
                }

                List<Employee> employeeList = response.body();

                EmployeeAdapter employeeAdapter = new EmployeeAdapter(ShowEmployee.this,employeeList);
                recyclerView.setAdapter(employeeAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(ShowEmployee.this));


            }


            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Log.d("msg","onFailure"+t.getLocalizedMessage());
                Toast.makeText(ShowEmployee.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }
}
