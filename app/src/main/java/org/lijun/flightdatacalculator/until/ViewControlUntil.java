package org.lijun.flightdatacalculator.until;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.WindowManager;

import org.lijun.flightdatacalculator.R;

public class ViewControlUntil {
    public static void controlSelectorActivity(Activity activity) {
        WindowManager.LayoutParams layoutParams = activity.getWindow().getAttributes();
        layoutParams.height = 660;
        layoutParams.width = 600;
        layoutParams.x = 0;
        layoutParams.y = 0;
        layoutParams.dimAmount = 0.1f;
        layoutParams.gravity = Gravity.CENTER;
        activity.getWindow().setAttributes(layoutParams);
        activity.getWindow().getDecorView().setBackgroundResource(R.drawable.dialog_activity_shape);
    }
}
