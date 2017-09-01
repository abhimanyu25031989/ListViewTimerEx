package example.com.abhi.listviewtimerex;

import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by abhimanyu on 29/8/17.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {


    private ArrayList al_data;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_timer;
        CountDownTimer timer;

        public MyViewHolder (View view){
            super(view);
            tv_timer = (TextView)view.findViewById(R.id.tv_timer);

        }

    }

    public Adapter(ArrayList al_data) {
        this.al_data = al_data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_layout,parent,false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        holder.tv_timer.setText(String.valueOf(al_data.get(position)));

        if (holder.timer != null) {
            holder.timer.cancel();
        }
        long timer = Long.parseLong(String.valueOf(al_data.get(position)));

        timer = timer*1000;

        holder.timer = new CountDownTimer(timer, 1000) {
            public void onTick(long millisUntilFinished) {
                holder.tv_timer.setText("" + millisUntilFinished/1000 + " Sec");
            }

            public void onFinish() {
                holder.tv_timer.setText("00:00:00");
            }
        }.start();


    }

    @Override
    public int getItemCount() {
        return al_data.size();
    }
}
