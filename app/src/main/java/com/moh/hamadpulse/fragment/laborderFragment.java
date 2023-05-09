package com.moh.hamadpulse.fragment;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.google.android.material.tabs.TabLayout;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.InterfacePatient;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.adapters.LaborderAdapter;
import com.moh.hamadpulse.adapters.LaborderfavtestAdapter;
import com.moh.hamadpulse.adapters.LabordertestAdapter;
import com.moh.hamadpulse.adapters.TabPagerAdapter;
import com.moh.hamadpulse.constants.CustomRequest;
import com.moh.hamadpulse.models.LaborderModel;
import com.moh.hamadpulse.models.Labordertestmodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// إضافة طلب مختبرات
public class laborderFragment extends Fragment implements LaborderfavtestAdapter.MyListenerFav
        , LabordertestAdapter.MyListenerCat, View.OnClickListener {

    private static ArrayList<LaborderModel> Carddata;
    private static ArrayList<Labordertestmodel> Favlist;
    LaborderAdapter laborderAdapter;
    LaborderfavtestAdapter laborderfavtestAdapter;
    //String patname, patid, , patadmcd, HOS_PERMISSION, , DEPT_CODE;
    String userid, doctor_spc;
    String patid,patadmcd;
    String pat_HOS_NO,patmrp,indate;

    ImageView card_arrow;
    RecyclerView recyclerView, recyclerViewfavlist;
    Button btn_addlaborder, btn_addcorona;

    ArrayList<Labordertestmodel> MyOutput;
    ArrayList<Labordertestmodel> MyFavList;
    LaborderModel mlaborderModel;
    TabLayout tabLayout;
    ViewPager viewPager;
    TextView badge;
    public String fragment_cd = "2";
    public laborderFragment() {

    }

    InterfacePatient mInterfacePatient;

    public InterfacePatient getmInterfacePatient() {
        return mInterfacePatient;
    }

    public void setmInterfacePatient(InterfacePatient mInterfacePatient) {
        this.mInterfacePatient = mInterfacePatient;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Log.e("test", getClass().getSimpleName());
//savedInstanceState.getSerializable("object");
        //MyOutput = (ArrayList<Labordertestmodel>)savedInstanceState.getSerializable("object");
        //Log.e("bundle1",""+MyOutput.size());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_laborder, container, false);
        badge = view.findViewById(R.id.badge);
        userid = Controller.pref.getString("USER_ID", "");
        doctor_spc = Controller.pref.getString("Doctor_spc", "");
//        card_arrow = view.findViewById(R.id.card_arrow);
        setHasOptionsMenu(true);/// to disable icon from menu
        MyOutput = new ArrayList<>();
        btn_addlaborder = view.findViewById(R.id.btn_add_laborder);
        //btn_addcorona = view.findViewById(R.id.btn_add_corona);
        btn_addlaborder.setOnClickListener(this);
        //   btn_addcorona.setOnClickListener(this);


        tabLayout = view.findViewById(R.id.tabs);
        viewPager = view.findViewById(R.id.pager);
        configureTabLayout();
        setHasOptionsMenu(true);/// to disable icon from menu

        LaborderfavtestAdapter.setmMyListenerFav(this);
        LabordertestAdapter.setmMyListenerCat(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //        patname = getArguments().getString("patname");
        patid = ((ActivityPatient)getActivity()).getmCardviewDataModel().getPatid()+"";
        patadmcd = ((ActivityPatient)getActivity()).getmCardviewDataModel().getAdmcd()+"";
        indate = ((ActivityPatient)getActivity()).getmCardviewDataModel().getIndate()+"";
        patmrp = ((ActivityPatient)getActivity()).getmCardviewDataModel().getPtmrpid()+"";
        pat_HOS_NO = ((ActivityPatient)getActivity()).getmCardviewDataModel().getHOS_NO()+"";
//        HOS_PERMISSION = getArguments().getString("HOS_PERMISSION");
//        DEPT_CODE = getArguments().getString("DEPT_CODE");
    }

    private void configureTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setText("المجموعات الرئيسية"));
        tabLayout.addTab(tabLayout.newTab().setText("المفضلة"));
        final PagerAdapter adapter = new TabPagerAdapter(getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (!(adapter == null)) {
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });

    }

