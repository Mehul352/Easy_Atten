package com.example.easy_atten;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
   private ArrayList<StudentInformation> studentInformation;
   private ArrayList<StudentInformation> studentInformationAll;
    private ArrayList checkedItems = new ArrayList();
    private Itemclicked activity;

    public interface Itemclicked
    {
        void onItemClicked(int index);
    }
    StudentAdapter(Context context, ArrayList<StudentInformation> List)
    {
        studentInformation = List;
        studentInformationAll = new ArrayList<>(List);
        activity = (Itemclicked) context;
    }
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        CheckBox check_Box;
        TextView tvName,tvRollno;
        ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_Name);
            tvRollno = itemView.findViewById(R.id.tv_Rollno);
            check_Box = itemView.findViewById(R.id.check_Box);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onItemClicked(studentInformation.indexOf((StudentInformation)v.getTag()));
                }
            });
        }
    }

    //search bar



//    @Override
//    public Filter getFilter() {
//
//        return filter;
//    }
//    private Filter filter = new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence charSequence) {
//            ArrayList<StudentInformation> filteredList = new ArrayList<>();
//            if(charSequence.toString().isEmpty())
//            {
//                filteredList.addAll(studentInformationAll);
//            }else {
//                for (StudentInformation std : studentInformationAll){
//                    if(std.toLowerCase().contains(charSequence.toString().toLowerCase())){
//                        filteredList.add(std);
//                    }
//                }
//            }
//            FilterResults filterResults = new FilterResults();
//            filterResults.values = filteredList;
//
//            return filterResults;
//        }
//
//        @Override
//        protected void publishResults(CharSequence charSequence, FilterResults results) {
//             studentInformation.clear();
//             studentInformation.addAll((Collection<? extends StudentInformation>) results.values);
//        }
//    };
    @NonNull
    @Override
    public StudentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_items, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.ViewHolder holder, int position) {
       holder.itemView.setTag(studentInformation.get(position));
       holder.tvName.setText(studentInformation.get(position).getName());
       holder.tvRollno.setText(studentInformation.get(position).getRollno());


    }

    @Override
    public int getItemCount() {
        return studentInformation.size();
    }

}
