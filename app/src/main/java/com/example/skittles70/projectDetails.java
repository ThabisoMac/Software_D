package com.example.skittles70;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class projectDetails extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference ref;
    String projectName;
    Project project;

    TextView name,owner,description,startDate,endDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);

        projectName=getIntent().getStringExtra("name");
        database= FirebaseDatabase.getInstance();

        name=(TextView)findViewById(R.id.projectName);
        owner=(TextView)findViewById(R.id.projectOwner);
        description=(TextView)findViewById(R.id.projectDescription);
        startDate=(TextView)findViewById(R.id.startDate);
        endDate=(TextView)findViewById(R.id.endDate);
        ref = database.getReference().child("Projects");

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.child("projectName").getValue().equals(projectName)){
                    project=new Project();

                    project.setProjectName(projectName);
                    project.setProductOwner(dataSnapshot.child("productOwner").getValue().toString());
                    project.setStartDate(dataSnapshot.child("startDate").getValue().toString());
                    project.setProjectDescription(dataSnapshot.child("projectDescription").getValue().toString());
                    project.setStartDate(dataSnapshot.child("startDate").getValue().toString());
                    project.setEndDate(dataSnapshot.child("endDate").getValue().toString());

                    name.append(" : "+project.getProjectName());
                    owner.append(" : "+project.getProductOwner());
                    description.append(project.getProjectDescription());
                    startDate.setText(project.getStartDate());
                    endDate.setText(project.getEndDate());

                }
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
}