//    @Override
//    public void onPrepareOptionsMenu(Menu menu) {
//        MenuItem item = menu.findItem(R.id.action_dept);
//        item.setVisible(false);
//        super.onPrepareOptionsMenu(menu);
//
//    }

    public void onResume() {
        super.onResume();
        //((HomeActivity) getActivity()).setActionBarTitle("طلب فحوصات مخبرية");
    }

    public void onClickFav(Labordertestmodel mLabordertestmodel) {
        Log.e("MYERROR", "Fav test name=" + mLabordertestmodel.getTEST_NAME());
        int pos = searchMyoutput(mLabordertestmodel.getTEST_CD());
        if (pos == -1)
            MyOutput.add(mLabordertestmodel);
        else
            MyOutput.remove(pos);

        String badgesize = String.valueOf(MyOutput.size());
        badge.setText(" +" + badgesize + " ");
    }

    @Override
    public void onClickCat(Labordertestmodel mLabordertestmodel) {

        Log.e("MYERROR", "Cat test name=" + mLabordertestmodel.getTEST_NAME());
        int pos = searchMyoutput(mLabordertestmodel.getTEST_CD());
        if (pos == -1)
            MyOutput.add(mLabordertestmodel);
        else
            MyOutput.remove(pos);
        String badgesize = String.valueOf(MyOutput.size());
        badge.setText(" +" + badgesize + " ");
    }

    public int searchMyoutput(String TestCD) {
        int pos = -1;
        for (int i = 0; i < MyOutput.size(); i++)
            if (MyOutput.get(i).getTEST_CD().equals(TestCD))
                pos = i;
        return pos;
    }

    public void addlaborder() {
        Log.e("ERRORsize", "MyOutput.size()=" + MyOutput.size());
        List<String> mListTest = new ArrayList<>();
        for (int i = 0; i < MyOutput.size(); i++) {
            Log.e("Ayat1", "MyOutput.get(i).getTEST_NAME()=" + MyOutput.get(i).getTEST_NAME());
            Log.e("Ayat2", "MyOutput.get(i).getTEST_CD()=" + MyOutput.get(i).getTEST_CD());
            mListTest.add(MyOutput.get(i).getTEST_NAME());
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setTitle("تأكيد إضافة الطلب للمختبر")
                .setIcon(R.drawable.testicon)
                .setItems(mListTest.toArray(new CharSequence[mListTest.size()]), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })

                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Controller.LOADER_DIALOG.showDialog("جاري إضافة الطلب للمختبر");
                        sendorderData();

                    }


                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Toast.makeText(getContext(),"Cancel",Toast.LENGTH_LONG).show();
                        dialog.dismiss();
//                        MyOutput=new ArrayList<>();
//                        badge.setText(" +0 ");
                    }
                });
        builder.create();
        builder.show();


    }

    public void enableBtnLabOrder(boolean b) {
        if (b)
            btn_addlaborder.setOnClickListener(this);
        else
            btn_addlaborder.setOnClickListener(null);
    }

