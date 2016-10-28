package com.example.hzxie.itaszkcatalog.zkcatalog;

import android.content.ClipData;
import android.content.Context;
import android.os.ParcelUuid;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.hzxie.itaszkcatalog.R;

import java.util.List;

/**
 * Created by hzxie on 2016/10/18.
 */
public class ZkCatalogBasicInfoAdapter extends BaseAdapter {
    private final int NUM_TYPE = 0;
    private final int NUM_TYPE_ENABLED = 1;
    private final int STING_TYPE_ENABLED = 2;
    private final int COMBO_TYPE = 3;
    private Context mContext;
    private List<ViewInfo> mEntries;
    public ZkCatalogBasicInfoAdapter(Context context, List<ViewInfo> entries)
    {
        mContext = context;
        mEntries = entries;
    }
    @Override
    public int getCount() {
        return mEntries.size();
    }

    @Override
    public Object getItem(int i) {
        return mEntries.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        NumViewHolder numViewHolder = null;
        ItemViewHolder itemViewHolder = null;

        int type = this.getItemViewType(i);
        if(convertView == null)
        {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            switch (type)
            {
                case NUM_TYPE:
                    convertView = inflater.inflate(R.layout.zk_number_listview,viewGroup,false);
                    numViewHolder = new NumViewHolder();
                    numViewHolder.iv =(ImageView)convertView.findViewById(R.id.listview_num_iv);
                    numViewHolder.tv =(TextView)convertView.findViewById(R.id.listview_num_tt);
                    numViewHolder.et =(EditText)convertView.findViewById(R.id.listview_num_et);
                    convertView.setTag(numViewHolder);
                    break;
                case NUM_TYPE_ENABLED:
                    convertView = inflater.inflate(R.layout.zk_number_listview_enabled,viewGroup,false);
                    numViewHolder = new NumViewHolder();
                    numViewHolder.iv =(ImageView)convertView.findViewById(R.id.listview_num_iv_enabled);
                    numViewHolder.tv =(TextView)convertView.findViewById(R.id.listview_num_tt_enabled);
                    numViewHolder.et =(EditText)convertView.findViewById(R.id.listview_num_et_enabled);
                    convertView.setTag(numViewHolder);
                    break;
                case STING_TYPE_ENABLED:
                    convertView = inflater.inflate(R.layout.zk_string_listview_enabled,viewGroup,false);
                    numViewHolder = new NumViewHolder();
                    numViewHolder.iv =(ImageView)convertView.findViewById(R.id.listview_string_iv_enabled);
                    numViewHolder.tv =(TextView)convertView.findViewById(R.id.listview_string_tt_enabled);
                    numViewHolder.et =(EditText)convertView.findViewById(R.id.listview_string_et_enabled);
                    convertView.setTag(numViewHolder);
                    break;
                case COMBO_TYPE:
                    convertView = inflater.inflate(R.layout.zk_combo_listview,viewGroup,false);
                    itemViewHolder = new ItemViewHolder();
                    itemViewHolder.iv =(ImageView)convertView.findViewById(R.id.listview_items_iv);
                    itemViewHolder.tv =(TextView)convertView.findViewById(R.id.listview_items_tt);
                    itemViewHolder.sp =(Spinner) convertView.findViewById(R.id.listview_items_sp);
                    convertView.setTag(itemViewHolder);
                    break;
                default:
                    break;
            }
        }
        else
        {
            switch (type)
            {
                case NUM_TYPE:
                case NUM_TYPE_ENABLED:
                case STING_TYPE_ENABLED:
                    numViewHolder = (NumViewHolder)convertView.getTag();
                    break;
                case COMBO_TYPE:
                    itemViewHolder =(ItemViewHolder)convertView.getTag();
                    break;
                default:
                    break;
            }
        }

        switch (type)
        {
            case NUM_TYPE:
            case NUM_TYPE_ENABLED:
            case STING_TYPE_ENABLED:
                numViewHolder.iv.setImageResource(mEntries.get(i).id);
                numViewHolder.tv.setText(mEntries.get(i).label);
                numViewHolder.et.setHint(mEntries.get(i).entriesOrhints[0]);
                break;
            case COMBO_TYPE:
                itemViewHolder.iv.setImageResource(mEntries.get(i).id);
                itemViewHolder.tv.setText(mEntries.get(i).label);
                SpinnerAdapter adapter = new ArrayAdapter<String >(mContext,android.R.layout.simple_spinner_item,mEntries.get(i).entriesOrhints);
                itemViewHolder.sp.setAdapter(adapter);
                break;
        }
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position)
        {
            case 0:
            case 1:
                return NUM_TYPE;
            case 2:
                return STING_TYPE_ENABLED;
            case 3:
                return COMBO_TYPE;
            default:
                return NUM_TYPE_ENABLED;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }

    private class NumViewHolder
    {
        ImageView iv;
        TextView tv;
        EditText et;
    }
    private class ItemViewHolder
    {
        ImageView iv;
        TextView tv;
        Spinner sp;
    }
    public class ViewInfo
    {
        public ViewInfo()
        {

        }
       public int id;
       public String label;
       public String[] entriesOrhints;
    }

}
