package emretuncer.homework2.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import butterknife.Bind;
import emretuncer.homework2.R;
import emretuncer.homework2.activity.TabActivity;
import emretuncer.homework2.adapter.ListAdapter;
import emretuncer.homework2.model.Announcement;

/**
 * Created by Emre on 10.5.2017.
 */
public class AnnouncementFragment extends Fragment  {



    public AnnouncementFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_custom, container, false);
        ListView list_item = (ListView) v.findViewById(R.id.list_item);

        TabActivity activity = (TabActivity) getActivity();
        List<Announcement> announcementList = activity.getAnnouncements();
        List<Object> variable = (List<Object>)(List<?>) announcementList;
        ListAdapter adapter_list_item=new ListAdapter(getActivity(),variable);
        list_item.setAdapter(adapter_list_item);
        return v;


    }

}