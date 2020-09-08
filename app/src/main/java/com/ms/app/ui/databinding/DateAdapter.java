package com.ms.app.ui.databinding;

import android.widget.TextView;
import androidx.databinding.BindingAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAdapter {

    @BindingAdapter(value = {"app:date", "app:format"}, requireAll = false)
    public static void bindFormat(TextView view, Long date, String format) {
        if (date == null) {
            return;
        }
        SimpleDateFormat formatter;
        if (format != null) {
            formatter = new SimpleDateFormat(format);
        } else {
            formatter = new SimpleDateFormat("yyyy-MM-dd");
        }

        view.setText(formatter.format(new Date(date)));}
}
