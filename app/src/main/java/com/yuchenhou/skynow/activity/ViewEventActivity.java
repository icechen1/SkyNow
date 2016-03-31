package com.yuchenhou.skynow.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.yuchenhou.skynow.R;
import com.yuchenhou.skynow.fragment.ViewEventFragment;
import com.yuchenhou.skynow.model.Event;
import com.yuchenhou.skynow.presenter.ViewEventPresenter;

import nucleus.view.NucleusAppCompatActivity;

public class ViewEventActivity extends NucleusAppCompatActivity<ViewEventPresenter> {
    public final static String EVENT = "EVENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> startActivity(new Intent(this, ListEventActivity.class)));

        if(getIntent().getExtras() != null) {
            Event ev = getIntent().getExtras().getParcelable(EVENT);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, ViewEventFragment.newInstance(ev))
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
