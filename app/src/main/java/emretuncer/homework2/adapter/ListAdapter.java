package emretuncer.homework2.adapter;

/**
 * Created by Emre on 11.5.2017.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

import emretuncer.homework2.R;
import emretuncer.homework2.fragment.FoodFragment;
import emretuncer.homework2.model.Announcement;
import emretuncer.homework2.model.News;

public class ListAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Object>   mgenericList;
    private Object[] mgenericArray;
    private Activity context;

    public ListAdapter(Activity activity, List<Object> genericList) {

        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);

        mgenericList = genericList;
        context = activity;
    }

    public ListAdapter(Activity activity, Object[] generic_array) {

        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);

        mgenericArray = generic_array;

    }



    @Override
    public int getCount() {
        return mgenericList.size();
    }

    @Override
    public Object getItem(int position) {

        return mgenericList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView;

        rowView = mInflater.inflate(R.layout.list_layout, null);
        TextView textView_item_name =
                (TextView) rowView.findViewById(R.id.itemName);


        Object item = mgenericList.get(position);


        if (item instanceof Announcement) {
            String title = ((Announcement) item).getTitle();
            textView_item_name.setText(title);
        }

        else if (item instanceof News)
        {
            String title = ((News) item).getTitle();
            textView_item_name.setText(title);
        }

        else if (item instanceof String)
        {
            String title = item.toString();
            textView_item_name.setText(title);
        }



        rowView.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           String url = "";
                                           Object item = mgenericList.get(position);
                                           if (item instanceof Announcement)
                                               url = ((Announcement) item).getLink();

                                            if (item instanceof News)
                                                url = ((News) item).getLink();

                                           if(!url.equals(""))
                                           {
                                               Intent intent= new Intent(Intent.ACTION_VIEW,Uri.parse(url));
                                               context.startActivity(intent);
                                           }

                                       }
                                   }

        );


        return rowView;
    }






}
