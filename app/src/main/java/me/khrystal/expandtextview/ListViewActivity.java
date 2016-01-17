package me.khrystal.expandtextview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    private ListView mListView;
    private List list = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        mListView = (ListView)findViewById(R.id.list);
        for (int i = 0; i < 50; i++) {
            list.add(i);
        }
        mListView.setAdapter(new MyAdapter());
    }

    class MyAdapter extends BaseAdapter{


        public MyAdapter(){}

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0L;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            if(convertView == null) {
                convertView = LayoutInflater.from(ListViewActivity.this).inflate(R.layout.list_item, viewGroup,false);
            }
            return convertView;
        }
    }
}
