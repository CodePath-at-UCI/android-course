package com.codepath.basictodo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {

    // This is where you will initialize all of the compoenents that will be used in this activity
    List<String> items;
    Button btndAdd;
    EditText etItem;
    RecyclerView rvItems;
    ItemsAdapter itemsAdapter;
    ImageView ivTest;


    // onCreate is the first function that is called when the MainActivity(and any activity) starts
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        findViewbyId will link the items we initialized to the components in
        the layout we designed
         */

        ivTest = findViewById(R.id.ivTest);

        btndAdd=findViewById(R.id.btnAdd);
        etItem=findViewById(R.id.etItem);
        rvItems=findViewById(R.id.rvItems);
        loadItems();
        ItemsAdapter.OnLongClicklistener onLongClickListener= new ItemsAdapter.OnLongClicklistener()
        {
            @Override
            public void onItemLongClicked(int position) {
                //delete the item from the model
                items.remove(position);

                //notify the adapter
                itemsAdapter.notifyItemRemoved(position);
                Toast.makeText(getApplicationContext(),"Item was removed",Toast.LENGTH_SHORT).show();
                saveItems();

            }
        };

        itemsAdapter=new ItemsAdapter(items,onLongClickListener);

        //
        // we set itemsAdapter to be the adapter for rvItems recyclerview
        rvItems.setAdapter(itemsAdapter);
        rvItems.setLayoutManager(new LinearLayoutManager(this));

        //here we give btnAdd some functionality
        btndAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todoItem =etItem.getText().toString();
                // add item to the model
                items.add(todoItem);

                itemsAdapter.notifyItemInserted(items.size()-1);
                etItem.setText("");
                Toast.makeText(getApplicationContext(),"Item was added",Toast.LENGTH_SHORT).show();
                saveItems();
            }
        });

    }
    private File getDataFile()
    {
        return new File(getFilesDir(),"data.txt");
    }

    //this function will load items by reading every line of the data file
    // this function isaves items by writing them into the data file

    private void loadItems()
    {
        try {
            items = new ArrayList<>(FileUtils.readLines(getDataFile(),Charset.defaultCharset()));

        }
        catch (IOException e){
            Log.e("MainActivity","Error reading Items",e);
            items=new ArrayList<>();

        }
    }
    private void saveItems()
    {
        try {
            FileUtils.writeLines(getDataFile(),items);
        } catch (IOException e) {
            Log.e("MainActivity","Error writing Items",e);
        }

    }
}
