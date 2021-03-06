package com.example.hoaiduc.quanlychitieu.CustomView;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.example.hoaiduc.quanlychitieu.R;

/**
 * Created by hoaiduc on 2/11/2018.
 */

public class ClearEditText extends EditText {
    Drawable crossx,nonecrossx;
    boolean visible=false;
    Drawable drawable;
    public ClearEditText(Context context) {
        super(context);
        create();
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        create();
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        create();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        create();
    }
    private void create()
    {
        crossx= ContextCompat.getDrawable(getContext(), R.drawable.ic_clear_black_24dp).mutate();
        nonecrossx= ContextCompat.getDrawable(getContext(), android.R.drawable.screen_background_light_transparent).mutate();
        configure();
    }
    private void configure()
    {
        setInputType(InputType.TYPE_CLASS_TEXT);
        Drawable[] drawables=getCompoundDrawables();
        drawable=visible?crossx:nonecrossx;
        setCompoundDrawablesWithIntrinsicBounds(drawables[0],drawables[1],drawable,drawables[3]);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN &&event.getX()>=(getRight()-drawable.getBounds().width()))
        {
            setText("");
        }
        return super.onTouchEvent(event);
    }
    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if(lengthAfter==0 && start==0)
        {
            visible=false;
            configure();
        }
        else
        {
            visible=true;//show image
            configure();
        }
    }
}
