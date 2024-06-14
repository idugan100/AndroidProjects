package com.example.tablecalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView display;
    SimpleExpression s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        display=findViewById(R.id.textview1);
        s=new SimpleExpression();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void goAC(View view) {
        s.clearOperands();
        display.setText("0");
    }

    public void goOperator(View view) {
        Button b = (Button)view;
        String operator = b.getText().toString();
        try{
            String val = (String) display.getText();
            s.setOperand1(Integer.parseInt(val));

        }catch(NumberFormatException e){
            s.setOperand1(0);
        }
        display.setText("0");
        s.setOperator(operator);

    }

    public void goOperand(View view) {
        String value =(String) display.getText();
        Button b = (Button)view;
        String digit = b.getText().toString();

        if(value.charAt(0)=='0'){
            display.setText(digit);
        }
        else{
            display.setText((String)display.getText() + digit.charAt(0) );
        }
    }

    public void goComputer(View view) {
        try{
            String val = (String) display.getText();
            s.setOperand2(Integer.parseInt(val));

        }catch(NumberFormatException e){
            s.setOperand2(0);
        }
        display.setText(s.getValue().toString());
    }
}