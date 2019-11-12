package com.example.skittles70;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class createProject extends AppCompatActivity {
    EditText projectName;
    EditText projectOwner;
    EditText projectDescription;
    EditText startDate;
    EditText endDate;
    TextView createProject;
    Project project;

    FirebaseDatabase database;
    DatabaseReference ref;

    ArrayList<Project>existingProjects;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);

        existingProjects=new ArrayList<>();


        database= FirebaseDatabase.getInstance();
        ref = database.getReference().child("Projects");

        //project to be added
        project=new Project();




        //set data listener
//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot data: dataSnapshot.getChildren()){
//                    String entry=data.child("projectName").getValue()+"";
//
//                    String k=project.getProjectName();
//
//                    if (entry!=null && k!=null && project!=null && project.getProjectName().equals(entry)) {
//                        Toast.makeText(getApplicationContext(),"Exist",Toast.LENGTH_SHORT).show();
//                        duplicate=true;
//                        break;
//                    } else {
//                        //do something if not exists
//                        duplicate=false;
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//
//        });

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Project p=new Project();
                p.setProjectName(dataSnapshot.child("projectName").getValue().toString());
                existingProjects.add(p);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Project p=new Project();
                p.setProjectName(dataSnapshot.child("projectName").getValue().toString());
                existingProjects.remove(p);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        projectName=(EditText)findViewById(R.id.projectName);
        projectOwner=(EditText)findViewById(R.id.projectOwner);
        projectDescription=(EditText)findViewById(R.id.projectDescription);
        startDate=(EditText)findViewById(R.id.startDate);
        endDate=(EditText)findViewById(R.id.endDate);


        createProject=(TextView)findViewById(R.id.createProject);

        createProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String name=projectName.getText().toString();
                String owner=projectOwner.getText().toString();
                String description=projectDescription.getText().toString();
                String startTime=startDate.getText().toString();
                String endTime=endDate.getText().toString();

                project.setProjectName(name);
                project.setProductOwner(owner);
                project.setProjectDescription(description);
                project.setStartDate(startTime);
                project.setEndDate(endTime);




                //add the project
                if(!existingProjects.contains(project)){
                    ref.push().setValue(project);
                    finish();
                }


                else
                    Toast.makeText(getApplicationContext(),"Project with same name exists",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
