package com.example.coffeeorder;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private OrderCalculator oc;
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
        oc = new OrderCalculator();
    }


    public void handleDecrement(View view) {
        oc.decrementQty();
        int qty = oc.getQuantity();
        TextView t = findViewById(R.id.qtyview);
        t.setText(String.valueOf(qty));
    }

    public void handleIncrement(View view) {
        oc.incrementQty();
        int qty = oc.getQuantity();
        TextView t = findViewById(R.id.qtyview);
        t.setText(String.valueOf(qty));
    }

    public void handleOrder(View view) {
        TextView t = findViewById(R.id.summary);
        CheckBox creamBox = findViewById(R.id.cream);
        CheckBox chocolateBox = findViewById(R.id.chocolate);
        String output = oc.getOrderSummary(creamBox.isChecked(), chocolateBox.isChecked());
        t.setText(output);
    }
}