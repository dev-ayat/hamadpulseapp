package com.moh.hamadpulse.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.InterfacePatient;
import com.moh.hamadpulse.OnAdapterClick;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.adapters.RadViewrAdapter;
import com.moh.hamadpulse.models.GetRadViewer;
import com.moh.hamadpulse.models.RadViewModel;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class newradresFragment extends Fragment implements OnAdapterClick, com.moh.hamadpulse.adapters.RadViewrAdapter.Loader {
    LinearLayout main_layout;
    private static com.moh.hamadpulse.adapters.RadViewrAdapter RadViewrAdapter;
    private static RecyclerView radrecyclerView;
    private static ArrayList<GetRadViewer> Carddata;
    public String fragment_cd = "6";
    public String foldername = "", myphoto;
    ImageButton get_rad;
    EditText txt_PID;
    TextView rad_count;
    CheckBox select_all;
    String txtdatetime, txtmodality, txtimageurl;
    LinearLayout radheader, emptyEfile_layout, from_p_layout;
    InterfacePatient mInterfacePatient;
    AVLoadingIndicatorView imgLoading;
    private RecyclerView.LayoutManager layoutManager;
    boolean flag;

    public newradresFragment() {
        Log.e("test", newradresFragment.class.getSimpleName());
    }

    public newradresFragment(InterfacePatient mInterfacePatient, boolean flag) {
        this.mInterfacePatient = mInterfacePatient;
        this.flag = flag;

    }


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
        get_rad = view.findViewById(R.id.get_rad);
        rad_count = view.findViewById(R.id.rad_count);
        imgLoading = view.findViewById(R.id.imgLoadingLoader);
        select_all = view.findViewById(R.id.select_all);
        emptyEfile_layout = view.findViewById(R.id.emptyEfile_layout);
        from_p_layout = view.findViewById(R.id.from_p_layout);
        txt_PID = view.findViewById(R.id.txt_PID);
        main_layout = view.findViewById(R.id.main_layout);
        if (flag) {
            txt_PID.setVisibility(View.VISIBLE);
            //set margin top by tool bar size
            TypedValue tv = new TypedValue();
            getActivity().getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true);
            int actionBarHeight = getResources().getDimensionPixelSize(tv.resourceId);
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            layoutParams.topMargin = actionBarHeight;
            main_layout.setLayoutParams(layoutParams);
        }
        Carddata = new ArrayList<>();
        RadViewrAdapter = new RadViewrAdapter(Carddata, getContext(), this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        radrecyclerView.setLayoutManager(mLayoutManager);
        radrecyclerView.setAdapter(RadViewrAdapter);
        setHasOptionsMenu(true);/// to disable icon from menu

        txt_PID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0)
                    txt_PID.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        get_rad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String r_cound = rad_count.getText().toString().trim();
                String pid = txt_PID.getText().toString().trim();
                if (flag && (pid.isEmpty() || pid.length() < 9)) {
                    txt_PID.setError("يجب أن لا يقل رقم الهوية عن 9 أرقام");
                    return;
                }
                if (r_cound.isEmpty() && !select_all.isChecked()) {
                    Toast.makeText(getContext(), "please select the count", Toast.LENGTH_SHORT).show();
                    return;
                }
                Getpacsdata(select_all.isChecked() ? "" : r_cound);
            }
        });
        select_all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    rad_count.setText("");
            }
        });
        if (!flag)
            Getpacsdata("2");

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

    public void onResume() {
        super.onResume();
        // Set title bar
        if (getActivity() instanceof ActivityPatient)
            ((ActivityPatient) getActivity()).setTitle("نتائج الأشعة");

    }

    @Override
    public void myClick(Object mObject) {
//        Log.e("onClick: ", "" + mObject);
//        foldername = Controller.pref.getString("foldername", "");
//        myphoto = "" + mObject;
//        Log.e("URL", myphoto);
//        Webview_Fragment mWebview_Fragment = new Webview_Fragment();
//        Bundle mBundle = new Bundle();
//        mBundle.putString("url", myphoto);
//        mWebview_Fragment.setArguments(mBundle);
//        ((ActivityPatient) getActivity()).CallFragment(mWebview_Fragment);
//        Toast.makeText(getContext(), mObject.toString(), Toast.LENGTH_LONG).show();
        byte[] code= Base64.decode(mObject.toString(),Base64.DEFAULT);
        Bitmap image= BitmapFactory.decodeByteArray(code,0,code.length);

        Image_View_Fragment fragment=new Image_View_Fragment(image);
        FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
        fragment.show(ft,"View Rad Image");
//        ((ActivityPatient) getActivity()).CallFragment(new Image_View_Fragment(image));

    }

    private String readFromFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("config.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append("\n").append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }

    public void writeToFile(String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            Toast.makeText(getContext(), getActivity().getFilesDir()+"/config.txt", Toast.LENGTH_LONG).show();
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public void Getpacsdata(String count) {
        showLoading(true);
        try {
            Log.d("MSG", flag ? txt_PID.getText().toString().trim().length() + "" : "test");
            String URL = "";
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("EmpID", (Controller.pref.getString("USER_ID", "")));
            jsonBody.put("PatientID", flag ? txt_PID.getText().toString().trim() :
                    ((ActivityPatient) getActivity()).getmCardviewDataModel().getPtmrpid() + "");
            jsonBody.put("PatientID", "802331306"); // hamad

            jsonBody.put("Modality", "*");
            jsonBody.put("Count", count);
            jsonBody.put("ImageDate", "");
            jsonBody.put("Platform", "mobile");
            jsonBody.put("appToken", "123a665a4592042");
            Log.d("root:  ", Controller.ROOT_RAD_python);
            Log.d("root:  ", jsonBody + "");
            Log.d("MAP:  ", jsonBody.toString() + "");
            JsonObjectRequest jsonOblect = new JsonObjectRequest(Request.Method.POST, Controller.ROOT_RAD_python, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("Response:  ", response.toString());

                    JSONObject jsonObject = null;

                    //                        jsonObject = response.getJSONObject("data");


                    Gson gson = new Gson();
                    RadViewModel model = null;
                    try {
                        model = gson.fromJson(response.getJSONObject("data").toString(),
                                RadViewModel.class);

//                    RadViewrAdapter.setList(model.getInstance_info().getBasic_info());
//                    String image=model.getInstance_info().getBasic_info().get(0).getImage_base64();

                        if (model != null) {
                            Log.d("status", model.getStatus());
//                        Log.d("img_link",model.getInstance_info().getBasic_info().get(0).getImg_link());
                            if (model.getStatus().equals("1")) {
                                RadViewrAdapter.setList(model.getInstance_info().getBasic_info());
                                RadViewrAdapter.notifyDataSetChanged();
                            } else {
                                radrecyclerView.setVisibility(View.GONE);
                                emptyEfile_layout.setVisibility(View.VISIBLE);
                            }
                        }
//                    writeToFile(image,getContext());
//                    Log.d("Tamam",model.getInstance_info().getBasic_info().get(0).getImage_base64().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                        showLoading(false);
                    }
                    showLoading(false);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Controller.view_error(error, getContext());
                    showLoading(false);
                }
            });

            Controller.getInstance().addToRequestQueue(jsonOblect);
            jsonOblect.setRetryPolicy(new DefaultRetryPolicy(
                    60000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        } catch (JSONException e) {
            e.printStackTrace();
        }


//         Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_LONG).show();


    }

    public void showLoading(boolean b) {
        imgLoading.setVisibility(b ? View.VISIBLE : View.GONE);
    }


}