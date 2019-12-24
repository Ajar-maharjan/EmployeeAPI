package com.e.classworkvoli;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.e.classworkvoli.API.EmployeeAPI;
import com.e.classworkvoli.model.Employee;
import com.e.classworkvoli.url.URL;

import javax.xml.validation.Validator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Search extends AppCompatActivity {

    EditText etEmpNo;
    Button btnSearch;
    TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        etEmpNo = findViewById(R.id.etEmpNo);
        tvData = findViewById(R.id.tvData);
        btnSearch = findViewById(R.id.btnSearch);


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });
    }

    private void loadData(){

        EmployeeAPI employeeAPI = URL.CreateInstance().create(EmployeeAPI.class);

        Call<Employee> listCall = employeeAPI.getEmployeeByID(Integer.parseInt(etEmpNo.getText().toString()));
        listCall.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
            String content = "";
                content += " Id : " +response.body().getId()+"\n";
                content += " Name : " +response.body().getEmployee_name()+"\n";
                content += " Age : " +response.body().getEmployee_age()+"\n";
                content += " Salary : " +response.body().getEmployee_salary()+"\n";
            tvData.setText(content);
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Log.d("msg","onFailure"+t.getLocalizedMessage());
                Toast.makeText(Search.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

