package com.example.hoaiduc.quanlychitieu.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.Currency;
import com.example.hoaiduc.quanlychitieu.R;
import com.example.hoaiduc.quanlychitieu.view.ListCurrency.ListCurrencyActivity;
import com.example.hoaiduc.quanlychitieu.view.wallet.AddWalletActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/
public class CustomAdapter extends ArrayAdapter<String> {
	
	private Activity activity;
    private List data;
    public Resources res;
    Currency tempValues=null;
    LayoutInflater inflater;

	public CustomAdapter(
			              AddWalletActivity activitySpinner,
			              int textViewResourceId,   
			              List objects,
			              Resources resLocal

			             ) 
	 {
        super(activitySpinner, textViewResourceId, objects);
        
        /********** Take passed values **********/
        activity = activitySpinner;
        data     = objects;
        res      = resLocal;
   
        /***********  Layout inflator to call external xml layout () **********************/
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	 }
    public CustomAdapter(
            ListCurrencyActivity activitySpinner,
            int textViewResourceId,
            List objects,
            Resources resLocal

    )
    {
        super(activitySpinner, textViewResourceId, objects);

        /********** Take passed values **********/
        activity = activitySpinner;
        data     = objects;
        res      = resLocal;

        /***********  Layout inflator to call external xml layout () **********************/
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {

    	/********** Inflate spinner_rows.xml file for each row ( Defined below ) ************/
        View row = inflater.inflate(R.layout.spinner_rows, parent, false);
        
        /***** Get each Model object from Arraylist ********/
        tempValues = null;
        tempValues = (Currency) data.get(position);
        
        TextView label        = (TextView)row.findViewById(R.id.company);
        TextView sub          = (TextView)row.findViewById(R.id.sub);
        ImageView companyLogo = (ImageView)row.findViewById(R.id.image);
//
//        if(position==0){
        	
        	// Default selected Spinner item 
        	label.setText("chọn tiền tệ");
        	sub.setText("");
//        }
//        else
//        {
            // Set values for spinner each row 
            label.setText(tempValues.getCurname());
            sub.setText(tempValues.getCursymbol());
            Picasso.with(activity).load(tempValues.getImage())
                    .placeholder(R.drawable.cpb_background)
                    .error(R.drawable.facebookiconsmall)
                    .into(companyLogo);

//        }

        return row;
      }
 }
