package com.moh.hamadpulse.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.InterfacePatient;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.adapters.DocTyesConstSpinnerAdapter;
import com.moh.hamadpulse.adapters.PhotoBitmapAdapter;
import com.moh.hamadpulse.constants.CustomRequest;
import com.moh.hamadpulse.constants.VolleyMultipartRequest;
import com.moh.hamadpulse.models.GetDocumentsTypeConst;
import com.moh.hamadpulse.models.photoBitmapModel;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class EfilePhotoCopingFragment extends Fragment implements View.OnClickListener{

    String USER_CODE,USER_ID,DocTypecd;
    TextView txtpatid, txtpatName, txtadmdate, txtdaycount;
    ArrayList<GetDocumentsTypeConst> DocumentsTypeArrayList;
    Spinner DocTypeSpinner;
    Button btn_pickimg, btn_upload;
    DocTyesConstSpinnerAdapter docTyesSpinnerAdapter;
    ImageView img;
    Bitmap bitmap;
    String imgPath;
    ArrayList<photoBitmapModel> bmp_imageslist;
    PhotoBitmapAdapter photoBitmapAdapter;
    public static final int REQUEST_PERMISSION = 0;
    String fragment_cd = "17";
    public EfilePhotoCopingFragment() {
        // Required empty public constructor
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
        View view = inflater.inflate(R.layout.fragment_efile_photo_coping, container, false);
        txtpatName = view.findViewById(R.id.txtpatName);
        txtpatid = view.findViewById(R.id.txtpatid);
        txtadmdate = view.findViewById(R.id.txtadmdate);
        DocTypeSpinner = view.findViewById(R.id.DocTypeSpinner);
        btn_pickimg = view.findViewById(R.id.btn_pickimg);
        btn_upload = view.findViewById(R.id.btn_upload);
        img = view.findViewById(R.id.img);

        USER_CODE = String.valueOf(Controller.pref.getInt("USER_CODE", -1));
        USER_ID = Controller.pref.getString("USER_ID", "");
        DocumentsTypeArrayList = new ArrayList<>();
        setHasOptionsMenu(true);
        bmp_imageslist = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView bitmap_recycler_view = (RecyclerView) view.findViewById(R.id.bitmap_recycler_view);
        bitmap_recycler_view.setLayoutManager(layoutManager);
        photoBitmapAdapter = new PhotoBitmapAdapter(getContext(), bmp_imageslist);
        bitmap_recycler_view.setAdapter(photoBitmapAdapter);
        PrepareGetDocumenttypesData();
        DocTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DocTypecd = ((GetDocumentsTypeConst) DocTypeSpinner.getSelectedItem()).getDOCTYPECD();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_pickimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // PickImageDialog.on(getSupportFragmentManager(), new PickSetup());
                haveStoragePermission();
                PickImageDialog.build(new PickSetup()).show(getActivity()).setOnPickResult(new IPickResult() {
                    @Override
                    public void onPickResult(PickResult r) {
                        if (r.getError() == null) {
                            //If you want the Uri.
                            //Mandatory to refresh image from Uri.
                            //getImageView().setImageURI(null);

                            //Setting the real returned image.
                            //getImageView().setImageURI(r.getUri());

                            //If you want the Bitmap.

                            bmp_imageslist.add(new photoBitmapModel(r.getBitmap()));
                            photoBitmapAdapter.notifyDataSetChanged();

                            //  img.setImageBitmap(r.getBitmap());
                            // bitmap=r.getBitmap();
                            //Image path
                            imgPath = r.getPath();
                            //   Toast.makeText(getActivity(), r.getPath(), Toast.LENGTH_SHORT).show();

                        } else {
                            //Handle possible errors
                            //TODO: do what you have to do with r.getError();
                            Toast.makeText(getActivity(), r.getError().getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                });
            }
        });

        btn_upload.setOnClickListener(this);
        return view;
    }

    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public void enableBtnUpload(boolean b)
    {
        if(b)
            btn_upload.setOnClickListener(this);
        else
            btn_upload.setOnClickListener(null);
    }

    private void uploadBitmap(String DocTypecd) {

        mInterfacePatient.showLoading(true);
        enableBtnUpload(false);
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, Controller.INSERT_ARCHIVE_DOCUMENTS_URL,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        Log.e("volleyMultipartRequest", new String(response.data));
                        try {
                            Log.e("volleyMultipartRequest", new String(response.data));
                            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers)).trim();
                            AddPhotosForPatient();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                            enableBtnUpload(true);
                            mInterfacePatient.showLoading(false);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        Controller.view_error(error, getContext());
                        enableBtnUpload(true);
                        mInterfacePatient.showLoading(false);
                    }
                }) {

            /*
             * If you want to add more parameters with the image
             * you can do it here
             * here we have only one parameter with the image
             * which is tags
             * */

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                try {
                    params.put("patname", URLEncoder.encode(((ActivityPatient)getActivity()).getmCardviewDataModel().getPatname(), "utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                params.put("patmrp", ((ActivityPatient)getActivity()).getmCardviewDataModel().getPtmrpid());
                params.put("patid", ((ActivityPatient)getActivity()).getmCardviewDataModel().getPatid()+"");
                params.put("sex_cd", ((ActivityPatient)getActivity()).getmCardviewDataModel().getMRP_SEX_CD());
                params.put("sex_name", ((ActivityPatient)getActivity()).getmCardviewDataModel().getH_NAME_AR());
                params.put("hos_no", ((ActivityPatient)getActivity()).getmCardviewDataModel().getHOS_NO());
                params.put("hos_name", ((ActivityPatient)getActivity()).getmCardviewDataModel().getH_NAME_AR());
                params.put("patDOB", ((ActivityPatient)getActivity()).getmCardviewDataModel().getMRP_DOB());
                params.put("USER_CODE", USER_CODE);
                params.put("USER_ID", USER_ID);
                params.put("LOC_CD", ((ActivityPatient)getActivity()).getmCardviewDataModel().getHOS_PERMISSION());
                params.put("DocTypecd", DocTypecd);
                Log.e("photoparams", params.toString());
                return params;
            }

            /*
             * Here we are passing image by renaming it with a unique name
             * */
            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                for (int i = 0; i < bmp_imageslist.size(); i++) {
                    params.put("pic[" + i + "]", new DataPart(i + imagename + ".JPEG", getFileDataFromDrawable(bmp_imageslist.get(i).getBitmap())));
                }
                return params;
            }
        };

        //adding the request to volley
        Volley.newRequestQueue(getContext()).add(volleyMultipartRequest);
    }

    public void PrepareGetDocumenttypesData() {
        Map<String, String> map = new HashMap<>();
        //        map.put("ORDER_DEP_CD", Controller.ORDER_DEP_CD);
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.GET_ARCHIVE_DOCUMENTS_TYPES_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("response", "ayat resp" + response.toString());
                try {
                    Log.e("response2", response.toString());

                    JSONArray jsonArray = response.getJSONArray("DOCUMENTS_TYPES");
                    Log.e("jsonarray", "ayat" + jsonArray.toString());

                    Gson gson = new Gson();
                    Type type = new TypeToken<List<GetDocumentsTypeConst>>() {
                    }.getType();
                    DocumentsTypeArrayList = gson.fromJson(jsonArray.toString(), type);
                    DocumentsTypeArrayList.add(0, new GetDocumentsTypeConst("اختر نوع الملف ... ", "0"));
                    Log.e("list", DocumentsTypeArrayList.toString());
                    Log.e("size", DocumentsTypeArrayList.size() + "");
                    docTyesSpinnerAdapter = new DocTyesConstSpinnerAdapter(getContext(), 0, DocumentsTypeArrayList);
                    DocTypeSpinner.setAdapter(docTyesSpinnerAdapter);
                    docTyesSpinnerAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {
                Log.e("onErrorResponse", "error : " + volleyError.getMessage());
                Controller.view_error(volleyError, getContext());
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

    public void AddPhotosForPatient() {


        Map<String, String> params = new HashMap<>();
        params.put("P_PATREIC_CD", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        params.put("P_PHOTOS_HOS_NO", ((ActivityPatient) getActivity()).getmCardviewDataModel().getHOS_NO());
        params.put("P_USER_ID", USER_ID);
        params.put("P_DOC_TYPE_CD", DocTypecd);

        params.put("TRANS_SCREEN_CD_IN", fragment_cd);
        params.put("TRANS_USER_CODE_IN", (Controller.pref.getString("USER_ID", "")));
        params.put("TRANS_ACTION_CD_IN", "2");
        params.put("TRANS_DOCUMENT_CD_IN", ((ActivityPatient) getActivity()).getmCardviewDataModel().getPatid() + "");
        params.put("TRANS_IP_ADDRESS_IN", (Controller.pref.getString("IP_Address", "")));
        params.put("TRANS_DESCRIPTION_IN", "الأرشيف الطبي");
        //        params.put("ORDER_DEP_CD", Controller.ORDER_DEP_CD);

        Log.e("ayat", params.toString());

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.INSERT_PHOTOS_URL, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    int res = response.getInt("P_RESULT");
                    if (res == 1)
                        Toast.makeText(getContext(), "تمت الإضافة بنجاح", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getContext(), "لم تتم الإضافة", Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("json", "ERROR");
                }
                enableBtnUpload(true);
                mInterfacePatient.showLoading(false);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {

                Controller.view_error(volleyError, getContext());
                enableBtnUpload(true);
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


    public boolean haveStoragePermission() {

        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.e("Permission error", "You have permission");
                return true;
            } else {

                Log.e("Permission error", "You have asked for permission");
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION);
                return false;
            }
        } else {
            Log.e("Permission error", "You already have the permission");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //you have the permission now.
                Toast.makeText(getContext(), "permission granted", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_upload:
                if(DocTypecd==null || DocTypecd.equals("0"))
                {
                    Toast.makeText(getContext(), "اختر نوع الملف", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(bmp_imageslist.size()==0)
                {
                    Toast.makeText(getContext(), "الرجاء ادراج صورة واحدة ع الأقل", Toast.LENGTH_SHORT).show();
                    return;
                }
                uploadBitmap(DocTypecd);
                break;
        }
    }
}
