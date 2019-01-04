package com.example.webplat.amoldesigning.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.webplat.amoldesigning.R;
import com.example.webplat.amoldesigning.Utils.ApplicationConstant;
import com.example.webplat.amoldesigning.pojo.bbpssubcategory.Datum;


import java.util.List;

/**
 * Created by webplat2 on 1/8/18.
 */

public class CustomListAdapter extends RecyclerView.Adapter<CustomListAdapter.ViewHolder> {
    Context context;
    CustomListAdapter.SelectOperator selectOperator;
    List<Datum> operatorDataArrayList;
    public CustomListAdapter(Context context, List<Datum> operatorDataArrayList) {
        this.context=context;
        this.operatorDataArrayList=operatorDataArrayList;
    }


    public void ChooseOperator( CustomListAdapter.SelectOperator selectOperator){
        this.selectOperator=selectOperator;
    }

    @Override
    public CustomListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_mobile_operator, parent, false);

        return new CustomListAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Glide.with(context)
                .load(ApplicationConstant.IMAGEWEBSERVICEURL+operatorDataArrayList.get(position).getImageurl()).placeholder(R.mipmap.logo).error(R.mipmap.logo)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(holder.operatorImage);
        holder.operatorname.setText(operatorDataArrayList.get(position).getBillername());
    }


    @Override
    public int getItemCount() {
        return operatorDataArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnTouchListener, View.OnClickListener {
        private TextView operatorname;
        private ImageView operatorImage;

        public ViewHolder(View view) {
            super(view);
            operatorname = (TextView) view.findViewById(R.id.title1);
            operatorImage = (ImageView) view. findViewById(R.id.list_image1);
            // view.setOnTouchListener(this);
            view.setOnClickListener(this);
        }
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int position = getAdapterPosition();
            selectOperator.selectOperatorfromlist(position);
            return true;
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            selectOperator.selectOperatorfromlist(position);
        }
    }

    public interface SelectOperator{
        public void selectOperatorfromlist(int position);
    }

}
