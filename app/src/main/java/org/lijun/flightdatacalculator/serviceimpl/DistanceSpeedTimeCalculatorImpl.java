package org.lijun.flightdatacalculator.serviceimpl;

import org.lijun.flightdatacalculator.service.DistanceSpeedTimeCalculator;

public class DistanceSpeedTimeCalculatorImpl implements DistanceSpeedTimeCalculator {
    @Override
    public double calculatorSpeed(double distance, int time) {
        return (distance * 60 * 60) / time;
    }

    @Override
    public int calculatorTime(double distance, double speed) {
        return (int) (distance / speed * 60 * 60);
    }

    @Override
    public double calculatorDistance(double speed, int time) {
        return speed * (time / 3600);
    }
}
