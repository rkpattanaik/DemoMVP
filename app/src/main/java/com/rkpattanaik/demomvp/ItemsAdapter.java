package com.rkpattanaik.demomvp;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rkpattanaik.demomvp.model.Content;
import com.rkpattanaik.demomvp.model.Item;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author Rajesh Pattanaik
 */

class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    private List<Item> mItems;
    private Context mContext;
    private float deviceDensity;

    ItemsAdapter(Context context) {
        mContext = context;
        mItems = new ArrayList<>();
        deviceDensity = mContext.getResources().getDisplayMetrics().density;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = mItems.get(position);
        List<Content> contents = item.getContent();
        addContents(holder, contents);
    }

    // This methods adds the contents (text/image) to the row
    private void addContents(ViewHolder holder, List<Content> contents) {
        for (int i = 0; i < contents.size(); i++) {
            Content content = contents.get(i);
            if (content.getT() == 1) {
                TextView tv = new TextView(mContext);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.leftMargin = 0;
                params.rightMargin = 0;
                params.topMargin = i == 0 ? 0 : (int) (deviceDensity * 4);
                params.bottomMargin = i == contents.size() - 1 ? 0 : (int) (deviceDensity * 4);
                tv.setLayoutParams(params);
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                tv.setText(content.getV());
                holder.contentsContainer.addView(tv);
            } else if (content.getT() == 2) {
                ImageView iv = new ImageView(mContext);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, (int) (160 * deviceDensity));
                params.leftMargin = 0;
                params.rightMargin = 0;
                params.topMargin = i == 0 ? 0 : (int) (deviceDensity * 4);
                params.bottomMargin = i == contents.size() - 1 ? 0 : (int) (deviceDensity * 4);
                iv.setLayoutParams(params);
                iv.setScaleType(ImageView.ScaleType.CENTER_CROP);

                Glide.with(mContext)
                        .load(content.getV())
                        .placeholder(R.drawable.placeholder)
                        .centerCrop()
                        .error(R.drawable.error)
                        .crossFade()
                        .into(iv);

                holder.contentsContainer.addView(iv);
            }
        }
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);
        holder.contentsContainer.removeAllViews();
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    void setItems(List<Item> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.contentsContainer)
        LinearLayout contentsContainer;
        @BindView(R.id.cardItem)
        CardView cardItem;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
