package com.example.hoaiduc.quanlychitieu.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoaiduc.quanlychitieu.Moldel.ObjectClass.Category;
import com.example.hoaiduc.quanlychitieu.Moldel.category.ModelCategory;
import com.example.hoaiduc.quanlychitieu.R;
import com.example.hoaiduc.quanlychitieu.ultil.Constant;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

/**
 * Created by hoaiduc on 2/27/2018.
 */

public class ExpanAdapter extends BaseExpandableListAdapter
{

    Context context;
    List<Category> categoryList;
    ViewHolderCategory viewHolderCategory;
    private ExpandableListView _listview;

    public ExpanAdapter(Context context,List<Category> categoryList,int userid,int group,ExpandableListView expandableListView)
    {
        this._listview=expandableListView;
        this.context=context;
        this.categoryList=categoryList;
        ModelCategory modelCategory=new ModelCategory();
        int cout=categoryList.size();
        for(int i=0;i<cout;i++)
        {
            int maloaidanhmuc=categoryList.get(i).getCategoryid();
            categoryList.get(i).setCategoryList(modelCategory.danhSachmuccon(maloaidanhmuc,userid,group));//gọi
            //đến model để put dữ liệu lên
        }
    }
    public class ViewHolderCategory
    {
        TextView txttencha;
        ImageView imgcha;
        ImageView imgcategoryy;

    }
    @Override
    public int getGroupCount() {
        return categoryList.size();
    }

    @Override
    public int getChildrenCount(int vitricha) {
        return categoryList.get(vitricha).getCategoryList().size();
    }

    @Override
    public Object getGroup(int vitricha) {
        return categoryList.get(vitricha);
    }

    @Override
    public Object getChild(int vitricha, int vitricon) {
        return categoryList.get(vitricha).getCategoryList().get(vitricon);
    }

    @Override
    public long getGroupId(int i) {
        return categoryList.get(i).getCategoryid();
    }

    @Override
    public long getChildId(int vitrigroupcha, int vitrigroupcon) {
        return categoryList.get(vitrigroupcha).getCategoryList().get(vitrigroupcon).getCategoryid();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }


    @Override
    public View getGroupView(final int vitrigroupcha, final boolean isexpanded, View view, ViewGroup viewGroup) {
        View viewgroupcha=view;
        if(viewgroupcha==null)
        {
            viewHolderCategory=new ViewHolderCategory();
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            viewgroupcha=layoutInflater.inflate(R.layout.layout_custom_group_cha,viewGroup,false);
            viewHolderCategory.txttencha=(TextView)viewgroupcha.findViewById(R.id.txttenloaisp);
            viewHolderCategory.imgcha=(ImageView)viewgroupcha.findViewById(R.id.imglistcategory);
            viewHolderCategory.imgcategoryy=(ImageView)viewgroupcha.findViewById(R.id.imcategory);
            viewgroupcha.setTag(viewHolderCategory);
        }
        else
        {
            viewHolderCategory=(ViewHolderCategory) viewgroupcha.getTag();
        }

        viewHolderCategory.txttencha.setText(categoryList.get(vitrigroupcha).getName());
        int dem=categoryList.get(vitrigroupcha).getCategoryList().size();
        if(dem>0)
        {
            viewHolderCategory.imgcategoryy.setVisibility(View.VISIBLE);
        }
        else
        {
            viewHolderCategory.imgcategoryy.setVisibility(View.INVISIBLE);
        }
        Picasso.with(context).load(categoryList.get(vitrigroupcha).getImage())
                .resize(64,64)
                .placeholder(R.drawable.cpb_background)
                .error(R.drawable.facebookiconsmall)
                .into(viewHolderCategory.imgcha);

        viewHolderCategory.imgcategoryy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isexpanded)
                {
                    _listview.expandGroup(vitrigroupcha);
                }
                else
                {
                    _listview.collapseGroup(vitrigroupcha);
                }
            }
        });

        if(isexpanded)
        {
            viewHolderCategory.txttencha.setTypeface(null, Typeface.BOLD);
            animateExpand();

        }
        else
        {
            viewHolderCategory.txttencha.setTypeface(null, Typeface.NORMAL);
            animateCollapse();

        }

       viewgroupcha.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view)
           {
               Category category=new Category();
               String name=categoryList.get(vitrigroupcha).getName();
               int categoryid=categoryList.get(vitrigroupcha).getCategoryid();
            //   int userId=categoryList.get(vitrigroupcha).getUserid();
               //int walletid=categoryList.get(vitrigroupcha).getWalletid();
               int iconid=categoryList.get(vitrigroupcha).getIconid();
               int group=categoryList.get(vitrigroupcha).getGroup();
               category.setIconid(iconid);
               category.setCategoryid(categoryid);
              // category.setUserid(userId);
               category.setName(name);
               category.setGroup(group);
               //category.setWalletid(walletid);
               Intent intent = new Intent("custom-message");
               intent.putExtra("quantity",(Serializable)category);

               LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
           }
       });
//        ExpandableListView mExpandableListView = (ExpandableListView) viewGroup;
//        mExpandableListView.expandGroup(vitrigroupcha);
        return viewgroupcha;
    }

    @Override
    public View getChildView(final int vitrigroupcha , final int vitrigroupcon, boolean b, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View viewgroupcha=layoutInflater.inflate(R.layout.layout_custom_group_con,viewGroup,false);
        TextView txtten=(TextView)viewgroupcha.findViewById(R.id.txttenloaispcon);
        ImageView imgcon=(ImageView)viewgroupcha.findViewById(R.id.imglistcategorycon);
        txtten.setText(categoryList.get(vitrigroupcha).getCategoryList().get(vitrigroupcon).getName());

        Picasso.with(context).load(categoryList.get(vitrigroupcha).getCategoryList().get(vitrigroupcon).getImage())
                .resize(64,64)
                .placeholder(R.drawable.cpb_background)
                .error(R.drawable.facebookiconsmall)
                .into(imgcon);
        //event child
        viewgroupcha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Category category=new Category();
                String name=categoryList.get(vitrigroupcha).getCategoryList().get(vitrigroupcon).getName();
                int categoryid=categoryList.get(vitrigroupcha).getCategoryList().get(vitrigroupcon).getCategoryid();
                int userid=categoryList.get(vitrigroupcha).getCategoryList().get(vitrigroupcon).getUserid();
                int walletid=categoryList.get(vitrigroupcha).getCategoryList().get(vitrigroupcon).getWalletid();
                int iconid=categoryList.get(vitrigroupcha).getCategoryList().get(vitrigroupcon).getIconid();
                int gorpup=categoryList.get(vitrigroupcha).getCategoryList().get(vitrigroupcon).getGroup();
                category.setIconid(iconid);
                category.setCategoryid(categoryid);
                category.setUserid(userid);
                category.setWalletid(walletid);
                category.setName(name);                category.setGroup(gorpup);
                Intent intent = new Intent("custom-message");
                intent.putExtra(Constant.sencategory,(Serializable)category);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

            }
        });
        return viewgroupcha;
    }
    private void animateExpand() {
        RotateAnimation rotate =
                new RotateAnimation(360, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        viewHolderCategory.imgcategoryy.setAnimation(rotate);
    }

    private void animateCollapse() {
        RotateAnimation rotate =
                new RotateAnimation(180, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        viewHolderCategory.imgcategoryy.setAnimation(rotate);
    }
    @Override
    public boolean isChildSelectable(int i, int i1)
    {

        return true;
    }

}
