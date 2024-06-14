package com.example.carloanapp;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DataInputActivity extends AppCompatActivity {
    Auto a;
    String  loanReport;
    String monthlyPayment;
    private EditText price;
    private EditText downPayment;
    private RadioGroup loanTermRG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.data_input);
        a = new Auto();
        price = findViewById(R.id.editText1);
        downPayment = findViewById(R.id.editText2);
        loanTermRG = findViewById(R.id.radioGroup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void collectAutoInputData(){
        a.setPrice((double)Integer.valueOf(price.getText().toString()));
        a.setDownPayment((double)Integer.valueOf(downPayment.getText().toString()));
        Integer radioID = loanTermRG.getCheckedRadioButtonId();
        RadioButton term = findViewById(radioID);
        a.setLoanTerm(term.getText().toString());

    }

    private void buildLoanReport(){
        Resources res = getResources();
        monthlyPayment = res.getString(R.string.report_line1) + String.format("%.02f",a.monthlyPayment());

        loanReport = res.getString(R.string.report_line6) + String.format("%10.02f",a.getPrice());
        loanReport += res.getString(R.string.report_line7) + String.format("%10.02f",a.getDownPayment());
        loanReport += res.getString(R.string.report_line9) + String.format("%10.02f",a.taxAmount());
        loanReport += res.getString(R.string.report_line10) + String.format("%10.02f",a.totalAmount());
        loanReport += res.getString(R.string.report_line11) + String.format("%10.02f",a.borrowedAmount());
        loanReport += res.getString(R.string.report_line12) + String.format("%10.02f",a.interestAmount());
        loanReport += "\n\n" +res.getString(R.string.report_line8) + " " +a.getLoanTerm() +" years.";
        loanReport += "\n\n"+res.getString(R.string.report_line2);
        loanReport += res.getString(R.string.report_line3);
        loanReport += res.getString(R.string.report_line4);
        loanReport += res.getString(R.string.report_line5);


    }

    public void activateLoanSummary(View view) {
        collectAutoInputData();
        buildLoanReport();
        Intent i = new Intent(this, SummaryActivity.class);
        i.putExtra("LoanReport",loanReport);
        i.putExtra("MonthlyPayment",monthlyPayment);
        startActivity(i);
    }
}