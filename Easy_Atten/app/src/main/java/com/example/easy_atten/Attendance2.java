package com.example.easy_atten;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import static android.widget.Toast.LENGTH_SHORT;


public class Attendance2 extends AppCompatActivity implements StudentAdapter.Itemclicked {

    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    Integer position=-1;
    Button submit,saveDrag,cancel;
    CheckBox cb;
    TextView rollno;


    public ArrayList<StudentInformation> stdinfo=new ArrayList  <StudentInformation>();
    public HashMap<String,Integer> hp=new HashMap<String,Integer>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance2);
        Intent intent=getIntent();
        final String pos=intent.getStringExtra("position");
        position=Integer.parseInt(pos.trim());
        submit=findViewById(R.id.submit_attendance);
        cancel=findViewById(R.id.delete_attendance);
        rollno = findViewById(R.id.tv_Rollno);
        cb = findViewById(R.id.check_Box);
        recyclerView = findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        //*****
        myAdapter = new StudentAdapter(this,stdinfo);
        recyclerView.setAdapter(myAdapter);

        try{
            FirebaseDatabase database=FirebaseDatabase.getInstance();
            DatabaseReference databaseReference=database.getReference();
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    try {

                        Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                        for (DataSnapshot child : children) {
                            StudentInformation std = child.getValue(StudentInformation.class);

                            //check for subjects
                            if(std.getSubjects().get(Integer.parseInt(pos))!=-1) {
                                stdinfo.add(std);
                            }Log.e("String is", std.getName());
                        }
                        fn(pos);
                    } catch (Exception e) {
                        Log.e("Exception is", e.toString());
                    }
                }

                private void fn(String pos)
                {
                    int siz= stdinfo.size();
                    StudentAdapter sa=new StudentAdapter(Attendance2.this,stdinfo);
                    recyclerView.setAdapter(sa);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }catch(Exception e){
            Log.e("Exception is",e.toString());
        }

//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
//        itemTouchHelper.attachToRecyclerView(recyclerView);
//        saveDrag = findViewById(R.id.save_dragandrop);
//        saveDrag.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                savedraganddrop();
//            }
//        });



    }

//    private void savedraganddrop()
//    {
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("StudentInformation");
//        HashMap<String, Integer> hp = new HashMap<>();
//        hp.put("numberArranging",);
//        ref.child("StudentInformation").updateChildren();
//        Toast.makeText(this, "List Arrange Successfully", Toast.LENGTH_SHORT).show();
//    }



    public void submit(View view) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(Attendance2.this);
        builder.setCancelable(false);
        builder.setTitle("SUBMIT");
        builder.setMessage("Are you sure you want to submit the attendance?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    int siz=stdinfo.size();
                    // putting every students attendance details
                    for(int i=0;i<siz;i++){
                        try{
                            hp.put(stdinfo.get(i).getRollno(),stdinfo.get(i).getSubjects().get(position));
                        }catch(Exception e){
                            Log.e("Exception is",e.toString());
                        }
                    }

                    for(int i=0;i<siz;i++){
                        try {
                            View view1 = recyclerView.getChildAt(i);
                            String rollnum = ((TextView) view1.findViewById(R.id.tv_Rollno)).getText().toString().trim();
                            CheckBox cb = view1.findViewById(R.id.check_Box);
                            Integer totalAttendance = hp.get(rollnum);
                            if (cb.isChecked()) {
                                hp.put(rollnum, totalAttendance + 1001);
                                Log.e(rollnum, " is present, attendance is " + hp.get(rollnum));
                            } else {
                                hp.put(rollnum, totalAttendance + 1000);
                                Log.e(rollnum, " is absent");
                            }
                        }catch (Exception e ){
                            Log.e("Exception is",e.toString());

                        }
                    }
                } catch (Exception e){
                    Log.e("Exception is",e.toString());

                }


                // Marking attendance and storeing into database
                try{
                    FirebaseDatabase database=FirebaseDatabase.getInstance();
                    DatabaseReference databaseReference=database.getReference();
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public  void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            try {

                                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                                for (DataSnapshot child : children) {
                                    StudentInformation std = child.getValue(StudentInformation.class);
                                    String rollno=std.getRollno().trim();
                                    if(hp.containsKey(rollno)){
                                        int attendance=hp.get(rollno);
                                        child.getRef().child("subjects").child(String.valueOf(position)).setValue(attendance);

                                    }
                                }
                            } catch (Exception e) {
                                Log.e("Exception is", e.toString());
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }catch(Exception e){
                    Log.e("Exception is",e.toString());
                }
                finish();
                Toast.makeText(getApplicationContext(), "Attendance Complete", LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });
        builder.create().show();


    }

    public void selectallcheckbox(View view) {

    }

    @Override
    public void onItemClicked(int index) {

        Toast tos =  Toast.makeText(this, "Submitted Attendance: "+"\n"+stdinfo.get(index).getSubjects(), Toast.LENGTH_SHORT);
        tos.setGravity(Gravity.CENTER_VERTICAL | Gravity.START, 180, 0);
        ViewGroup group = (ViewGroup) tos.getView();
        TextView messageTextView = (TextView) group.getChildAt(0);
        messageTextView.setTextSize(20);
        messageTextView.setTextColor(Color.DKGRAY);
        tos.show();

        //Drag and drop feature...
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);


    }
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN |
            ItemTouchHelper.START | ItemTouchHelper.END,0) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            int fromPosition = viewHolder.getAdapterPosition();
            int toPosition = target.getAdapterPosition();
            Collections.swap(stdinfo,fromPosition,toPosition);
            recyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        }
    };


    public void cancel(View view) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("DELETE");
        builder.setMessage("Are you sure you want to delete the attendance?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                int siz=stdinfo.size();

                // putting every students attendance details
                for(int i=0;i<stdinfo.size();i++){
                    try{
                        hp.put(stdinfo.get(i).getRollno(),stdinfo.get(i).getSubjects().get(position));
                    }catch(Exception e){
                        Log.e("Exception is",e.toString());
                    }
                }
                for(int i=0;i<stdinfo.size();i++){
                    try {
                        View view1 = recyclerView.getChildAt(i);
                        String rollno = ((TextView) view1.findViewById(R.id.tv_Rollno)).getText().toString().trim();
                        CheckBox cb = view1.findViewById(R.id.check_Box);
                        Integer totalAttendance = hp.get(rollno);
                        if (cb.isChecked() ) {
                            hp.put(rollno, totalAttendance + -0001);
                            Log.e(rollno, " is present, attendance is " + hp.get(rollno));
                        } else{
                            hp.put(rollno, totalAttendance );
                            Log.e(rollno, " is absent");
                        }
                    }catch (Exception e ){
                        Log.e("Exception is",e.toString());

                    }
                }
                // Marking attendance and storeing into database
                try{
                    FirebaseDatabase database=FirebaseDatabase.getInstance();
                    DatabaseReference databaseReference=database.getReference();
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public  void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            try {

                                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                                for (DataSnapshot child : children) {
                                    StudentInformation std = child.getValue(StudentInformation.class);
                                    String rollno=std.getRollno().trim();
                                    if(hp.containsKey(rollno)){
                                        int attendance=hp.get(rollno);
                                        child.getRef().child("subjects").child(String.valueOf(position)).setValue(attendance);

                                    }
                                }
                            } catch (Exception e) {
                                Log.e("Exception is", e.toString());
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }catch(Exception e){
                    Log.e("Exception is",e.toString());
                }
                finish();
                Toast.makeText(getApplicationContext(), "Attendance Deleted", LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}
