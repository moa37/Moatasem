package com.example.jobs.model;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobs.Database.DBtask;
import com.example.jobs.MainActivity;
import com.example.jobs.taskActivity;
import com.example.jobs.R;

import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myAdapter.holder> {
    List<Data> list;
    Context context;
int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recview, parent, false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        final Data data=list.get(position);
        holder.job.setText("Job Name: "+data.getName());
        holder.start.setText("Start :"+data.getStrt());
        holder.end.setText("End :"+data.getEnd());
        holder.spin.setText("Periority :"+data.getSpin());
       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent =new Intent(context,taskActivity.class);
               intent.putExtra("ID",String.valueOf(data.getId()));
               context.startActivity(intent);
           }
       });


    }



    @Override
    public int getItemCount() {
        return list!=null?list.size():0;
    }

    public void setData(List<Data> list) {
        this.list=list;
        notifyDataSetChanged();
    }

    class holder extends RecyclerView.ViewHolder{
        TextView job,start,end,spin;
        ImageView imageView;

        public holder(@NonNull final View itemView) {
            super(itemView);
            job=itemView.findViewById(R.id.textJob);
            start=itemView.findViewById(R.id.textStart);
            end=itemView.findViewById(R.id.EndDate);
            spin=itemView.findViewById(R.id.SpinnerText);
            imageView=itemView.findViewById(R.id.menu);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popupMenu=new PopupMenu(context,imageView);
                    popupMenu.getMenuInflater().inflate(R.menu.main,popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            Data data=list.get(getAdapterPosition());
                            ((MainActivity)context).setAdapteratPosition(data);
                            switch (item.getItemId()){
                                case R.id.eJ:
                                    ((MainActivity)context).edit();
                                    break;
                                case R.id.dJ:
                                    ((MainActivity)context).delete();
                                    break;
                                case R.id.cH:
                                    ((MainActivity)context).changePeriority();
                                    break;
                            }

                            return true;
                        }
                    });
                    popupMenu.show();

                }
            });

        }
    }
}
