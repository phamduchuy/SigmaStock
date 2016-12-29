package admin.sigmastock.sigmastock.Util;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import admin.sigmastock.sigmastock.adapter.NewsAdapter;


/**
 * Created by Admin on 18/8/2016.
 */
public class RequestVolley
{
    public static  void callStringRequest (final Context context, final SwipeRefreshLayout mSwipeRefreshLayout, final NewsAdapter adapter, final String url, final boolean refresh)
    {

        Cache cache = AppController.getInstance().getRequestQueue().getCache();
        Cache.Entry entry = cache.get(url);
        if (refresh)
        {
            mSwipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    mSwipeRefreshLayout.setRefreshing(true);
                }
            });
            StringRequest strReq = new StringRequest(Request.Method.GET,
                    url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response){
                    //setAdapterListView(response, adapter,refresh,context,url);
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d("Error", "Error: " + error.getMessage());
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            });

// Adding request to request queue
            AppController.getInstance().addToRequestQueue(strReq, url);
        }
        else
        {
            if (entry != null) {
                //Cache data available.
                try {
                    String data = new String(entry.data, "UTF-8");
                    //setAdapterListView(data, adapter,refresh,context,url);

                    Log.d("CACHE DATA", data);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } else {
                // Cache data not exist.
                mSwipeRefreshLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(true);
                    }
                });
                StringRequest strReq = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                               // setAdapterListView(response, adapter,refresh,context,url);
                                //mSwipeRefreshLayout.setRefreshing(false);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d("Error", "Error: " + error.getMessage());
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                })
                {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String,String> params =  super.getHeaders();
                        if(params==null)params = new HashMap<>();

                        return params;
                    }
                };

                AppController.getInstance().addToRequestQueue(strReq, url);
            }
        }

    }

}
