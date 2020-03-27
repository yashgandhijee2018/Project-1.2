package com.demo.incampus.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

import com.demo.incampus.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class Info extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText dob_et;
    private Calendar c;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
        //if the system api is below marshmallow, set status bar to default black
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(Color.BLACK);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        dob_et=findViewById(R.id.dob_et);
        c = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, monthOfYear);
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };
        final DatePickerDialog dpd = new DatePickerDialog(Info.this, date,
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)
        );
        //need to add minimum and maximum time
        dob_et.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dpd.show();
            }
        });

        Spinner univ = findViewById(R.id.univ);
        Spinner course = findViewById(R.id.course);
        Spinner pos_et = findViewById(R.id.pos_et);
        Spinner gender_et = findViewById(R.id.gender_et);
        Spinner major = findViewById(R.id.major);

        univ.setOnItemSelectedListener(this); //univ.prompt="Select your institute"
        course.setOnItemSelectedListener(this); //course.prompt="Select your degree"
        pos_et.setOnItemSelectedListener(this);
        gender_et.setOnItemSelectedListener(this); //gender_et.prompt="Select your gender"
        major.setOnItemSelectedListener(this); //major.prompt="Select your major"

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
            this,
            R.array.institute,
            R.layout.spinner_item2
        );
        adapter1.setDropDownViewResource(R.layout.spinner_dialog_item);
        univ.setAdapter(adapter1);

        ArrayAdapter<CharSequence> adapter2 =ArrayAdapter.createFromResource(
            this,
            R.array.position,
            R.layout.spinner_item
        );
        adapter2.setDropDownViewResource(R.layout.spinner_dialog_item);
        pos_et.setAdapter(adapter2);

        ArrayAdapter<CharSequence> adapter3 =ArrayAdapter.createFromResource(
            this,
            R.array.courses,
            R.layout.spinner_item2
        );
        adapter3.setDropDownViewResource(R.layout.spinner_dialog_item);
        course.setAdapter(adapter3);

        ArrayAdapter<CharSequence> adapter4 =ArrayAdapter.createFromResource(
            this,
            R.array.genders,
            R.layout.spinner_item
        );
        adapter4.setDropDownViewResource(R.layout.spinner_dialog_item);
        gender_et.setAdapter(adapter4);

        ArrayAdapter<CharSequence> adapter5 =ArrayAdapter.createFromResource(
            this,
            R.array.major,
            R.layout.spinner_item2
        );
        adapter5.setDropDownViewResource(R.layout.spinner_dialog_item);
        major.setAdapter(adapter5);

        final EditText name=findViewById(R.id.name_et);
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()<3){
                    name.getBackground().setTint(Color.RED);
                }
                else{
                    name.getBackground().setTint(Color.parseColor("darkgrey"));
                }
            }
        });

        Button savechanges=findViewById(R.id.savechanges);
        savechanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name=findViewById(R.id.name_et);
                EditText dob=findViewById(R.id.dob_et);
                if(name.getText().length()>=3 && dob.getText().length()>0)
                    startActivity(new Intent(getApplicationContext(), AddPfP.class));
                else{
                    if(name.getText().length()<3)
                        name.getBackground().setTint(Color.RED);
                    else
                        dob_et.getBackground().setTint(Color.RED);
                }
            }
        });
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        dob_et.setText(sdf.format(c.getTime()));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        parent.getItemAtPosition(position);
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) { }
}