//    public void enableBtnLabCovid(boolean b) {
//        if (b)
//            btn_addcorona.setOnClickListener(this);
//        else
//            btn_addcorona.setOnClickListener(null);
//    }


    private void sendorderData() {
        mInterfacePatient.showLoading(true);
        enableBtnLabOrder(false);
        String Testcd = null;
        JSONArray CatArray = new JSONArray();
        for (int i = 0; i < MyOutput.size(); i++) {
            Testcd = MyOutput.get(i).getTEST_CD();
            JSONObject Testobj = new JSONObject();
            try {
                Testobj.put("Testcd", String.valueOf(Testcd));
                CatArray.put(Testobj);
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("ayat", e.getMessage());
            }

        }
        Map<String, String> map = new HashMap<>();
        map.put("ORDER_DOC_NOTES", "");
        map.put("DOC_ID", userid);
        map.put("ORDER_PATREC_CD", patid);
        map.put("ORDER_ADMISSION_CD", patadmcd);
        map.put("CAT_LIST", CatArray.toString());
        map.put("HOS_NO", "");
        map.put("ORDER_DEP_CD",Controller.ORDER_DEP_CD);
        map.put("TRANS_SCREEN_CD_IN", fragment_cd);
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "1");
        map.put("TRANS_DOCUMENT_CD_IN", patid);
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "إضافة طلب مختبر");
        map.put("HOS_NO", ((ActivityPatient) getActivity()).getmCardviewDataModel().getHOS_NO() + "");
        Log.e("TAG", map.toString());
        mInterfacePatient.showLoading(true);
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.INSERT_LAB_ORDER_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("resresponse:", response.toString());
                int res = 0;
                try {
                    res = response.getInt("RESULT");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                mInterfacePatient.showLoading(false);
                enableBtnLabOrder(true);
                if (res == 1) {
                    Toast.makeText(getContext(), "تم إضافة الطلب بنجاح", Toast.LENGTH_SHORT).show();
                    MyOutput = new ArrayList<>();
                    badge.setText(" +0 ");
                    send_notification();
                    getActivity().onBackPressed();
                } else {
                    Toast.makeText(getContext(), "لم تتم إضافة الطلب", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Controller.view_error(error, getContext());
                mInterfacePatient.showLoading(false);
                enableBtnLabOrder(true);
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

    private void send_notification() {
        Map<String, String> map = new HashMap<>();
        map.put("P_DEP_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getLOC_CODE());
        map.put("P_USER_TYPE", "2");
        map.put("P_TITLE","إجراء عملية سحب عينة");
        map.put("P_MESSAGE", "مطولب إجراء سحب عينة للمريض :\n" +
                ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatname());
        map.put("P_IMG_URL", "https://cdn-icons-png.flaticon.com/512/172/172838.png");
        map.put("HOS_NO",  ((ActivityPatient) getActivity()).getmCardviewDataModel().getHOS_NO()+"");
        map.put("TRANS_SCREEN_CD_IN", fragment_cd);
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "1");
        map.put("TRANS_DOCUMENT_CD_IN", patid);
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "send notification for taking sample");
        map.put("HOS_NO", ((ActivityPatient) getActivity()).getmCardviewDataModel().getHOS_NO() + "");
        Log.e("TAG", map.toString());
//        mInterfacePatient.showLoading(true);
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.SEND_NOTIFICATION_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onErrorResponse(VolleyError error) {
                if(getContext()!=null)
                Controller.view_error(error, getContext());
//                mInterfacePatient.showLoading(false);

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

    public void ShowConfMsg() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setTitle(" هل تريد بالتأكيد إضافة تحليل COVID-19 إلى المختبر المركزي ؟")
                .setIcon(R.drawable.testicon)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Controller.LOADER_DIALOG.showDialog("جاري إضافة الطلب للمختبر المركزي");
                        AddCoronaLabOrder();
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Toast.makeText(getContext(),"Cancel",Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });
        builder.create();
        builder.show();
    }

    private void AddCoronaLabOrder() {
        String frag_cd = "3";

        mInterfacePatient.showLoading(true);
        // enableBtnLabCovid(false);
        Map<String, String> map = new HashMap<>();
        map.put("P_CREATED_BY", userid);
        map.put("P_PATIENT_ID", patmrp);
        map.put("P_LAST_VISIT_DATE", indate);
        map.put("P_REFERRED_FROM", "552");
        map.put("P_PLACE_ID", pat_HOS_NO);
        map.put("P_DEPARTMENT_ID", doctor_spc);
        map.put("TRANS_SCREEN_CD_IN", frag_cd);
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "1");
        map.put("TRANS_DOCUMENT_CD_IN", patid);
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "إضافة طلب مختبر كوفيد");

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.INSERT_CORONA_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("response:", response.toString());
                int res = 0;
                try {
                    res = response.getInt("RESULT");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                mInterfacePatient.showLoading(false);
                //  enableBtnLabCovid(true);
                if (res > 0) {
                    Toast.makeText(getContext(), "تم إضافة الطلب بنجاح", Toast.LENGTH_SHORT).show();
                    getActivity().onBackPressed();
                } else {
                    Toast.makeText(getContext(), "لم تتم إضافة الطلب", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Controller.view_error(error, getContext());
                mInterfacePatient.showLoading(false);
                //    enableBtnLabCovid(true);
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

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_add_laborder:
                addlaborder();
                break;
            case R.id.btn_add_corona:
                ShowConfMsg();
                break;
        }
    }
}
