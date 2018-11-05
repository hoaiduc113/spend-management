package com.example.hoaiduc.quanlychitieu.CustomView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.media.MediaCodec;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.example.hoaiduc.quanlychitieu.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hoaiduc on 2/11/2018.
 */

public class PassWordEditText extends EditText {
    Drawable eye,eyetrike;
    boolean visible=false;
    boolean usestrike=false;
    boolean usevalidate=false;
    Drawable drawable;
    //* là chuỗi
    //.là điều kiện
    // một số ?=.*\d)
    //[] có thể có điều kiện hoặc ko
    //{} bắt buộc phải có
    //?.là điều kiện
    String MATCHER_PATTERN="((?=.*\\d)(?=.*[A-Z])(?=.*[a-z]).{6,20})";
    Pattern pattern;
    Matcher matcher;

    public PassWordEditText(Context context) {
        super(context);
        create(null);
    }

    public PassWordEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        create(attrs);
    }

    public PassWordEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        create(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PassWordEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        create(attrs);
    }
    private void create(AttributeSet attrs)
    {
        this.pattern=Pattern.compile(MATCHER_PATTERN);
        if(attrs!=null)
        {
            TypedArray array=getContext().getTheme().obtainStyledAttributes(attrs,R.styleable.PassWordEditText,0,0);
            this.usestrike=array.getBoolean(R.styleable.PassWordEditText_usestrike,false);
            this.usevalidate=array.getBoolean(R.styleable.PassWordEditText_usevalidate,false);
        }
        eye= ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_black_24dp).mutate();
        eyetrike=ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_off_black_24dp).mutate();
        if(this.usevalidate)
        {
            setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if(!b)//mất focus
                    {
                        String chuoi=getText().toString();
                        TextInputLayout textInputLayout= (TextInputLayout) view.getParent().getParent();
                        matcher=pattern.matcher(chuoi);
                        if(!matcher.matches())
                        {
                            Log.d("kiemtra",matcher.matches()+"");
                            textInputLayout.setErrorEnabled(true);
                            textInputLayout.setError("mật khẩu phải bao gồm 6 ký tự và 1 chữ hoa");
                        }
                        else
                        {
                            textInputLayout.setErrorEnabled(false);
                            textInputLayout.setError("");
                        }
                    }
                }
            });
        }
        hidePassword();
    }
    private void hidePassword() {
        setInputType(InputType.TYPE_CLASS_TEXT | (visible ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_TEXT_VARIATION_PASSWORD
        ));
        Drawable[] drawables=getCompoundDrawables();
        this.drawable=usestrike &&!visible?eyetrike:eye;
        setCompoundDrawablesWithIntrinsicBounds(drawables[0],drawables[1],drawable,drawables[3]);
    }
//usetrike sử dụng con mắt
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_UP &&event.getX()>=(getRight()-drawable.getBounds().width()))
        {
            visible=!visible;
            hidePassword();
            invalidate();
        }
        return super.onTouchEvent(event);
    }
}
