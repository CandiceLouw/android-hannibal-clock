package com.graham.hannibalclock;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.AlarmClock;
import android.widget.RemoteViews;


/**
 * Created by CLouw on 18/01/2019.
 */
public class White extends AppWidgetProvider{

    public void onReceive(Context context, Intent intent){
        String action = intent.getAction();
        PendingIntent pendingIntent;



        if (AppWidgetManager.ACTION_APPWIDGET_UPDATE.equals(action))
        {

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.white);

            PackageManager packageManager = context.getPackageManager();
            Intent alarmClockIntent = new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER);

            String clockImpls[][] = {
                    { "HTC", "com.htc.android.worldclock",
                            "com.htc.android.worldclock.WorldClockTabControl" },
                    {"Standard Alarm Clock2", "com.google.android.deskclock",
                            "com.android.deskclock.AlarmClock"},
                    { "Standard", "com.android.deskclock",
                            "com.android.deskclock.AlarmClock" },
                    { "Froyo", "com.google.android.deskclock",
                            "com.android.deskclock.DeskClock" },
                    { "Motorola", "com.motorola.blur.alarmclock",
                            "com.motorola.blur.alarmclock.AlarmClock" },
                    { "Sony Ericsson Xperia Z", "com.sonyericsson.organizer",
                            "com.sonyericsson.organizer.Organizer_WorldClock" },
                    { "Sony Ericsson", "com.sonyericsson.alarm", "com.sonyericsson.alarm.Alarm" },
                    { "Samsung", "com.sec.android.app.clockpackage",
                            "com.sec.android.app.clockpackage.ClockPackage" }
            };

            boolean foundClockImpl = false;

            for(int i=0; i<clockImpls.length; i++)
            {
                String vendor = clockImpls[i][0];
                String packageName = clockImpls[i][1];
                String className = clockImpls[i][2];

                try
                {
                    ComponentName cn = new ComponentName(packageName, className);
                    alarmClockIntent.setComponent(cn);
                    foundClockImpl = true;
                }
                catch (Exception e){};

                if (foundClockImpl) {
                    pendingIntent = PendingIntent.getActivity(context, 0, alarmClockIntent, 0);
                    views.setOnClickPendingIntent(R.id.Widget, pendingIntent);
                    AppWidgetManager.getInstance(context).updateAppWidget(intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS), views);
                }else{
                    //Added on 20200626 -- remove if causing issues with device behaviour
                    Intent in = new Intent(AlarmClock.ACTION_SET_ALARM);
                    in.putExtra(AlarmClock.EXTRA_MESSAGE, "New Alarm");
                    in.putExtra(AlarmClock.EXTRA_HOUR, 10);
                    in.putExtra(AlarmClock.EXTRA_MINUTES, 30);
                    context.startActivity(in);
                }
            }




        }

    }
}
