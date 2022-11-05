package org.lijun.flightdatacalculator.until;

import java.util.ArrayList;
import java.util.List;

public class DataProduceUntil {
    public static List itemDataListFactory(int startNum, int spacingNum, int endNum) {
        ArrayList<String> arrayList = new ArrayList();
        for (int i = 0; i <= (endNum - startNum) / spacingNum; i++) {
            arrayList.add(String.valueOf((startNum + spacingNum * i)));
        }
        return arrayList;
    }
}
