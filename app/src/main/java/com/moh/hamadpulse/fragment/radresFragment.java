package com.moh.hamadpulse.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.InterfacePatient;
import com.moh.hamadpulse.OnAdapterClick;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.adapters.RadPhotoAdapter;
import com.moh.hamadpulse.constants.CustomRequest;
import com.moh.hamadpulse.models.RadphotoModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class radresFragment extends Fragment implements OnAdapterClick {

    private static RecyclerView.Adapter radPhotoAdapter;
    private static RecyclerView radrecyclerView;
    private static ArrayList<RadphotoModel> Carddata;
    String patname, patid, indate;
    LinearLayout radheader;
    private RecyclerView.LayoutManager layoutManager;
    public String fragment_cd = "6";

    public radresFragment() {
    }

    InterfacePatient mInterfacePatient;

    public InterfacePatient getmInterfacePatient() {
        return mInterfacePatient;
    }

    public void setmInterfacePatient(InterfacePatient mInterfacePatient) {
        this.mInterfacePatient = mInterfacePatient;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_radres, container, false);
        radrecyclerView = view.findViewById(R.id.radorder_recycler_view);
        radheader = view.findViewById(R.id.radheader);
        Carddata = new ArrayList<>();
        radPhotoAdapter = new RadPhotoAdapter(Carddata, getContext(),this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        radrecyclerView.setLayoutManager(mLayoutManager);
        radrecyclerView.setAdapter(radPhotoAdapter);
        prepareRadPhotoData();
        setHasOptionsMenu(true);/// to disable icon from menu


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

//    @Override
//    public void onPrepareOptionsMenu(Menu menu) {
//        MenuItem item = menu.findItem(R.id.action_dept);
//        item.setVisible(false);
//        super.onPrepareOptionsMenu(menu);
//
//    }

    private void prepareRadPhotoData() {

        mInterfacePatient.showLoading(true);
        Map<String, String> map = new HashMap<>();
        map.put("PATREC_CODE", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("P_FROM_DATE", ((ActivityPatient) getActivity()).getmCardviewDataModel().getIndate());
        map.put("TRANS_SCREEN_CD_IN", fragment_cd);
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "2");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "أشعة");
        map.put("HOS_NO", ((ActivityPatient) getActivity()).getmCardviewDataModel().getHOS_NO() + "");
        //        map.put("ORDER_DEP_CD", Controller.ORDER_DEP_CD);

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.GET_GET_PHOTO_RAD_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {

                    JSONArray RAD_ARR = jsonObject.getJSONArray("RAD_REPORT");
                    if (RAD_ARR.length() > 0) {

                        for (int i = 0; i < RAD_ARR.length(); i++) {
                            JSONObject obj = RAD_ARR.getJSONObject(i);
                            if (obj != null) {
                                if (obj.getString("ORDER_CODE") != null) {
                                    RadphotoModel Card = new RadphotoModel(
                                            obj.getString("ORDER_DATE"),
                                            obj.getString("SERVICE_NAME_AR"),
                                            obj.getString("ORGAN_SERVICE_CD"),
                                            obj.getString("ORGAN_NAME_AR"),
                                            obj.getString("ORGAN_CODE"),
                                            obj.getString("MRP_ID")


                                    );
                                    Carddata.add(Card);
                                }
                            }
                        }
                    } else {
                        LinearLayout emptyEfile = getView().findViewById(R.id.emptyEfile_layout);
                        radheader.setVisibility(View.GONE);
                        emptyEfile.setVisibility(View.VISIBLE);
                    }
                    radrecyclerView.setAdapter(radPhotoAdapter);
                    radPhotoAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("json", "ERROR");
                }
                mInterfacePatient.showLoading(false);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                Controller.view_error(volleyError, getContext());
                mInterfacePatient.showLoading(false);
            }
        });
        jsObjRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                // Here goes the new timeout 3 minutes
                return 3 * 60 * 1000;
            }

            @Override
            public int getCurrentRetryCount() {
                // The max number of attempts
                return 5;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        Controller.getInstance().addToRequestQueue(jsObjRequest);
    }


    public void onResume() {
        super.onResume();
        // Set title bar
        ((ActivityPatient) getActivity()).setTitle("نتائج الأشعة");

    }

    public String foldername = "",myphoto;
    @Override
    public void myClick(Object mObject) {
        foldername = Controller.pref.getString("foldername", "");
        if(mObject instanceof RadphotoModel)
        {
            RadphotoModel mRadphotoModel = (RadphotoModel) mObject;

            String my_MRP_ID = mRadphotoModel.getMRP_ID();
            String my_SERVICE_NAME_AR = "";
            Log.e("onClick: ", "" + mRadphotoModel.getORGAN_SERVICE_CD());
            if (mRadphotoModel.getORGAN_SERVICE_CD().equals("1")) {
                my_SERVICE_NAME_AR = "CR";
            } else if (mRadphotoModel.getORGAN_SERVICE_CD().equals("3")) {
                my_SERVICE_NAME_AR = "CT";
            } else if (mRadphotoModel.getORGAN_SERVICE_CD().equals("6")) {
                my_SERVICE_NAME_AR = "MRI";
            }
            Log.e("my_SERVICE_NAME_AR", "" + my_SERVICE_NAME_AR);
            //    String myphoto = "http://10.20.11.4:3000/studylist?id=" + my_MRP_ID + "&modality=" + my_SERVICE_NAME_AR;
            if (foldername.length() > 0) {
                myphoto = "http://" + foldername + "-pacs.moh.gov.ps:3000/studylist?id=" + my_MRP_ID + "&modality=" + my_SERVICE_NAME_AR;
                Log.e("folderrad", myphoto);
            } else {
               myphoto = "http://-pacs.moh.gov.ps:3000/studylist?id=" + my_MRP_ID + "&modality=" + my_SERVICE_NAME_AR;
               // myphoto = "http://192.168.5.2:3000/studylist?id=" + my_MRP_ID + "&modality=" + my_SERVICE_NAME_AR;

               // myphoto = "http://172.16.170.50:8042/studylist?id=" + my_MRP_ID + "&modality=" + my_SERVICE_NAME_AR;
                // myphoto = "http://10.20.10.21:3000/studylist?id=" + my_MRP_ID + "&modality=" + my_SERVICE_NAME_AR;

            }

            Log.e("urlpdf", myphoto);
//            Intent mIntent = new Intent(getContext(), ActivityWeb.class);
//            mIntent.putExtra("url",myphoto);
//            startActivity(mIntent);
            Webview_Fragment mWebview_Fragment = new Webview_Fragment();
            Bundle mBundle = new Bundle();
            mBundle.putString("url",myphoto);
            mWebview_Fragment.setArguments(mBundle);
            ((ActivityPatient)getActivity()).CallFragment(mWebview_Fragment);
//            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(myphoto));
//            startActivity(browserIntent);
//            Controller.LOADER_DIALOG.hideDialog();
        }
    }
}
