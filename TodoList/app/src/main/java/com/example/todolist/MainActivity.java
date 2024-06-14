package com.example.todolist;

import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.model.Document;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> items;
    ArrayAdapter<String> itemsAdaptor;
    ListView itemsView;
    DocumentReference doc = FirebaseFirestore.getInstance().document("todo/afx6Xnbebm0qSdUfFZ5l");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        itemsView = findViewById(R.id.listView);

        readItems();
        itemsAdaptor = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        itemsView.setAdapter(itemsAdaptor);
        setUpItemsListener();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void addItem(View view) {
        EditText e = findViewById(R.id.editTextText);
        String s = e.getText().toString();
        itemsAdaptor.add(s);
        e.setText("");
        writeItems();
    }

    private void setUpItemsListener(){
        itemsView.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener(){
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        items.remove(position);
                        itemsAdaptor.notifyDataSetChanged();
                        writeItems();
                        return true;
                    }
                }
        );
    }

    private void readItems(){
        items= new ArrayList<String>();
        doc.get().addOnSuccessListener(
                new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        ArrayList<String> s = (ArrayList<String>) documentSnapshot.get("items");

                        items.addAll(s);
                        itemsAdaptor.notifyDataSetChanged();
                        Log.e("TAG", items.get(0) );


                    }
                }
        ).addOnFailureListener(
                new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle failure here
                        Log.e("TAG", "Error retrieving document: " + e.getMessage());
                    }
                }
        );
//        File filesDir =  getFilesDir();
//        File todoFile = new File(filesDir,"todo.txt");
//        try {
//            items= new ArrayList<String>(FileUtils.readLines(todoFile));
//        }catch(Exception e){
//            items= new ArrayList<String>();
//
//        }
    }
    private void writeItems(){
        Map<String, Object> a = new HashMap<>();
        a.put("items",items);
        doc.set(a);
//        File filesdir =getFilesDir();
//        File todoFile = new File(filesdir,"todo.txt");
//        String encoding=null;
//        try{
//            FileUtils.writeLines(todoFile,items);
//        } catch(IOException e){
//            e.printStackTrace();
//        }
    }
}