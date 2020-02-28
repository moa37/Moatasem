package com.example.jobs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.GestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jobs.Database.DBtask;
import com.example.jobs.Database.DataBase;
import com.example.jobs.model.Data;
import com.example.jobs.model.TaskModel;
import com.example.jobs.model.myAdapter;
import com.example.jobs.model.taskAdapter;

import java.util.ArrayList;
import java.util.List;

public class taskActivity extends AppCompatActivity {
    ImageView imageView;
    RecyclerView recyclerView;
    SQLiteDatabase db;
    String []colms={DBtask.Task_NAME,DBtask.START_TDATE,DBtask.END_TDATE,DBtask.TSPINNER,DBtask.Task_ID,DBtask.tasble_ID};
    TaskModel model=new TaskModel();

    taskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        DBtask dBtask=new DBtask(this);
         db = dBtask.getWritableDatabase();
        initComponent();
        setRecyclerView();
        getDataFromaddtask();

    }
    @Override
    protected void onStart() {
        super.onStart();
        ViewAllNotes();
    }
    private void setRecyclerView() {
        recyclerView=findViewById(R.id.recTask);
        GridLayoutManager manager=new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(manager);
         adapter=new taskAdapter();
        recyclerView.setAdapter(adapter);
        DividerItemDecoration decoration=new DividerItemDecoration(this,manager.getOrientation());
        recyclerView.addItemDecoration(decoration);
    }

    private void getDataFromaddtask() {
        Intent intent=getIntent();
        String tname = intent.getStringExtra("tname");
        String tstart = intent.getStringExtra("tstart");
        String tend = intent.getStringExtra("tend");
        String spin = intent.getStringExtra("tspin");
       String  id=intent.getStringExtra("tid");
   //     System.out.println("id is "+ id);
        if(!TextUtils.isEmpty(tend)&&!TextUtils.isEmpty(tname)&&!TextUtils.isEmpty(tstart)){

            saveInDataBase(tname,tstart,tend,spin,id);
        }


    }

    private void saveInDataBase(String tname, String tstart, String tend, String spin, String id) {
        ContentValues values=new ContentValues();
        values.put(DBtask.tasble_ID,id);
       // System.out.println("database ididid "+id);
        values.put(DBtask.Task_NAME,tname);
     //   System.out.println("database "+tname);
        values.put(DBtask.START_TDATE,tstart);
        values.put(DBtask.END_TDATE,tend);
        values.put(DBtask.TSPINNER,spin);

        long insert = db.insert(DBtask.TABLE_NAME, null, values);
        if(insert>0){
            Toast.makeText(this,"done",Toast.LENGTH_LONG).show();
            ViewAllNotes();
        }


    }

    private void ViewAllNotes() {
        Intent intent=getIntent();
        String id=intent.getStringExtra("ID");
       String selectQuery = "SELECT  * FROM " + DBtask.TABLE_NAME+" where "+DBtask.tasble_ID+" = "+id ;
        System.out.println("id "+id);
      Cursor cursor=db.rawQuery(selectQuery,null);
//        Cursor cursor=db.query(DBtask.TABLE_NAME,colms,null,null,null,null,null);
        ViewInRecyclerView(cursor);
    }

    private void ViewInRecyclerView(Cursor cursor) {
        List<TaskModel> list=new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                int id=cursor.getInt(cursor.getColumnIndex(DBtask.Task_ID));
                String tableId=cursor.getString(cursor.getColumnIndex(DBtask.tasble_ID));
                String name=cursor.getString(cursor.getColumnIndex(DBtask.Task_NAME));
                String start=cursor.getString(cursor.getColumnIndex(DBtask.START_TDATE));
                String end=cursor.getString(cursor.getColumnIndex(DBtask.END_TDATE));
                String spin=cursor.getString(cursor.getColumnIndex(DBtask.TSPINNER));
                list.add(new TaskModel(name,start,end,spin,id,tableId));
            }while (cursor.moveToNext());
            adapter.setData(list);
        }
    }

    private void initComponent() {
        imageView=findViewById(R.id.imageTask);
        final Intent intent=new Intent(this,addtask.class);
        Intent intent1=getIntent();

        final   String id=intent1.getStringExtra("ID");
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("mid",id);
               startActivity(intent);
            }
        });
    }


    public void edit() {
       // db.update(DBtask.TABLE_NAME,)
    }

    public void delete() {
        int delete = db.delete(DBtask.TABLE_NAME, DBtask.Task_ID + " =? ", new String[]{String.valueOf(model.getTskId())});
        if(delete>0){
            ViewAllNotes();
        }
    }

    public void changePeriority() {
    }

    public void setAdapterPositio(TaskModel model) {
        this.model=model;
    }
}
