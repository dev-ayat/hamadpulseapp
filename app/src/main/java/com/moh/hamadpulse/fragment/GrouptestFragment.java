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
import com.moh.hamadpulse.MyRequest;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.adapters.LaborderAdapter;
import com.moh.hamadpulse.constants.CustomRequest;
import com.moh.hamadpulse.dialog.DialogLoding;
import com.moh.hamadpulse.dialog.DialogMsg;
import com.moh.hamadpulse.models.LaborderModel;
import com.moh.hamadpulse.models.Labordertestmodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrouptestFragment extends Fragment implements LaborderAdapter.myInterface {
    private static ArrayList<LaborderModel> Carddata;
    private static ArrayList<Labordertestmodel> Favlist;
    RecyclerView recyclerView;
    LaborderAdapter laborderAdapter;
    LaborderModel mlaborderModel;
    ArrayList<Labordertestmodel> MyOutput;
    ArrayList<Labordertestmodel> MyFavList;
    DialogLoding mDialogLoding;
    DialogMsg mDialogMsg;
    favtestFragment mfavtestFragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grouptest, container, false);
        mfavtestFragment = new favtestFragment();
        recyclerView = view.findViewById(R.id.laborder_groups_recyclerv);
        Carddata = new ArrayList<>();
        laborderAdapter = new LaborderAdapter(Carddata, getContext(), this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(laborderAdapter);
        MyOutput = new ArrayList<>();
        Favlist = new ArrayList<>();
        preparedeptData();
        return view;
    }

    @Override
    public void myClick(Labordertestmodel mLabordertestmodel) {
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

    public void maddFavClick(Labordertestmodel mLabordertestmodel) {

        Log.e("fav", mLabordertestmodel.getTEST_CD() + " " + mLabordertestmodel.getTEST_NAME());
        MyFavList = new ArrayList<>();
        MyFavList.add(mLabordertestmodel);
        Log.e("fav", "MyFavList.size()=" + MyFavList.size());
        List<String> mFavList = new ArrayList<>();
        for (int i = 0; i < MyFavList.size(); i++) {
            Log.e("fav", "MyFavList.get(i).getTEST_NAME()=" + MyFavList.get(i).getTEST_NAME());
            mFavList.add(MyFavList.get(i).getTEST_NAME());
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setTitle("تأكيد الإضافة إلى المفضلة")
                .setIcon(R.drawable.testicon)
                .setItems(mFavList.toArray(new CharSequence[mFavList.size()]), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Controller.LOADER_DIALOG.showDialog("جاري الإضافة للمفضلة");
                        sendFavListData();
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        builder.create();
        builder.show();


    }

    private void preparedeptData() {

        Map map = new HashMap<>();
        map.put("HOS_NO", ((ActivityPatient) getActivity()).getmCardviewDataModel().getHOS_NO() + "");

        MyRequest.makeRquest(getContext(), Controller.LAB_ORDER_GROUPS_URL, map, new MyRequest.CallBack() {
            @Override
            public void Result(String response) {
                try {
                    JSONObject mJSONObject = new JSONObject(response);
                    JSONArray groups_arr = mJSONObject.getJSONArray("GROUP");
                    if (groups_arr.length() > 0) {
                        for (int i = 0; i < groups_arr.length(); i++) {
                            JSONObject group_obj = (JSONObject) groups_arr.get(i);
                            if (group_obj != null) {
                                if (group_obj.getString("GROUP_CD") != null) {
                                    mlaborderModel = new LaborderModel(group_obj.getString("GROUP_NAME_EN"), group_obj.getString("GROUP_CD"));
                                    Carddata.add(mlaborderModel);
                                    JSONArray CATEGORY_arr = group_obj.getJSONArray("CATEGORY");
                                    if (CATEGORY_arr.length() > 0) {

                                        for (int y = 0; y < CATEGORY_arr.length(); y++) {
                                            JSONObject CAT_obj = CATEGORY_arr.getJSONObject(y);
                                            Labordertestmodel labordertestmodel = new Labordertestmodel(
                                                    CAT_obj.getString("CATEGORY_NAME_EN"),
                                                    CAT_obj.getString("CATEGORY_CD")
                                            );
                                            mlaborderModel.getmListCat().add(labordertestmodel);
                                        }
                                    }

                                }
                            }
                        }
                    } else {

                    }
                    recyclerView.setAdapter(laborderAdapter);
                    laborderAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }

            @Override
            public void Error(VolleyError error) {
                Controller.view_error(error, getContext());
            }
        });


    }

    private void sendFavListData() {

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

        Map<String, String> map = new HashMap<>();
        map.put("DOC_ID", String.valueOf(Controller.pref.getInt("USER_CODE", -1)));
        map.put("CAT_LIST", CatArray.toString());
        map.put("HOS_NO", ((ActivityPatient) getActivity()).getmCardviewDataModel().getHOS_NO() + "");
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.INSERT_FAV_LAB_TEST_URL, map, new Response.Listener<JSONObject>() {
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
                                Controller.Msg_DIALOG.showDialog(" تمت الإضافة بنجاح");
                                Favlist.clear();
                            }
                        }, 2000);
                        Controller.Msg_DIALOG.hideDialog();
                    } else if (res == 3) {
                        Handler aHandler = new Handler();
                        aHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Controller.LOADER_DIALOG.hideDialog();
                                Controller.Msg_DIALOG.showDialog("الفحص مضاف مسبقاً للمفضلة");

                            }
                        }, 2000);
                        Controller.Msg_DIALOG.hideDialog();
                    } else {
                        Handler aHandler = new Handler();
                        aHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Controller.LOADER_DIALOG.hideDialog();
                                Controller.Msg_DIALOG.showDialog("تمت الإضافة مسبقاً");
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

    public interface OnCatClickListener {
        void onCatChecked(View v, int position);

        void onCatFavClicked(View v, int position);

    }

}
