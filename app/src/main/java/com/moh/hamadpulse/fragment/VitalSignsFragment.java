package com.moh.hamadpulse.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.evrencoskun.tableview.TableView;
import com.evrencoskun.tableview.filter.Filter;
import com.evrencoskun.tableview.pagination.Pagination;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moh.hamadpulse.ActivityPatient;
import com.moh.hamadpulse.Controller;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.Tableviewtest.test;
import com.moh.hamadpulse.activiteis.HomeActivity;
import com.moh.hamadpulse.adapters.VitalSignsAdapter;
import com.moh.hamadpulse.models.VitalSigns;
import com.moh.hamadpulse.tableview.TableViewAdapter;
import com.moh.hamadpulse.tableview.TableViewListener;
import com.moh.hamadpulse.tableview.TableViewModel;
import com.moh.hamadpulse.tableview.model.MyColumn;
import com.moh.hamadpulse.tableview.model.MyRow;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// عرض العلامات الحيوية
public class VitalSignsFragment extends Fragment {
    FloatingActionButton add_vital_sign;
    TextView txtpatid, txtpatName, txtadmdate, txtdaycount;
    String patname, patid, admdate, patadmcd;
    ArrayList<VitalSigns> vitalsignsArray;
    VitalSignsAdapter vitalsignsAdapter;
    RecyclerView vital_signs_recyclerview;
    LinearLayout vitalresult, emptyresult, vitallblresult;

    Gson mGson;
    ArrayList<MyRow> mListMyRow;
    ArrayList<test> mListTest;
    JSONObject mJSONObject;
    /////
    private Spinner moodFilter, genderFilter;
    private ImageButton previousButton, nextButton;
    private TextView tablePaginationDetails;
    private TableView mTableView;

    public VitalSignsFragment() {
        // Required empty public constructor
    }

