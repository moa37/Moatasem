package com.example.jobs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jobs.Database.DataBase;
import com.example.jobs.model.Data;
import com.example.jobs.model.myAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ImageView addImage;
    RecyclerView recyclerView;
    myAdapter adapter;
    SQLiteDatabase liteDatabase;
    String[] colms={DataBase.JOB_NAME,DataBase.END_DATE,DataBase.SPINNER,DataBase.START_DATE,DataBase.COL_ID};
    private Data data;
    String nameC;
    String strtC;
    String endC,s;
    String spinC;
    EditText names,start,end;
    Spinner spinner;
    String spinCont[]={"High","Low"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataBase dataBase=new DataBase(this);
        liteDatabase = dataBase.getWritableDatabase();
        initComponent();
        recyclerView=findViewById(R.id.recyclerView);
        GridLayoutManager manager=new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(manager);
         adapter=new myAdapter();
        recyclerView.setAdapter(adapter);
        DividerItemDecoration dv=new DividerItemDecoration(this,manager.getOrientation());
        recyclerView.addItemDecoration(dv);
        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        String start=intent.getStringExtra("start");
        String end=intent.getStringExtra("end");
        String spin=intent.getStringExtra("spin");
        if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(start)&&!TextUtils.isEmpty(spin)&&!TextUtils.isEmpty(end)){
        saveINDB(name,start,end,spin);}
    }

    @Override
    protected void onStart() {
        super.onStart();
        ViewAllNots();
    }

    private void saveINDB(String name, String startText, String endText, String spinText) {
        ContentValues values=new ContentValues();
        values.put(DataBase.JOB_NAME,name);
        values.put(DataBase.START_DATE,startText);
        values.put(DataBase.END_DATE,endText);
        values.put(DataBase.SPINNER,spinText);
        long row = liteDatabase.insert(DataBase.TABLE_NAME, null, values);
        if(row>0){
            Toast.makeText(this,"done",Toast.LENGTH_LONG).show();
            ViewAllNots();
        }
    }

    private void ViewAllNots() {
        Cursor cursor=liteDatabase.query(DataBase.TABLE_NAME,colms,null,null,null,null,null);
        viewonRecyclerView(cursor);
    }
    private void viewonRecyclerView(Cursor cursor) {
        List<Data> list=new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                int colmid=cursor.getColumnIndex(DataBase.COL_ID);
                 int ids=cursor.getInt(colmid);
                nameC=cursor.getString(cursor.getColumnIndex(DataBase.JOB_NAME));
                strtC=cursor.getString(cursor.getColumnIndex(DataBase.START_DATE));
                endC=cursor.getString(cursor.getColumnIndex(DataBase.END_DATE));
                spinC=cursor.getString(cursor.getColumnIndex(DataBase.SPINNER));
                list.add(new Data(nameC,strtC,endC,spinC,ids));
            }while (cursor.moveToNext());
            adapter.setData(list);

        }
    }

    private void initComponent() {
        final Intent intent=new Intent(this,addJob.class);
        addImage=findViewById(R.id.addJ);
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(intent);
                finish();
            }
        });

    }

    public void edit() {


        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Edit Job")
                .setView(R.layout.dia)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String Tname=names.getText().toString();
                        String Tstrt=start.getText().toString();
                        String Tend=end.getText().toString();
                        if(TextUtils.isEmpty(Tname)){
                            names.setError("this field is required");
                        }if(TextUtils.isEmpty(Tstrt)){
                            start.setError("this field is required");
                        }if(TextUtils.isEmpty(Tend)){
                            end.setError("this field is required");
                        }
                        if(!TextUtils.isEmpty(Tend)&&!TextUtils.isEmpty(Tname)&&!TextUtils.isEmpty(Tstrt)){
                        updateData(Tname,Tstrt,Tend);}


                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog=builder.show();
        names=dialog.findViewById(R.id.name);
        start=dialog.findViewById(R.id.Dstrt);
        end=dialog.findViewById(R.id.end);
        names.setText(data.getName());
        start.setText(data.getStrt());
        end.setText(data.getEnd());



    }

    private void updateData(String tname, String tstrt, String tend) {
        ContentValues values=new ContentValues();
        values.put(DataBase.JOB_NAME,tname);
        values.put(DataBase.START_DATE,tstrt);
        values.put(DataBase.END_DATE,tend);

        int update = liteDatabase.update(DataBase.TABLE_NAME, values, DataBase.COL_ID + " =?", new String[]{String.valueOf(data.getId())});
        if (update > 0) {
            ViewAllNots();
        }
    }

    public void delete() {
        int row = liteDatabase.delete(DataBase.TABLE_NAME, DataBase.COL_ID + " =?", new String[]{String.valueOf(data.getId())});
        if(row>0){
            ViewAllNots();
        }

    }

    public void setAdapteratPosition(Data data) {
        this.data=data;
    }


    public void changePeriority() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setView(R.layout.per)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        updatper(s);
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog=builder.show();
        spinner=dialog.findViewById(R.id.spinner3);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,spinCont);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);

    }

    private void updatper(String s) {
        ContentValues values=new ContentValues();
        values.put(DataBase.SPINNER,s);
        int update = liteDatabase.update(DataBase.TABLE_NAME, values, DataBase.COL_ID + " =?", new String[]{String.valueOf(data.getId())});
        if(update>0){
            ViewAllNots();
        }
    }

    @Override
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
