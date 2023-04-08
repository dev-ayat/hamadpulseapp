package com.moh.hamadpulse.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.celerysoft.tablefixheaders.adapter.BaseTableAdapter;
import com.moh.hamadpulse.R;
import com.moh.hamadpulse.bean.AttributeEnumerable;
import com.moh.hamadpulse.models.GetVitalForAdm;

import java.util.List;

public class MatrixTableAdapter2<T extends AttributeEnumerable> extends BaseTableAdapter {

    private final static int WIDTH_DIP = 110;
    private final static int HEIGHT_DIP = 48;

    private final Context context;
    private final int width;
    private final int height;
    private List<T> list;

    public MatrixTableAdapter2(Context context) {
        this.context = context;

        Resources r = context.getResources();

        width = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, WIDTH_DIP, r.getDisplayMetrics()));
        height = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, HEIGHT_DIP, r.getDisplayMetrics()));
    }

    public void setData(List<T> list) {
        this.list = list;
    }

    public T getItem(int row) {
        if (row <= getRowCount()) {
            return this.list.get(row);
        } else {
            return null;
        }
    }

    @Override
    public int getRowCount() {
        int rowCount = list.size() - 1;
        return rowCount >= 0 ? rowCount : 0;
    }

    @Override
    public int getColumnCount() {
        if (getRowCount() > 0) {
            return list.get(0).getAttributeCountable() - 1;
        } else {
            return 0;
        }
    }

    @Override
    public View getView(int row, int column, View convertView, ViewGroup parent) {
        Log.e("ERROR", (row + 1) + " " + column + 1);
        if (convertView == null) {
            convertView = new TextView(context);
            ((TextView) convertView).setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
        }

        try {
            Object mObject = list.get(row + 1).getAttribute(column + 1);
            ((TextView) convertView).setTextColor(Color.BLACK);
            if(mObject instanceof GetVitalForAdm.Iyad)
            {

                ((TextView) convertView).setTextColor(((GetVitalForAdm.Iyad) mObject).getColor());
                ((TextView) convertView).setText(((GetVitalForAdm.Iyad) mObject).getVal());
            }
            else
            {
                ((TextView) convertView).setText(list.get(row + 1).getAttribute(column + 1).toString());
            }

        }
        catch (Exception e)
        {
            ((TextView) convertView).setText("");
        }
        return convertView;
    }

    @Override
    public int getHeight(int row) {
        return height;
    }

    @Override
    public int getWidth(int column) {
        return width;
    }

    @Override
    public int getItemViewType(int row, int column) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getBackgroundResId(int row, int column) {
        return 0;
    }

    @Override
    public int getBackgroundHighlightResId(int row, int column) {
        return R.drawable.item_highlight_rect;
    }

    @Override
    public boolean isRowSelectable(int row) {
        return true;
    }

    @Override
    public long getItemId(int row, int column) {
        return 0;
    }

    @Override
    public Object getItem(int row, int column) {
        if (row <= getRowCount()) {
            T t = this.list.get(row);
            if (t != null && column <= getColumnCount()) {
                return t.getAttribute(column);
            }
        }

        return null;
    }
}
