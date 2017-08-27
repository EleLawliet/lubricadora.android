package com.grupopulpo.lubriacadora.duty.utils;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.CalendarContract;

import java.util.Calendar;

/**
 * Created by Junior on 26/07/2017.
 */

public class AddToCalendar {

    public static void addToCal(Context ctx, String title, long start, long end){
            Intent intent = new Intent(Intent.ACTION_INSERT);
            intent.setType("vnd.android.cursor.item/event");

            Calendar cal = Calendar.getInstance();

            intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, start);
            intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end);
            intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);

            intent.putExtra(CalendarContract.Events.TITLE, "Lubricadora Fast-Duty");
            intent.putExtra(CalendarContract.Events.DESCRIPTION, "Fast-Duty te recuerda que tienes un"+title+" para el día de mañana.");
            intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Lubricadora Fast-Duty");
            intent.putExtra(CalendarContract.Events.RRULE, "FREQ=YEARLY");

            ctx.startActivity(intent);

    }


    /**
     * Adds the event to a calendar. It lets the user choose the calendar
     * @param ctx Context ( Please use the application context )
     * @param title title of the event
     * @param dtstart Start time: The value is the number of milliseconds since Jan. 1, 1970, midnight GMT.
     * @param dtend End time: The value is the number of milliseconds since Jan. 1, 1970, midnight GMT.
     */
    public static void addToCalendar(Context ctx, final String title, final long dtstart, final long dtend) {
        final ContentResolver cr = ctx.getContentResolver();
        Cursor cursor ;
        if (Integer.parseInt(Build.VERSION.SDK) >= 8 )
            cursor = cr.query(Uri.parse("content://com.android.calendar/calendars"), null, null, null, null);
        else
            cursor = cr.query(Uri.parse("content://calendar/calendars"),null, null, null, null);
        if ( cursor.moveToFirst() ) {
            final String[] calNames = new String[cursor.getCount()];
            final int[] calIds = new int[cursor.getCount()];
            for (int i = 0; i < calNames.length; i++) {
                calIds[i] = cursor.getInt(0);
                calNames[i] = cursor.getString(1);
                cursor.moveToNext();
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
            builder.setSingleChoiceItems(calNames, -1, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ContentValues cv = new ContentValues();
                    cv.put("calendar_id", calIds[which]);
                    cv.put("title", title);
                    cv.put("dtstart", dtstart );
                    cv.put("hasAlarm", 1);
                    cv.put("dtend", dtend);

                    Uri newEvent ;
                    if (Integer.parseInt(Build.VERSION.SDK) >= 8 )
                        newEvent = cr.insert(Uri.parse("content://com.android.calendar/events"), cv);
                    else
                        newEvent = cr.insert(Uri.parse("content://calendar/events"), cv);

                    if (newEvent != null) {
                        long id = Long.parseLong( newEvent.getLastPathSegment() );
                        ContentValues values = new ContentValues();
                        values.put( "event_id", id );
                        values.put( "method", 1 );
                        values.put( "minutes", 15 ); // 15 minutes
                        if (Integer.parseInt(Build.VERSION.SDK) >= 8 )
                            cr.insert( Uri.parse( "content://com.android.calendar/reminders" ), values );
                        else
                            cr.insert( Uri.parse( "content://calendar/reminders" ), values );

                    }
                    dialog.cancel();
                }

            });

            builder.create().show();
        }
        cursor.close();
    }

}
