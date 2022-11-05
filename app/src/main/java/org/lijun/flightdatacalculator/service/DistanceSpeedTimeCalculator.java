package org.lijun.flightdatacalculator.service;

public interface DistanceSpeedTimeCalculator {
    double calculatorSpeed(double distance,int time);
    int calculatorTime(double distance,double speed);
    double calculatorDistance(double speed,int time);
}
