package com.example.jobs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class addtask extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText taskName,taskStart,taskEnd;
    Spinner taskSpin;
    String spinCont[]={"High","Low"};
    String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtask);
        initcomponent();
    }

    private void initcomponent() {
        taskName=findViewById(R.id.nameTask);
        taskStart=findViewById(R.id.StartTask1);
        taskEnd=findViewById(R.id.endTask1);
        taskSpin=findViewById(R.id.spinTask);
        taskSpin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,spinCont);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        taskSpin.setAdapter(aa);

    }

    public void save(View view) {
        Intent in=getIntent();
       final String id=in.getStringExtra("mid");
        System.out.println("mid "+id);
        String name=taskName.getText().toString();
        String start=taskStart.getText().toString();
        String end=taskEnd.getText().toString();
        if(TextUtils.isEmpty(name)){
            taskName.setError("this field is required");
        }if(TextUtils.isEmpty(start)){
            taskStart.setError("this field is required");
        }if(TextUtils.isEmpty(end)){
            taskEnd.setError("this field is required");
        }
        if(!TextUtils.isEmpty(end)&&!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(start)){
            Intent intent=new Intent(this,taskActivity.class);
            intent.putExtra("tname",name);
            intent.putExtra("tstart",start);
            intent.putExtra("tend",end);
            intent.putExtra("tspin",s);
            intent.putExtra("tid",id);
            startActivity(intent);
               finish();

        }


    } @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        itemSel(spinCont[position]);
    }

    private void itemSel(String s) {
        this.s=s;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {



    }


}
