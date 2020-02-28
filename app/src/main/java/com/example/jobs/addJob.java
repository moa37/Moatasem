package com.example.jobs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class addJob extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText jobName,start,end;
    Spinner spinner;
    String spinCont[]={"High","Low"};
    String spinText;
    Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job);
        initComponent();


    }

    private void initComponent() {
        jobName=findViewById(R.id.job);
        start=findViewById(R.id.startDate);
        end=findViewById(R.id.editEnd);
        spinner=findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,spinCont);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);

    }

    public void saveButton(View view) {
        String nameS=jobName.getText().toString();
        String startText=start.getText().toString();
        String endText=end.getText().toString();
        if(TextUtils.isEmpty(nameS)){
            jobName.setError("this field is required");
        }if(TextUtils.isEmpty(startText)){
            start.setError("this field is required");
        }if(TextUtils.isEmpty(endText)){
            end.setError("this field is required");
        }
        if(!TextUtils.isEmpty(nameS)&&!TextUtils.isEmpty(startText)&&!TextUtils.isEmpty(endText)){
            Intent intent =new Intent(this,MainActivity.class);
            intent.putExtra("name",nameS);
            intent.putExtra("start",startText);
            intent.putExtra("end",endText);
            intent.putExtra("spin",spinText);

            startActivity(intent);
            finish();

        }

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        itemSelcted(spinCont[position]);

    }

    private void itemSelcted(String s) {
        spinText=s;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
