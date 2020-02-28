package com.example.jobs.model;

import android.app.Activity;
import android.content.Context;
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

import com.example.jobs.MainActivity;
import com.example.jobs.R;
import com.example.jobs.taskActivity;

import java.util.List;

public class taskAdapter extends RecyclerView.Adapter<taskAdapter.holder> {
    Context context;
    List<TaskModel> list;
    myAdapter adapter=new myAdapter();
    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View inflate = inflater.inflate(R.layout.taskrecycler, parent, false);
        return new holder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        TaskModel model=list.get(position);
        holder.name.setText("Task Name: "+ model.getTskName());
        holder.end.setText("Start: "+ model.getTskEnd());
        holder.start.setText("End: "+ model.getTskStrt());
        holder.spin.setText("Priority: "+ model.getSpin());

    }

    @Override
    public int getItemCount() {
        return list!=null?list.size():0;
    }

    public void setData(List<TaskModel> list) {
        this.list=list;
        notifyDataSetChanged();
    }

    class holder extends RecyclerView.ViewHolder{
        TextView name,start,end,spin;
        ImageView imageView;

        public holder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.textViewName);
            spin=itemView.findViewById(R.id.textViewSpin);
            start=itemView.findViewById(R.id.textViewStrt);
            end=itemView.findViewById(R.id.textViewEnd);
            imageView=itemView.findViewById(R.id.taskImage);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popupMenu=new PopupMenu(context,imageView);
                    popupMenu.getMenuInflater().inflate(R.menu.task,popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            TaskModel model=list.get(getAdapterPosition());
                            ((taskActivity)context).setAdapterPositio(model);
                            switch (item.getItemId()){
                                case R.id.edit:
                                    ((taskActivity)context).edit();
                                    break;
                                case R.id.delete:
                                    ((taskActivity)context).delete(); break;
                                case R.id.cperiority:
                                    ((taskActivity)context).changePeriority();
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
