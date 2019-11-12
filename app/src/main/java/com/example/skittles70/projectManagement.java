package com.example.skittles70;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class projectManagement extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference ref;
    LinearLayout listProjects;
    Button createProject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_management2);

        listProjects=(LinearLayout)findViewById(R.id.projectList);
        createProject=(Button)findViewById(R.id.createProject);

        createProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newProject=new Intent(getApplicationContext(),createProject.class);
                startActivity(newProject);
            }
        });


        database= FirebaseDatabase.getInstance();

        ref = database.getReference().child("Projects");

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                addProject(dataSnapshot.child("projectName").getValue().toString());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addProject(String name){
        LinearLayout card=new LinearLayout(this);
        card=(LinearLayout) card.inflate(this,R.layout.listproject,null);
        final TextView projectIndicator=(TextView)card.findViewById(R.id.slot);
        projectIndicator.setText(name);

        projectIndicator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String projectName=projectIndicator.getText().toString();

                Intent details=new Intent(getApplicationContext(),projectDetails.class);
                details.putExtra("name",projectName);
                startActivity(details);

            }
        });

        listProjects.addView(card);
    }

    public void removeProject(){

    }


}
