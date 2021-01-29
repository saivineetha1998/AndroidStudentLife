package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Activity2 extends AppCompatActivity {
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button btnDisplay;
    DatabaseReference reff;
    MoodTrack moodTrack;
    long maxid=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        radioGroup = (RadioGroup) findViewById(R.id.radio);
        btnDisplay = (Button) findViewById(R.id.mood);
        moodTrack = new MoodTrack();
        reff = FirebaseDatabase.getInstance().getReference().child("Member");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                    maxid = (snapshot.getChildrenCount());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btnDisplay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);
                moodTrack.setUserMood(radioButton.getText().toString().trim());
                //reff.child("UserMoodTracking").setValue(moodTrack);
                reff.child(String.valueOf(maxid+1)).setValue(moodTrack);

                Toast.makeText(Activity2.this, "data inserted successfully", Toast.LENGTH_LONG).show();



                //Toast.makeText(Activity2.this,
                       // radioButton.getText(), Toast.LENGTH_SHORT).show();

            }

        });

    }
}