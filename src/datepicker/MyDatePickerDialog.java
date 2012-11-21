package datepicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

public class MyDatePickerDialog extends DatePickerDialog{

private int maxYear;
private int maxMonth;
private int maxDay;

private final Calendar mCalendar;
private final SimpleDateFormat formatter;


public MyDatePickerDialog(Context context,OnDateSetListener callBack,int year,int monthOfYear,int dayOfMonth){
    super(context,callBack,year,monthOfYear,dayOfMonth);

    mCalendar = Calendar.getInstance();

    mCalendar.setTime(new Date());
    //set up maxdate

    maxYear=mCalendar.get(Calendar.YEAR);
    maxMonth=mCalendar.get(Calendar.MONTH);
    maxDay=mCalendar.get(Calendar.DATE);

    //set up init date
    mCalendar.set(Calendar.YEAR, year);
    mCalendar.set(Calendar.MONTH, monthOfYear);
    mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

  //set up date display format
    formatter = new SimpleDateFormat("dd/MM/yyyy");
    setTitle(formatter.format(mCalendar.getTime()));
}

@Override
public void onDateChanged(DatePicker view, int year,int month, int day){

    boolean afterMaxDate=false;


        if(year>maxYear){
            afterMaxDate=true;
        }
        else if(year==maxYear){
            if(month>maxMonth){
                afterMaxDate=true;
            }
            else if(month==maxMonth){
                if(day>maxDay){
                    afterMaxDate=true;
                }
            }
        }


    //need set invalid date to mindate or maxdate
    if(afterMaxDate){
            year=maxYear;
            month=maxMonth;
            day=maxDay;
            view.updateDate(year,  month,  day);
    }

    //display in view title
    mCalendar.set(Calendar.YEAR, year);
    mCalendar.set(Calendar.MONTH, month);
    mCalendar.set(Calendar.DAY_OF_MONTH, day);
    setTitle(formatter.format(mCalendar.getTime()));
}
}
