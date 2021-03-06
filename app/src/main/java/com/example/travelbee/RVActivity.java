package com.example.travelbee;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.travelbee.adapters.RVAdapter;
import com.example.travelbee.models.ToDo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RVActivity extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    RVAdapter adapter;
    DAOTask dao;
    boolean isLoading=false;
    String key =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rvactivity);
        swipeRefreshLayout =findViewById(R.id.swipe);
        recyclerView = findViewById(R.id.rv);


        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
         adapter = new RVAdapter(this);
         recyclerView.setAdapter(adapter);
         dao = new DAOTask();
         loadData();
         recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
             @Override
             public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

                 LinearLayoutManager linearLayoutManager= (LinearLayoutManager) recyclerView.getLayoutManager();
                 int totalItem = linearLayoutManager.getItemCount();
                 int lastVisible = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                 if(totalItem< lastVisible+3){
                     if(! isLoading){

                         isLoading=true;
                         loadData();
                     }
                 }
             }
         });
         
         
    }

    private void loadData() {

        swipeRefreshLayout.setRefreshing(true);

        dao.get(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<ToDo> todos = new ArrayList<>();
                for(DataSnapshot data : snapshot.getChildren())
                {
                    ToDo todo =data.getValue(ToDo.class);
                    todo.setKey(data.getKey());
                    todos.add(todo);
                    key =data.getKey();
                }
                adapter.setItems(todos);
                adapter.notifyDataSetChanged();
                isLoading=false;
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                swipeRefreshLayout.setRefreshing(false);
            }


        });
    }
}
/*   private void loadData() {

        swipeRefreshLayout.setRefreshing(true);

        dao.get(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<ToDo> todos = new ArrayList<>();
                for(DataSnapshot data : snapshot.getChildren())
                {
                     ToDo todo =data.getValue(ToDo.class);
                     todo.setKey(data.getKey());
                     todos.add(todo);
                     key =data.getKey();
                }
                adapter.setItems(todos);
                adapter.notifyDataSetChanged();
                isLoading=false;
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}*/