package com.yuchenhou.skynow.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.yuchenhou.skynow.R;
import com.yuchenhou.skynow.databinding.ViewNextEventBinding;
import com.yuchenhou.skynow.model.Event;
import com.yuchenhou.skynow.presenter.NextEventPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusAppCompatActivity;

@RequiresPresenter(NextEventPresenter.class)
public class ListEventActivity extends NucleusAppCompatActivity<NextEventPresenter> {
    @Bind(R.id.next_event_container)
    FrameLayout mNextEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
        if (savedInstanceState == null) {
            getPresenter().requestEvent();
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

    public void setNextEvent(Event nextEvent) {
        ViewNextEventBinding view = DataBindingUtil.inflate(getLayoutInflater(), R.layout.view_next_event, mNextEvent, false);
        view.setEvent(nextEvent);
        mNextEvent.addView(view.getRoot());
    }
}
