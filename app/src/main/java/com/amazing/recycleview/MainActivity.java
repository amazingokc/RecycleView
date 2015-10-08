package com.amazing.recycleview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private RecyclerView mRecycleView;
    private ArrayList<String> mDatas;
    private simpleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        initDatas();
        initViews();
        mAdapter = new simpleAdapter(this, mDatas);
        mRecycleView.setAdapter(mAdapter);

        //设置recycleview的布局管理
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mRecycleView.setLayoutManager(linearLayoutManager);
        mRecycleView.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setmOnItemClickListener(new simpleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "click:" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(MainActivity.this, position + "longclick" + position, Toast.LENGTH_SHORT).show();
            }
        });

        //设置Item的分隔线
//        mRecycleView.addItemDecoration(new DividerItemDecoration(this,
//                DividerItemDecoration.VERTICAL_LIST));
    }

    private void initViews() {

        mRecycleView = (RecyclerView) findViewById(R.id.id_recycleview);
    }

    private void initDatas() {
        mDatas = new ArrayList<String>();

        for(int i = 'A' ; i <= 'Z' ; i++) {
            mDatas.add("" + (char)i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.action_add:
                mAdapter.addData(1);

                break;
            case R.id.action_delete:
                mAdapter.deleteData(1);

                break;
            case R.id.action_gridview:
                mRecycleView.setLayoutManager(new GridLayoutManager(this, 3));

                break;
            case R.id.action_listview:
                mRecycleView.setLayoutManager(new LinearLayoutManager(this));

                break;
            case R.id.action_staggered:
                Intent intent = new Intent(this, StaggeredGridLayoutActivity.class);
                startActivity(intent);

                break;
            case R.id.action_hor_gridview:
                mRecycleView.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.
                        HORIZONTAL));

                break;

        }
        return super.onOptionsItemSelected(item);
    }


}
