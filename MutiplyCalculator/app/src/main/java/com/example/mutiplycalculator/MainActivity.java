package com.example.mutiplycalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Calculator calculator = new Calculator();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //initialize result to default calculator value
        TextView results = findViewById(R.id.result);
        results.setText(String.valueOf(this.calculator.getValue()));

    }

    public void multiply(View view){
        EditText input = findViewById(R.id.input);
        TextView results = findViewById(R.id.result);
        //try catch here in case no input is given and user clicks multiply button
        try {
            int inputNumber = Integer.parseInt(input.getText().toString());
            this.calculator.multiplyBy(inputNumber);
            results.setText(String.valueOf(this.calculator.getValue()));
        }
        catch (Exception ignored){

        }


    }

    public void clear(View view){
        this.calculator.reset();
        TextView results = findViewById(R.id.result);
        results.setText(String.valueOf(this.calculator.getValue()));
    }
}