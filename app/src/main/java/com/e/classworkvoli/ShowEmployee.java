package com.e.classworkvoli;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.e.classworkvoli.API.EmployeeAPI;
import com.e.classworkvoli.Recycler.EmployeeAdapter;
import com.e.classworkvoli.Recycler.EmployeeView;
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

//    TextView tvOutput;
    RecyclerView recyclerView;
    List<EmployeeView> employeeViewList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_employee);

//        tvOutput=findViewById(R.id.tvOutput);
        recyclerView = findViewById(R.id.recyclerView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //instance for interface
        EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);
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

                for(Employee emp: employeeList){
//                    String data = "";
//                    data +="Name is:"+emp.getEmployee_name()+"\n";
//                    data +="Salary is:"+emp.getEmployee_salary()+"\n";
//                    data +="Age is:"+emp.getEmployee_age()+"\n";
//                    data +="........"+"\n";
//                    tvOutput.append(data);
                    int id,age;
                    String name,salary;
                    id = emp.getId();
                    age = emp.getEmployee_age();
                    name = emp.getEmployee_name();
                    salary = emp.getEmployee_salary();
                    employeeViewList.add(new EmployeeView(id,name,salary,age));

                }


            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Log.d("msg","onFailure"+t.getLocalizedMessage());
                Toast.makeText(ShowEmployee.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        EmployeeAdapter employeeAdapter = new EmployeeAdapter(this,employeeViewList);
        recyclerView.setAdapter(employeeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
