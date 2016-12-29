package admin.sigmastock.sigmastock.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import admin.sigmastock.sigmastock.Accessor.MobileCareSortAccessor;
import admin.sigmastock.sigmastock.Accessor.MobileCareSortAccessorImpl;
import admin.sigmastock.sigmastock.Config;
import admin.sigmastock.sigmastock.Entity.CStock;
import admin.sigmastock.sigmastock.Entity.RssItem;
import admin.sigmastock.sigmastock.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FmHome.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FmHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FmHome extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    AsyMain asyMain;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    ProgressBar pbTopProgress ;
    MobileCareSortAccessor mobileCareSortAccessor ;
    public FmHome() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FmHome.
     */
    // TODO: Rename and change types and number of parameters
    public static FmHome newInstance(String param1, String param2) {
        FmHome fragment = new FmHome();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_fm_home, container, false);
        pbTopProgress = (ProgressBar) view.findViewById(R.id.pbTopProgress);
        pbTopProgress.setVisibility(View.VISIBLE);
        // Inflate the layout for this fragment
        asyMain = new AsyMain();
        asyMain.execute();

        return view;
    }


    List<CStock> stockList ;
    List<CStock> stockListAll ;
    AdapterHome adapterHome;
    List<String> listGroup;
    List<RssItem> listCafeNew;
    public class AsyMain extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected Void doInBackground(Void... voids)
        {
                stockListAll   = new ArrayList<>();
                CStock cStock = new CStock();
                cStock.setCode("Chỉ số");
                cStock.setLast("Giá");
                cStock.setChange("Thay đổi");
                cStock.setChangeRatio("Thay đổi (%)");
                 stockListAll.add(cStock);

            try
            {
                mobileCareSortAccessor = new MobileCareSortAccessorImpl();
                String ss = mobileCareSortAccessor.testHSX();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
                try
                {
                    mobileCareSortAccessor = new MobileCareSortAccessorImpl();
                    stockList = new ArrayList<>();
                    stockList = mobileCareSortAccessor.getDataVNi();
                    stockListAll.addAll(stockList);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

                try
                {
                    mobileCareSortAccessor = new MobileCareSortAccessorImpl();
                    stockList = new ArrayList<>();
                    stockList = mobileCareSortAccessor.getDataCOM();
                    stockListAll.addAll(stockList);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

                try
                {

                    mobileCareSortAccessor = new MobileCareSortAccessorImpl();
                    stockList = new ArrayList<>();
                    stockList = mobileCareSortAccessor.getDataExchangeRate();
                    stockListAll.addAll(stockList);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

             try
             {

                mobileCareSortAccessor = new MobileCareSortAccessorImpl();
                 listCafeNew = new ArrayList<>();
                 listCafeNew = mobileCareSortAccessor.getDataNewCafeF();

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            try
            {
                SharedPreferences pref = getActivity().getSharedPreferences(Config.SHARED_PREF, 0);
                String regId = pref.getString("regId", null);
                mobileCareSortAccessor = new MobileCareSortAccessorImpl();
                String res = mobileCareSortAccessor.sendToken(regId);
                Log.e("huy id",regId);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

                publishProgress();
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            if (adapterHome == null) {
                adapterHome = new AdapterHome(getActivity());
                listGroup = new ArrayList<>();
                listGroup.add("Chỉ sổ");
                listGroup.add("Tin nổi bật trong ngày (Nguồn :cafef.vn)");
                listGroup.add("Tin doanh nghiệp niêm yết");

                ExpandableListView expandableListView = (ExpandableListView) getActivity().findViewById(R.id.listView);
                expandableListView.setAdapter(adapterHome);
                for (int i = 0; i < 3; i++)
                {
                    expandableListView.expandGroup(i);
                }
                pbTopProgress.setVisibility(View.GONE);
            }
            else
            {
                adapterHome.notifyDataSetChanged();
            }
        }
    }

    public class AdapterHome extends BaseExpandableListAdapter{
    private Context context;

    private ViewHolder holder;

    public AdapterHome(Context context)
    {
        super();
        this.context = context;

    }
    @Override
    public int getGroupCount()
    {
        return listGroup.size();
    }

    @Override
    public int getChildrenCount(int groupPosition)
    {
        // return this.listChild.get(this.listGroup.get(groupPosition)).size();

        int childCount =1 ;
        if (groupPosition==0)
        {
            childCount = stockListAll.size();
        }
        else {
            if (groupPosition == 1)
            {
                childCount = 5;
            }
        }
       /* switch (groupPosition)
        {
            case 0:
                childCount =1;
                break;
            case 1:
                childCount =5;
                break;

        }*/
        return childCount;
    }

    @Override
    public Object getGroup(int groupPosition)
    {
        return listGroup.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition)
    {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition)
    {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition)
    {
        return childPosition;
    }

    @Override
    public boolean hasStableIds()
    {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)
    {
            /*if (listCellGroup.size() > groupPosition)
            {
                return listCellGroup.get(groupPosition);
            }*/


        convertView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_itemheader_home, null);

        TextView txt = (TextView) convertView.findViewById(R.id.txtTitleHeader);
        txt.setText(listGroup.get(groupPosition));
        // convertView.setTag(holder);

        //listCellGroup.add(convertView);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
    {
        if (groupPosition ==0 || groupPosition ==2 ) {
            convertView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_item_number_home, null);
            holder = new ViewHolder();
            holder.txtIndices = (TextView) convertView.findViewById(R.id.txtIndices);
            holder.txtIndices.setText(stockListAll.get(childPosition).getCode());
            holder.txtLast = (TextView) convertView.findViewById(R.id.txtLast);
            holder.txtLast.setText(stockListAll.get(childPosition).getLast());
            holder.txtChange = (TextView) convertView.findViewById(R.id.txtChange);
            holder.txtChange.setText(stockListAll.get(childPosition).getChange());
            holder.txtChangRatio = (TextView) convertView.findViewById(R.id.txtChangeRatio);
            holder.txtChangRatio.setText(stockListAll.get(childPosition).getChangeRatio());
        }
        else {
            convertView = LayoutInflater.from(getActivity()).inflate(R.layout.list_title_bai_bao, null);
            holder = new ViewHolder();
            holder.iv = (ImageView) convertView.findViewById(R.id.imgIcon);
            holder.tv = (TextView) convertView.findViewById(R.id.title);
            holder.txtPubdate = (TextView) convertView.findViewById(R.id.pubDate);
            holder.lnNews = (LinearLayout) convertView.findViewById(R.id.lnNews);
            if (listCafeNew.get(childPosition).getUrlImg().length() < 5) {
                holder.tv.setText(listCafeNew.get(childPosition).getTitel());
                holder.iv.setImageResource(R.drawable.icon_bai_bao);
            } else {
                Picasso.with(context).load(listCafeNew.get(childPosition).getUrlImg())
                        .placeholder(R.drawable.icon_bai_bao).error(R.drawable.icon_bai_bao).into(holder.iv);

                holder.tv.setText(listCafeNew.get(childPosition).getTitel().trim());
                holder.txtPubdate.setText(listCafeNew.get(childPosition).getDate().trim());
            }
            holder.lnNews.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   /* Intent intent = new Intent(holder.lnNews.getContext(), DisplayItemNewsPaper.class);
                    intent.putExtra(Variables.LINK,items.get(position).getLink());
                    holder.lnNews.getContext().startActivity(intent);*/
                }
            });
        }
            return convertView;
    }


        private class ViewHolder
        {
            TextView  txtIndices, txtLast, txtChange,txtChangRatio;
            ImageView iv ;
            TextView tv ;
            TextView txtPubdate ;
            LinearLayout lnNews;

            ImageView iv2 ;
            TextView tv2 ;
            TextView txtPubdate2 ;
            LinearLayout lnNews2;

        }
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition)
    {
        return true;
    }
}
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            /*throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");*/
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
