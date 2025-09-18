package cis3334.java_heartrate_start;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextPulse, editTextAge;
    private Button buttonInsert;
    private RecyclerView recyclerViewHeartrate;
    private HeartrateAdapter adapter;
    private List<Heartrate> heartrateList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextPulse = findViewById(R.id.editTextPulse);
        editTextAge = findViewById(R.id.editTextAge);
        buttonInsert = findViewById(R.id.buttonInsert);
        recyclerViewHeartrate = findViewById(R.id.recyclerViewHeartrate);

        heartrateList = new ArrayList<>();
        adapter = new HeartrateAdapter(heartrateList);

        recyclerViewHeartrate.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewHeartrate.setAdapter(adapter);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int pulse = Integer.parseInt(editTextPulse.getText().toString());
                    int age = Integer.parseInt(editTextAge.getText().toString());
                    Heartrate newHeart = new Heartrate(pulse, age);
                    heartrateList.add(newHeart);
                    adapter.notifyItemInserted(heartrateList.size() - 1);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
