package com.sam_chordas.android.stockhawk.widget;

/**
 * Created by 836137 on 12-05-2016.
 */
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import com.sam_chordas.android.stockhawk.R;
import com.sam_chordas.android.stockhawk.ui.MyStocksActivity;
import com.sam_chordas.android.stockhawk.ui.StockDetailActivity;

public class WidgetProvider extends AppWidgetProvider {

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {


        for (int appWidgetId : appWidgetIds) {
            Intent intent = new Intent(context, WidgetService.class);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_provider_layout);
            rv.setRemoteAdapter(appWidgetId, R.id.list1, intent);
            intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
            Intent historyIntent = new Intent(context, StockDetailActivity.class);
            historyIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            PendingIntent toastPendingIntent = PendingIntent.getActivity(context, 0, historyIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            rv.setPendingIntentTemplate(R.id.list1, toastPendingIntent);
            appWidgetManager.updateAppWidget(appWidgetId, rv);
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.list1);

        }
    }
}
