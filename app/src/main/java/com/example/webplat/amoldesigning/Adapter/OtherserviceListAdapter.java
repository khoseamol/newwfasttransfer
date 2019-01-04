package com.example.webplat.amoldesigning.Adapter;

/**
 * Created by webplat on 3/2/18.
 */


        import android.content.Context;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.example.webplat.amoldesigning.R;
        import com.example.webplat.amoldesigning.Utils.Transction;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by webplat on 2/2/18.
 */

public class OtherserviceListAdapter extends RecyclerView.Adapter<OtherserviceListAdapter.MyViewHolder> {

    private List<Transction> transactionList;
    Context mContext;

    ItemClickEvent itemClickEvent;

    public interface ItemClickEvent
    {
        public void clikc(int transction);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImage;
        private TextView mTxtDate;


        public MyViewHolder(View view) {
            super(view);
            mImage = (ImageView)view. findViewById(R.id.image);
            mTxtDate = (TextView)view. findViewById(R.id.txtDate);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickEvent.clikc(getAdapterPosition());
                }
            });
        }
    }

    public OtherserviceListAdapter(Context mContext, ArrayList<Transction> horizontalList , ItemClickEvent itemClickEvent) {
        this.transactionList = horizontalList;
        this.mContext=mContext;
        this.itemClickEvent=itemClickEvent;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontalsingleitem_view1, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.mImage.setImageResource(transactionList.get(position).getImageID());
        holder.mTxtDate.setText(transactionList.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }
}

