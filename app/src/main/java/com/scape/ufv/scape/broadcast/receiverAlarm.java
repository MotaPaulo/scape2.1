package com.scape.ufv.scape.broadcast;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

import com.scape.ufv.scape.R;
import com.scape.ufv.scape.SCAPE.Agenda;

public class receiverAlarm extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        gerarNotificacao(context, new Intent(context, Agenda.class), "Palestra Agendada", "SCAPE", "Está na hora da sua palestra");
    }

    public void gerarNotificacao(Context context, Intent intent, CharSequence ticker, CharSequence titulo, CharSequence descricao){
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent p = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setTicker(ticker);
        builder.setContentTitle(titulo);
        builder.setContentText(descricao);
        builder.setSmallIcon(R.drawable.scape_logo_small);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.scape_logo_small));
        builder.setContentIntent(p);

        Notification n = builder.build();
        n.vibrate = new long[]{150, 300, 150, 600};
        n.flags = Notification.FLAG_AUTO_CANCEL;
        nm.notify(R.drawable.scape_logo_small, n);

        try{
            //Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            //Ringtone toque = RingtoneManager.getRingtone(context, som);
            //toque.play();
        }
        catch(Exception e){}
    }

}