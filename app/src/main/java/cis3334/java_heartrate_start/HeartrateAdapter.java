package cis3334.java_heartrate_start;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HeartrateAdapter extends RecyclerView.Adapter<HeartrateAdapter.HeartrateViewHolder> {

    private List<Heartrate> heartrateList;

    public HeartrateAdapter(List<Heartrate> heartrateList) {
        this.heartrateList = heartrateList;
    }

    @NonNull
    @Override
    public HeartrateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.heartrate_row, parent, false);
        return new HeartrateViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HeartrateViewHolder holder, int position) {
        Heartrate current = heartrateList.get(position);

        holder.textViewPulse.setText("Pulse: " + current.getPulse());
        holder.textViewAge.setText("Age: " + current.getAge());
        holder.textViewRange.setText("Range: " + current.getRangeName());
        holder.textViewDescription.setText("Status: " + current.getRangeDescription());
    }

    @Override
    public int getItemCount() {
        return heartrateList.size();
    }

    static class HeartrateViewHolder extends RecyclerView.ViewHolder {
        TextView textViewPulse, textViewAge, textViewRange, textViewDescription;

        public HeartrateViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPulse = itemView.findViewById(R.id.textViewPulse);
            textViewAge = itemView.findViewById(R.id.textViewAge);
            textViewRange = itemView.findViewById(R.id.textViewRange);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
        }
    }
}
