package com.example.shopperpractice;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CreateList extends AppCompatActivity {

    Intent intent;

    EditText nameEditText;
    EditText storeEditText;
    EditText dateEditText;

    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nameEditText = findViewById(R.id.nameEditText);
        storeEditText = findViewById(R.id.storeEditText);
        dateEditText = findViewById(R.id.dateEditText);

        calendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateDueDate();
            }
        };

        dateEditText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                new DatePickerDialog(CreateList.this,
                        date,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }
    public void updateDueDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        dateEditText.setText(simpleDateFormat.format(calendar.getTime()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //get the id of the item that was selected
        switch (item.getItemId()){
            case R.id.action_home :
                //initializing an Intent for the Main Activity, starting it
                //and returning true
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_create_list :
                intent = new Intent(this, CreateList.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);


        }
    }

    public void createList(MenuItem menuItem){

        String name = nameEditText.getText().toString();
        String store = storeEditText.getText().toString();
        String date = dateEditText.getText().toString();

        if(name.trim().equals("") || store.trim().equals("") || store.trim().equals("")){
            Toast.makeText(this, "Please enter a name, store, and date!", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Shopping List Added!", Toast.LENGTH_LONG).show();
        }
    }

}
