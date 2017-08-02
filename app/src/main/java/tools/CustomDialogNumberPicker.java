package tools;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.iranplanner.tourism.iranplanner.R;

import java.lang.reflect.Field;

import tools.widget.PersianDatePicker;

/**
 * Created by HoDA on 7/29/2017.
 */

public class CustomDialogNumberPicker extends Dialog implements
        View.OnClickListener {



    public static boolean setNumberPickerTextColor(NumberPicker numberPicker, int color)
    {
        final int count = numberPicker.getChildCount();
        for(int i = 0; i < count; i++){
            View child = numberPicker.getChildAt(i);
            if(child instanceof EditText){
                try{
                    Field selectorWheelPaintField = numberPicker.getClass()
                            .getDeclaredField("mSelectorWheelPaint");
                    selectorWheelPaintField.setAccessible(true);
                    ((Paint)selectorWheelPaintField.get(numberPicker)).setColor(color);
                    ((EditText)child).setTextColor(color);
                    numberPicker.invalidate();
                    return true;
                }
                catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
    private void setDividerColor(NumberPicker picker, int color) {

        java.lang.reflect.Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (java.lang.reflect.Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDivider")) {
                pf.setAccessible(true);
                try {
                    ColorDrawable colorDrawable = new ColorDrawable(color);
                    pf.set(picker, colorDrawable);

                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                }
                catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }



    public Activity c;
    public Dialog d;
    public TextView yes, no;
    NumberPicker dayNumberPicker;
    int maxValue,minValue;
    String title;
    int value;

    public CustomDialogNumberPicker(Activity a,int maxValue, int minValue, String title) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.maxValue=maxValue;
        this.minValue=minValue;
        this.title=title;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        bara inke keybord bala nayad
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_number_duration);
        dayNumberPicker = (NumberPicker) findViewById(R.id.dayNumberPicker);
        TextView txtAlertTitle = (TextView) findViewById(R.id.txtAlertTitle);
        txtAlertTitle.setText(title);
        dayNumberPicker.setMaxValue(maxValue);
        dayNumberPicker.setMinValue(minValue);
        dayNumberPicker.setValue(minValue);
        setDividerColor(dayNumberPicker , ResourcesCompat.getColor(c.getResources(), R.color.greyLight, null));
        setNumberPickerTextColor(dayNumberPicker ,  ResourcesCompat.getColor(c.getResources(), R.color.dark_blue, null));
        yes = (TextView) findViewById(R.id.txtOk);
        no = (TextView) findViewById(R.id.txtNo);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
        value=1;
        dayNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
               value= numberPicker.getValue();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtOk:
                if( dialogNumberPick != null ){
                    dialogNumberPick.finish(value);
                }
                dismiss();
//                break;
                break;
            case R.id.txtNo:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
    OnDialogNumberPick dialogNumberPick;
    public interface OnDialogNumberPick{
        void finish(int result);
    }

    public void setDialogResult(OnDialogNumberPick dialogNumberPick){
        this.dialogNumberPick = dialogNumberPick;
    }
}