    private Filter mTableFilter; // This is used for filtering the table.
    private Pagination mPagination; // This is used for paginating the table.
    private boolean mPaginationEnabled = false;
    // Handler for the changing of pages in the paginated TableView.
    @NonNull
    private Pagination.OnTableViewPageTurnedListener onTableViewPageTurnedListener = new
            Pagination.OnTableViewPageTurnedListener() {
                @Override
                public void onPageTurned(int numItems, int itemsStart, int itemsEnd) {
                    int currentPage = mPagination.getCurrentPage();
                    int pageCount = mPagination.getPageCount();
                    previousButton.setVisibility(View.VISIBLE);
                    nextButton.setVisibility(View.VISIBLE);

                    if (currentPage == 1 && pageCount == 1) {
                        previousButton.setVisibility(View.INVISIBLE);
                        nextButton.setVisibility(View.INVISIBLE);
                    }

                    if (currentPage == 1) {
                        previousButton.setVisibility(View.INVISIBLE);
                    }

                    if (currentPage == pageCount) {
                        nextButton.setVisibility(View.INVISIBLE);
                    }

                    tablePaginationDetails.setText(getString(R.string.table_pagination_details, String
                            .valueOf(currentPage), String.valueOf(itemsStart), String.valueOf(itemsEnd)));

                }
            };
    @NonNull
    private AdapterView.OnItemSelectedListener mItemSelectionListener = new AdapterView
            .OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            // 0. index is for empty item of spinner.
            if (position > 0) {

                String filter = Integer.toString(position);

                if (parent == moodFilter) {
                    filterTableForMood(filter);
                } else if (parent == genderFilter) {
                    filterTableForGender(filter);
                }
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            // Left empty intentionally.
        }
    };
    @NonNull
    private TextWatcher mSearchTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            filterTable(String.valueOf(s));
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    @NonNull
    private AdapterView.OnItemSelectedListener onItemsPerPageSelectedListener = new AdapterView
            .OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            int itemsPerPage;
            if ("All".equals(parent.getItemAtPosition(position).toString())) {
                itemsPerPage = 0;
            } else {
                itemsPerPage = Integer.parseInt(parent.getItemAtPosition(position).toString());
            }

            setTableItemsPerPage(itemsPerPage);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    @NonNull
    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == previousButton) {
                previousTablePage();
            } else if (v == nextButton) {
                nextTablePage();
            }
        }
    };
    @NonNull
    private TextWatcher onPageTextChanged = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            int page;
            if (TextUtils.isEmpty(s)) {
                page = 1;
            } else {
                page = Integer.parseInt(String.valueOf(s));
            }

            goToTablePage(page);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_vital_signs, container, false);
        add_vital_sign = layout.findViewById(R.id.btn_add_vital_sign);
        txtpatName = layout.findViewById(R.id.txtpatName);
        txtpatid = layout.findViewById(R.id.txtpatid);
        txtadmdate = layout.findViewById(R.id.txtadmdate);
        txtdaycount = layout.findViewById(R.id.txtdaycount);
        emptyresult = layout.findViewById(R.id.emptyresult_linearLayout);
        patname = getArguments().getString("patname");
        patid = getArguments().getString("patid");
        admdate = getArguments().getString("indate");
        patadmcd = getArguments().getString("patadm");
        Log.e("patient", "patient");
        Log.e("patientinfo", patid + "-----" + "" + patadmcd + "-----" + patname + "-----" + admdate);
        txtpatName.setText(patname);
        txtpatid.setText(patid);
        txtadmdate.setText(admdate);
        add_vital_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowAddVitalSigns();
            }
        });


        EditText searchField = layout.findViewById(R.id.query_string);
        searchField.addTextChangedListener(mSearchTextWatcher);

        moodFilter = layout.findViewById(R.id.mood_spinner);
        moodFilter.setOnItemSelectedListener(mItemSelectionListener);

        genderFilter = layout.findViewById(R.id.gender_spinner);
        genderFilter.setOnItemSelectedListener(mItemSelectionListener);

        Spinner itemsPerPage = layout.findViewById(R.id.items_per_page_spinner);

        View tableTestContainer = layout.findViewById(R.id.table_test_container);

        previousButton = layout.findViewById(R.id.previous_button);
        nextButton = layout.findViewById(R.id.next_button);
        EditText pageNumberField = layout.findViewById(R.id.page_number_text);
        tablePaginationDetails = layout.findViewById(R.id.table_details);

        if (mPaginationEnabled) {
            tableTestContainer.setVisibility(View.VISIBLE);
            itemsPerPage.setOnItemSelectedListener(onItemsPerPageSelectedListener);

            previousButton.setOnClickListener(mClickListener);
            nextButton.setOnClickListener(mClickListener);
            pageNumberField.addTextChangedListener(onPageTextChanged);
        } else {
            tableTestContainer.setVisibility(View.GONE);
        }

        // Let's get TableView
        mTableView = layout.findViewById(R.id.tableview);

        initializeTableView();

        if (mPaginationEnabled) {
            mTableFilter = new Filter(mTableView); // Create an instance of a Filter and pass the
            // created TableView.

            // Create an instance for the TableView pagination and pass the created TableView.
            mPagination = new Pagination(mTableView);

            // Sets the pagination listener of the TableView pagination to handle
            // pagination actions. See onTableViewPageTurnedListener variable declaration below.
            mPagination.setOnTableViewPageTurnedListener(onTableViewPageTurnedListener);
        }


        return layout;
        // return view;
    }

    private void initializeTableView() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        mGson = new Gson();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Controller.GET_VITAL_SIGN_URL, new com.android.volley.Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jobj = new JSONObject(response);
                            JSONArray jsonArray = jobj.getJSONArray("data");
                            mListTest = mGson.fromJson(jsonArray.toString(), new TypeToken<ArrayList<test>>() {
                            }.getType());
                            mListMyRow = new ArrayList<>();
                            for (int i = 0; i < mListTest.size(); i++) {
                                ArrayList<MyColumn> mListMyColumn = new ArrayList<>();
                                List<test.RespBean> mListResp = mListTest.get(i).getResp();
                                for (int j = 0; j < mListResp.size(); j++) {
                                    mListMyColumn.add(new MyColumn(j + "", mListResp.get(j).getCREATED_ON(), mListResp.get(j).getVSVALUE()));
                                }
                                mListMyRow.add(new MyRow(i + "", mListTest.get(i).getName(), mListMyColumn));
                            }

                            // Create TableView View model class  to group view models of TableView
                            TableViewModel tableViewModel = new TableViewModel(mListMyRow);


                            // Create TableView Adapter
                            TableViewAdapter tableViewAdapter = new TableViewAdapter(tableViewModel);

                            mTableView.setAdapter(tableViewAdapter);
                            mTableView.setTableViewListener(new TableViewListener(mTableView));

                            // Load the dummy data to the TableView
                            tableViewAdapter.setAllItems(tableViewModel.getColumnHeaderList(), tableViewModel
                                    .getRowHeaderList(), tableViewModel.getCellList());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
        }, new com.android.volley.Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Controller.view_error(error, getContext());

                    }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("ADM_CD", patadmcd);
                params.put("HOS_NO", ((ActivityPatient) getActivity()).getmCardviewDataModel().getHOS_NO() + "");
                Log.e("params_test", "params" + params);
                return params;
            }
        };

        queue.add(stringRequest);

    }

    public void filterTable(@NonNull String filter) {
        // Sets a filter to the table, this will filter ALL the columns.
        mTableFilter.set(filter);
    }

    public void filterTableForMood(@NonNull String filter) {
        // Sets a filter to the table, this will only filter a specific column.
        // In the example data, this will filter the mood column.
        mTableFilter.set(TableViewModel.MOOD_COLUMN_INDEX, filter);
    }

    public void filterTableForGender(@NonNull String filter) {
        // Sets a filter to the table, this will only filter a specific column.
        // In the example data, this will filter the gender column.
        mTableFilter.set(TableViewModel.GENDER_COLUMN_INDEX, filter);
    }

    // The following four methods below: nextTablePage(), previousTablePage(),
    // goToTablePage(int page) and setTableItemsPerPage(int itemsPerPage)
    // are for controlling the TableView pagination.
    public void nextTablePage() {
        mPagination.nextPage();
    }

    public void previousTablePage() {
        mPagination.previousPage();
    }

    public void goToTablePage(int page) {
        mPagination.goToPage(page);
    }

    public void setTableItemsPerPage(int itemsPerPage) {
        mPagination.setItemsPerPage(itemsPerPage);
    }

    public void ShowAddVitalSigns() {
        final Bundle args = new Bundle();
        args.putString("patname", patname);
        args.putString("patid", patid);
        args.putString("indate", admdate);
        args.putString("patadm", patadmcd);
        AddVitalSignsFragment addVitalSignsFragment = new AddVitalSignsFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        addVitalSignsFragment.setArguments(args);
        addVitalSignsFragment.show(ft, "add_vital_signs_tag");

    }

    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).setActionBarTitle("العلامات الحيوية");
    }
}