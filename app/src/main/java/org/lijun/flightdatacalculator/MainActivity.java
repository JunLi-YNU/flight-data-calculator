package org.lijun.flightdatacalculator;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.ims.RegistrationManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.lijun.flightdatacalculator.entity.Constant;
import org.lijun.flightdatacalculator.entity.DistanceSpeedTime;
import org.lijun.flightdatacalculator.service.DistanceSpeedTimeCalculator;
import org.lijun.flightdatacalculator.serviceimpl.DistanceSpeedTimeCalculatorImpl;


public class MainActivity extends AppCompatActivity {

    private EditText editTextNumberDistanceForTime;
    private EditText editTextNumberSpeedForTime;
    private Button buttonSelectSpeedForTime;
    private Button buttonCalculateForTime;
    private Button buttonRemoveForTime;
    private TextView textViewTimeShowForTime;

    private EditText editTextNumberDistanceForSpeed;
    private EditText editTextNumberTimeForSpeed;
    private Button buttonSelectTimeForSpeed;
    private Button buttonCalculateForSpeed;
    private Button buttonRemoveForSpeed;
    private TextView textViewSpeedShowForSpeed;

    private EditText editTextNumberSpeedForDistance;
    private EditText editTextNumberTimeForDistance;
    private Button buttonCalculateForDistance;
    private Button buttonRemoveForDistance;
    private TextView textViewDistanceShowForDistance;

