package org.lijun.flightdatacalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;

import org.lijun.flightdatacalculator.entity.Constant;
import org.lijun.flightdatacalculator.scrollsickerview.adapter.ScrollPickerAdapter;
import org.lijun.flightdatacalculator.scrollsickerview.view.ScrollPickerView;
import org.lijun.flightdatacalculator.until.DataProduceUntil;
import org.lijun.flightdatacalculator.until.ViewControlUntil;

public class SpeedSelectorActivity extends Activity {
    private ScrollPickerView mScrollPickerView;
    ScrollPickerAdapter mScrollPickerAdapter;
    ScrollPickerAdapter.ScrollPickerAdapterBuilder<String> builder;
    private String selectBackupDataString = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_speed_selector);
        ViewControlUntil.controlSelectorActivity(this);
        initView();
        initData();
    }

    private void initView() {
        mScrollPickerView = findViewById(R.id.speed_selector_scroll_picker_view);
        mScrollPickerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initData() {

        builder = new ScrollPickerAdapter.ScrollPickerAdapterBuilder<String>(this)
                .setDataList(DataProduceUntil.itemDataListFactory(300, 20, 500))
                .selectedItemOffset(2)
                .visibleItemNumber(5)
                .setDivideLineColor("#E5E5E5")
                .setItemViewProvider(null)
                .setOnClickListener(v -> {
                    selectBackupDataString = (String) v.getTag();
                    backupDataIntent();
                });
        mScrollPickerAdapter = builder.build();
        mScrollPickerView.setAdapter(mScrollPickerAdapter);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                selectBackupDataString = (String) mScrollPickerView.getChildAt(2).getTag();
                backupDataIntent();
            default:
                super.onTouchEvent(event);
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onBackPressed() {
       selectBackupDataString = (String) mScrollPickerView.getChildAt(2).getTag();
       backupDataIntent();
    }

    private void backupDataIntent() {
        Intent intent = new Intent();
        intent.putExtra(Constant.SPEED_SELECT_BACKUP_DATA, selectBackupDataString);
        setResult(RESULT_OK, intent);
        finish();
    }
}