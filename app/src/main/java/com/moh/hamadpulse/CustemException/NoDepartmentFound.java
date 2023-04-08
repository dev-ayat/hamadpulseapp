package com.moh.hamadpulse.CustemException;

import com.android.volley.VolleyError;

public class NoDepartmentFound extends VolleyError {
    public NoDepartmentFound(String exceptionMessage) {
        super(exceptionMessage);
    }
}