    private Button buttonDistanceTimeSpeedClean;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取距离速度算时间
        calculateTime();
        //获取距离时间算速度
        calculateSpeed();
        //获取速度时间算距离
        calculateDistance();
        //清空所有
        cleanData();
    }

    private void cleanData() {
        buttonDistanceTimeSpeedClean = findViewById(R.id.buttonDistanceTimeSpeedClean);
        buttonDistanceTimeSpeedClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextNumberSpeedForDistance.setText(null);
                editTextNumberTimeForDistance.setText(null);
                textViewDistanceShowForDistance.setText(null);
                editTextNumberDistanceForSpeed.setText(null);
                editTextNumberTimeForSpeed.setText(null);
                textViewSpeedShowForSpeed.setText(null);
                editTextNumberDistanceForTime.setText(null);
                editTextNumberSpeedForTime.setText(null);
                textViewTimeShowForTime.setText(null);
            }
        });
    }

    private void calculateDistance() {
        editTextNumberSpeedForDistance = findViewById(R.id.editTextNumberSpeedForDistance);
        editTextNumberTimeForDistance = findViewById(R.id.editTextNumberTimeForDistance);
        buttonCalculateForDistance = findViewById(R.id.buttonCalculateForDistance);
        textViewDistanceShowForDistance = findViewById(R.id.textViewDistanceShowForDistance);
        buttonCalculateForDistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DistanceSpeedTime distanceSpeedTime = new DistanceSpeedTime();
                DistanceSpeedTimeCalculatorImpl distanceSpeedTimeCalculator = new DistanceSpeedTimeCalculatorImpl();
                distanceSpeedTime.setSpeed(Double.parseDouble(editTextNumberSpeedForDistance.getText().toString()));
                distanceSpeedTime.setTime(Integer.parseInt(editTextNumberTimeForDistance.getText().toString()));
                String textShowDistance = String.valueOf(distanceSpeedTimeCalculator.calculatorDistance(distanceSpeedTime.getSpeed(), distanceSpeedTime.getTime()));
                textViewDistanceShowForDistance.setText(textShowDistance);
            }
        });
        buttonRemoveForDistance = findViewById(R.id.buttonRemoveForDistance);
        buttonRemoveForDistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextNumberSpeedForDistance.setText(null);
                editTextNumberTimeForDistance.setText(null);
                textViewDistanceShowForDistance.setText(null);
            }
        });
    }

    private void calculateSpeed() {
        editTextNumberDistanceForSpeed = findViewById(R.id.editTextNumberDistanceForSpeed);
        editTextNumberTimeForSpeed = findViewById(R.id.editTextNumberTimeForSpeed);
        buttonSelectTimeForSpeed = findViewById(R.id.buttonSelectTimeForSpeed);
        ActivityResultLauncher<Intent> activityResultRegistry = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                Intent intent = result.getData();
                String selectData = intent.getStringExtra(Constant.TIME_SELECT_BACKUP_DATA);
                textViewSpeedShowForSpeed.setText(selectData);
            }
        });
        buttonSelectTimeForSpeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TimeSelectorActivity.class);
                activityResultRegistry.launch(intent);
           }
        });
        buttonCalculateForSpeed = findViewById(R.id.buttonCalculateForSpeed);
        textViewSpeedShowForSpeed = findViewById(R.id.textViewSpeedShowForSpeed);
        buttonCalculateForSpeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DistanceSpeedTime distanceSpeedTime = new DistanceSpeedTime();
                DistanceSpeedTimeCalculatorImpl distanceSpeedTimeCalculator = new DistanceSpeedTimeCalculatorImpl();
                distanceSpeedTime.setDistance(Double.parseDouble(editTextNumberDistanceForSpeed.getText().toString()));
                distanceSpeedTime.setTime(Integer.parseInt(editTextNumberTimeForSpeed.getText().toString()));
                String textShowSpeed = String.valueOf(distanceSpeedTimeCalculator.calculatorSpeed(distanceSpeedTime.getDistance(), distanceSpeedTime.getTime()));
                textViewSpeedShowForSpeed.setText(textShowSpeed);
            }
        });
        buttonRemoveForSpeed = findViewById(R.id.buttonRemoveForSpeed);
        buttonRemoveForSpeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextNumberDistanceForSpeed.setText(null);
                editTextNumberTimeForSpeed.setText(null);
                textViewSpeedShowForSpeed.setText(null);
            }
        });
    }

    private void calculateTime() {
        editTextNumberDistanceForTime = findViewById(R.id.editTextNumberDistanceForTime);
        editTextNumberSpeedForTime = findViewById(R.id.editTextNumberSeepForTime);
        buttonSelectSpeedForTime = findViewById(R.id.buttonSelectSpeedForTime);
        ActivityResultLauncher<Intent> speedSelectorActivityResultRegistry = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                Intent intent = result.getData();
                String selectData = intent.getStringExtra(Constant.SPEED_SELECT_BACKUP_DATA);
                editTextNumberSpeedForTime.setText(selectData);
            }
        });
        buttonSelectSpeedForTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SpeedSelectorActivity.class);
                speedSelectorActivityResultRegistry.launch(intent);
            }
        });
        buttonCalculateForTime = findViewById(R.id.buttonCalculateForTime);
        textViewTimeShowForTime = findViewById(R.id.textViewTimeShowForTime);
        buttonCalculateForTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DistanceSpeedTime distanceSpeedTime = new DistanceSpeedTime();
                DistanceSpeedTimeCalculatorImpl distanceSpeedTimeCalculator = new DistanceSpeedTimeCalculatorImpl();
                distanceSpeedTime.setDistance(Double.parseDouble(editTextNumberDistanceForTime.getText().toString()));
                distanceSpeedTime.setSpeed(Double.parseDouble(editTextNumberSpeedForTime.getText().toString()));
                String textShowTime = String.valueOf(distanceSpeedTimeCalculator.
                        calculatorTime(distanceSpeedTime.getDistance(),distanceSpeedTime.getSpeed()));
                textViewTimeShowForTime.setText(textShowTime);
            }
        });
        buttonRemoveForTime = findViewById(R.id.buttonRemoveForTime);
        buttonRemoveForTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextNumberDistanceForTime.setText(null);
                editTextNumberSpeedForTime.setText(null);
                textViewTimeShowForTime.setText(null);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        editTextNumberTimeForSpeed.setText(data.getStringExtra(Constant.TIME_SELECT_BACKUP_DATA));
    }
}