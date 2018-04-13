package fcc.com.mydynamodbdemo;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;

import java.util.ArrayList;

import fcc.com.mydynamodbdemo.dao.LoadStudentsAsyncTask;
import fcc.com.mydynamodbdemo.dao.OnGetStudentsFinishedListener;
import fcc.com.mydynamodbdemo.model.Student;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private RecyclerView recycleView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager recycleViewLayoutManager;

    private ArrayList<Student> mStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);

        getDataFromDymoDB();
    }

    private void init()
    {
        recycleView = (RecyclerView)findViewById(R.id.recycler_view);
        recycleView.setHasFixedSize(true);

        recycleViewLayoutManager = new LinearLayoutManager(this);
        recycleView.setLayoutManager(recycleViewLayoutManager);

        myAdapter = new StudentRecyclerViewAdapter(mStudents);
        recycleView.setAdapter(myAdapter);
    }

    private void getDataFromDymoDB()
    {
        new LoadStudentsAsyncTask(this, new OnGetStudentsFinishedListener() {
            @Override
            public void onRetreiveData(PaginatedList result) {
                if(result != null)
                {
                    mStudents = new ArrayList<Student>(result);
                    init();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "no students", Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        }).execute();
    }
}
