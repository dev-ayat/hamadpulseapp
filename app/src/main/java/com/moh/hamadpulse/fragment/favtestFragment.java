package com.moh.hamadpulse.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.adapters.LaborderfavtestAdapter;
import com.moh.hamadpulse.constants.CustomRequest;
import com.moh.hamadpulse.dialog.DialogLoding;
import com.moh.hamadpulse.dialog.DialogMsg;
import com.moh.hamadpulse.models.Labordertestmodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class favtestFragment extends Fragment implements LaborderfavtestAdapter.myfavInterface {
    private static ArrayList<Labordertestmodel> Favlist;
    RecyclerView recyclerViewfavlist;
    LaborderfavtestAdapter laborderfavtestAdapter;
    ArrayList<Labordertestmodel> MyOutput;
    ArrayList<Labordertestmodel> MyFavList;
    String fragment_cd = "33";


    public favtestFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favtest, container, false);
        recyclerViewfavlist = view.findViewById(R.id.laborder_fav_test_recyclerv);
        Favlist = new ArrayList<>();
        MyOutput = new ArrayList<>();
        laborderfavtestAdapter = new LaborderfavtestAdapter(Favlist, this);
        RecyclerView.LayoutManager mfavLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewfavlist.setLayoutManager(mfavLayoutManager);
        recyclerViewfavlist.setAdapter(laborderfavtestAdapter);
        preparefavlistData();
        Controller.LOADER_DIALOG = new DialogLoding(getContext());
        Controller.Msg_DIALOG = new DialogMsg(getContext());
        return view;
    }

    private void preparefavlistData() {
        Map<String, String> map = new HashMap<>();
        map.put("DOC_ID", String.valueOf(Controller.pref.getInt("USER_CODE", -1)));
        map.put("TRANS_SCREEN_CD_IN", fragment_cd);
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "1");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "إضافة إلى المفضلة");
        map.put("HOS_NO", ((ActivityPatient) getActivity()).getmCardviewDataModel().getHOS_NO() + "");
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.GET_FAV_LAB_TEST_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    Log.e("json:", jsonObject.toString());
                    JSONArray fav_arr = jsonObject.getJSONArray("FAV_LAB_TEST");
                    Log.e("json", jsonObject.toString());


                    Log.e("json", "" + fav_arr.length());
                    if (fav_arr.length() > 0) {
                        Log.e("json", "" + fav_arr.getJSONObject(0).getString("F_LAB_TEST_CD") + " " + fav_arr.getJSONObject(0).getString(("F_TEST_NAME")));

                        for (int i = 0; i < fav_arr.length(); i++) {
                            JSONObject fav_obj = (JSONObject) fav_arr.get(i);
                            if (fav_obj != null) {
                                if (fav_obj.getString("F_LAB_TEST_CD") != null) {

                                    Labordertestmodel mfavlaborderModel = new Labordertestmodel(fav_obj.getString("F_TEST_NAME"), fav_obj.getString("F_TEST_CD"));
                                    Favlist.add(mfavlaborderModel);
                                }
                            }
                        }
                    } else {

                    }
                    recyclerViewfavlist.setAdapter(laborderfavtestAdapter);
                    laborderfavtestAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("json", "ERROR" + e.getMessage());

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                Controller.view_error(volleyError, getContext());
                Log.e("json", "ErrorListener");
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

    public void myfavClick(Labordertestmodel mLabordertestmodel) {
        Log.e("ERROR", mLabordertestmodel.getTEST_CD() + " " + mLabordertestmodel.getTEST_NAME());
        int pos = searchMyoutput(mLabordertestmodel.getTEST_CD());
        if (pos == -1)
            MyOutput.add(mLabordertestmodel);
        else
            MyOutput.remove(pos);
        //  Controller.setArrayPrefs(MyOutput);
    }

    public int searchMyoutput(String TestCD) {
        int pos = -1;
        for (int i = 0; i < MyOutput.size(); i++)
            if (MyOutput.get(i).getTEST_CD().equals(TestCD))
                pos = i;
        return pos;
    }

    @Override
    public void mRemoveFavClick(Labordertestmodel mLabordertestmodel) {

        Log.e("REMOVfav", mLabordertestmodel.getTEST_CD() + " " + mLabordertestmodel.getTEST_NAME());
        MyFavList = new ArrayList<>();
        MyFavList.add(mLabordertestmodel);
        Log.e("REMOVfav", "MyFavList.size()=" + MyFavList.size());
        List<String> mFavList = new ArrayList<>();
        for (int i = 0; i < MyFavList.size(); i++) {
            Log.e("REMOVfav", "MyFavList.get(i).getTEST_NAME()=" + MyFavList.get(i).getTEST_NAME());
            mFavList.add(MyFavList.get(i).getTEST_NAME());
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                    .setTitle("تأكيد الحذف من المفضلة")
                    .setIcon(R.drawable.testicon)
                    .setItems(mFavList.toArray(new CharSequence[mFavList.size()]), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    })
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Controller.LOADER_DIALOG.showDialog("جاري الحذف من المفضلة");
                            DelFavListData();
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
    }

    private void DelFavListData() {

        String Testcd = null;
        String Testname = null;
        JSONArray CatArray = new JSONArray();

        for (int i = 0; i < MyFavList.size(); i++) {
            Testcd = MyFavList.get(i).getTEST_CD();
            Testname = MyFavList.get(i).getTEST_NAME();
            JSONObject Testobj = new JSONObject();
            try {
                Testobj.put("Testcd", String.valueOf(Testcd));
                Testobj.put("Testname", String.valueOf(Testname));
                CatArray.put(Testobj);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        String frag_cd = "34";
        Map<String, String> map = new HashMap<>();
        map.put("DOC_ID", String.valueOf(Controller.pref.getInt("USER_CODE", -1)));
        map.put("CAT_LIST", CatArray.toString());
        map.put("HOS_NO", ((ActivityPatient) getActivity()).getmCardviewDataModel().getHOS_NO() + "");
        map.put("TRANS_SCREEN_CD_IN", frag_cd);
        map.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        map.put("TRANS_ACTION_CD_IN", "5");
        map.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        map.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        map.put("TRANS_DESCRIPTION_IN", "حذف إلى المفضلة");

        Log.e("map", map.toString());
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.DELETE_FAV_LAB_TEST_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("response:", response.toString());
                try {
                    int res = response.getInt("RESULT");
                    if (res == 1) {
                        Handler aHandler = new Handler();
                        aHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Controller.LOADER_DIALOG.hideDialog();
                                Controller.Msg_DIALOG.showDialog("تم الحذف بنجاح");
                                Favlist.clear();
                                preparefavlistData();
                            }
                        }, 2000);
                        Controller.Msg_DIALOG.hideDialog();
                    } else {
                        Handler aHandler = new Handler();
                        aHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Controller.LOADER_DIALOG.hideDialog();
                                Controller.Msg_DIALOG.showDialog("لم يتم الحذف");
                            }
                        }, 3000);
                        Controller.Msg_DIALOG.hideDialog();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Controller.view_error(error, getContext());
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

    public interface OnfavCatClickListener {
        void onFavCatChecked(View v, int position);

        void onRemoveFavClicked(View v, int position);

    }

}
