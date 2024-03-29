package com.example.starbuzz;

import android.app.Activity;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.ListView;
import android.view.View;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.widget.SimpleCursorAdapter;
import android.widget.CursorAdapter;
import android.widget.Toast;


public class TopLevelActivity extends Activity {
    private SQLiteDatabase db;
    private Cursor favoriteCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level);
        setupOtionsListView();
        setupFavoriteListView();
    }
    private void setupOtionsListView(){
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> listView,
                                    View itemView,
                                    int position,
                                    long id) {
                if (position == 0) {
                    Intent intent = new Intent(TopLevelActivity.this, DrinkCategoryActivity.class);
                    startActivity(intent);
                }
            }
            };

        ListView listView = (ListView) findViewById(R.id.list_options);
        listView.setOnItemClickListener(itemClickListener);
    }
    private  void setupFavoriteListView(){
        ListView list_favorites = (ListView)findViewById(R.id.list_favorite);
        try {
            SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
            db = starbuzzDatabaseHelper.getReadableDatabase();
            favoriteCursor = db.query("DRINK",new String[]{"_id","NAME"},"FAVORITE = 1",null,null,null,null);
            CursorAdapter favoriteAdapter = new SimpleCursorAdapter(TopLevelActivity.this,android.R.layout.simple_list_item_1,favoriteCursor,new String[]{"NAME"},new int[]{android.R.id.text1},0);
            list_favorites.setAdapter(favoriteAdapter);
        }catch (SQLiteException e){
            Toast toast = Toast.makeText(this,"DatabaseUnavailabe",Toast.LENGTH_SHORT);
            toast.show();
        }
        list_favorites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View v, int position, long id) {
                Intent intent = new Intent(TopLevelActivity.this,DrinkActivity.class);
                intent.putExtra(DrinkActivity.EXTRA_DRINKID,(int)id);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onRestart(){
        super.onRestart();
        Cursor newCursor = db.query("DRINK",new String[]{"_id","NAME"},"FAVORITE = 1",null,null,null,null);
        ListView listFavorites = (ListView)findViewById(R.id.list_favorite);
        CursorAdapter adapter = (CursorAdapter) listFavorites.getAdapter();
        adapter.changeCursor(newCursor);
        favoriteCursor = newCursor;

    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        favoriteCursor.close();
        db.close();
    }
}

