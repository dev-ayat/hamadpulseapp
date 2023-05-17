package com.moh.hamadpulse.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.OnAdapterClick;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.dialog.DialogMsg;
import com.moh.hamadpulse.fragment.newradresFragment;
import com.moh.hamadpulse.models.BasicInfoModel;
import com.moh.hamadpulse.models.GetRadViewer;
import com.moh.hamadpulse.models.ImageBase64Model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
public class RadViewrAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    OnAdapterClick mOnAdapterClick;
    Loader loader;
    private ArrayList<GetRadViewer> radViewerList;
    private ArrayList<BasicInfoModel> list;
    private Context context;
    private Controller mcontroller;
    String image;
    public ArrayList<BasicInfoModel> getList() {
        return list;
    }

    public void setList(ArrayList<BasicInfoModel> list) {
        this.list = list;
    }

    public RadViewrAdapter(ArrayList<GetRadViewer> radViewerList, Context context, newradresFragment fragment) {
        this.radViewerList = radViewerList;
        this.context = context;
        this.mOnAdapterClick = (OnAdapterClick) fragment;
        this.loader = (Loader) fragment;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater Inflater = LayoutInflater.from(parent.getContext());
        View view = Inflater.inflate(R.layout.row_rad_viewer, parent, false);
        return new RadViewholder(view);
    }
    public void writeToFile(String data, Context context){
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            Toast.makeText(context, "done", Toast.LENGTH_LONG).show();
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        RadViewholder mradViewholder = (RadViewrAdapter.RadViewholder) holder;
        if(list==null) {
            GetRadViewer data = radViewerList.get(position);
            mradViewholder.txtdatetime.setText(data.getInstanceTimestamp());
            mradViewholder.txtmodality.setText(data.getModality());
            // mradViewholder.txtimageurl.setText(data.getInstanceImage());
            mcontroller.Msg_DIALOG = new DialogMsg(context);
            Log.e("radv", "ayaaat" + data.getInstanceImage());

            mradViewholder.imglink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                Log.e("base64", "onClick: " + data.getImage_base64());
//                String url = "data:image/webp;base64," + data.getImage_base64();
                    mOnAdapterClick.myClick(data.getImage_base64());
//                mOnAdapterClick.myClick(url);

                }
            });

            mradViewholder.ohiv_link.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String ohiv_link = data.getOhivLink();
                    Log.e("ohiv_link: ", "" + ohiv_link);
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(ohiv_link));
                    context.startActivity(browserIntent);

                }
            });
        }else{
            BasicInfoModel data = list.get(position);
            String date_time[]=data.getTime_instance().split(",");
            mradViewholder.txtdatetime.setText(date_time[0].trim()+"\n"+date_time[1].trim());
            mradViewholder.txtmodality.setText(data.getModality());
            mradViewholder.imglink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("instance", "onClick: " + data.getInstance());

//                    Log.e("base64", "onClick: " + list.get(position).getImage_base64());
//                    String url = "data:image/webp;base64," + list.get(position).getImage_base64();

                    getImageBase64(data.getInstance());


//                    writeToFile(url, context);
//
//                    mOnAdapterClick.myClick(url);

                }
            });
            mradViewholder.ohiv_link.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String ohiv_link = data.getOhiv_link();
                    Log.e("ohiv_link: ", "" + ohiv_link);
                    if (ohiv_link != null) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(ohiv_link));
                        context.startActivity(browserIntent);
                    }
                }
            });
        }


//        mradViewholder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mOnAdapterClick.myClick(data);
//            }
//        });




    }

    private void getImageBase64(String instance) {
        loader.showLoading(true);
//       Log.d("show_loder",(mInterfacePatient==null)+"");
        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("EmpID", (Controller.pref.getString("USER_ID", "")));
            jsonBody.put("Instance", instance + "");
            jsonBody.put("Platform", "mobile");
            jsonBody.put("appToken", "123a665a4592042");
            Log.d("paythonmap:  ", jsonBody.toString());
            JsonObjectRequest jsonOblect = new JsonObjectRequest(Request.Method.POST, Controller.ROOT_RAD_python_img,
                    jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("Responsepaytonimg:  ", response.toString());

                    JSONObject jsonObject = null;

                    try {
                        jsonObject = response.getJSONObject("data");
                        Gson gson = new Gson();
                        ImageBase64Model model = gson.fromJson(jsonObject.toString(),
                                ImageBase64Model.class);
//                    image = response.getString("image_base64");
//                    Log.d("image:  ", image + "55555555555555555");
                        mOnAdapterClick.myClick(model.getImage_base64());
//                    String image=model.getInstance_info().getBasic_info().get(0).getImage_base64();
//
//                    writeToFile(model.getImage_base64(),context);
//                    Log.d("Tamam",model.getInstance_info().getBasic_info().get(0).getImage_base64().toString());

                    } catch (JSONException e) {

                        e.printStackTrace();
                    } finally {
                        loader.showLoading(false);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Controller.view_error(error, context);
                    loader.showLoading(false);

                }
            });

            Controller.getInstance().addToRequestQueue(jsonOblect);
            jsonOblect.setRetryPolicy(new DefaultRetryPolicy(
                    60000,
                    3,
                    3));
        } catch (JSONException e) {
            e.printStackTrace();

        }

    }

    @Override
    public int getItemCount() {
        return list!=null?list.size():radViewerList.size();
    }

    public ArrayList<GetRadViewer> getRadViewerList() {
        return radViewerList;
    }

    public void setRadViewerList(ArrayList<GetRadViewer> radViewerList) {
        this.radViewerList = radViewerList;
    }

    public class RadViewholder extends RecyclerView.ViewHolder {

        TextView txtdatetime, txtmodality;
        ImageButton imglink;
        ImageButton ohiv_link;

        public RadViewholder(@NonNull View itemView) {
            super(itemView);

            this.txtmodality = (TextView) itemView.findViewById(R.id.txtmodality);
            this.txtdatetime = (TextView) itemView.findViewById(R.id.txtdatetime);
            this.imglink = (ImageButton) itemView.findViewById(R.id.imglink);
            this.ohiv_link = (ImageButton) itemView.findViewById(R.id.ohiv_link);
        }
    }

    public interface Loader {
        void showLoading(boolean b);
    }
}