package com.e.classworkvoli;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.e.classworkvoli.API.EmployeeAPI;
import com.e.classworkvoli.model.EmployeeCUD;
import com.e.classworkvoli.url.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Register extends AppCompatActivity {

    EditText etName, etSalary, etEmpAge;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.etName);
        etSalary = findViewById(R.id.etSalary);
        etEmpAge = findViewById(R.id.etEmpAge);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    private void Register(){
        String name = etName.getText().toString();
        Float salary = Float.parseFloat(etSalary.getText().toString());
        int age = Integer.parseInt(etEmpAge.getText().toString());

        EmployeeCUD employee = new EmployeeCUD(name,salary, age);


        EmployeeAPI employeeAPI = URL.CreateInstance().create(EmployeeAPI.class);

        Call<Void> voidCall = employeeAPI.registerEmployee(employee);
        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(Register.this, "The detail has been registered", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("msg","onFailure"+t.getLocalizedMessage());
                Toast.makeText(Register.this, "Error : "+ t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
