/*
 * Copyright (c) 2018. Evren Coşkun
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.moh.hamadpulse.tableview;

import android.util.Log;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

import com.moh.hamadpulse.R;
import com.moh.hamadpulse.tableview.model.Cell;
import com.moh.hamadpulse.tableview.model.ColumnHeader;
import com.moh.hamadpulse.tableview.model.MyRow;
import com.moh.hamadpulse.tableview.model.RowHeader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by evrencoskun on 4.02.2018.
 */

public class TableViewModel {

    // Columns indexes
    public static final int MOOD_COLUMN_INDEX = 10;
    public static final int GENDER_COLUMN_INDEX = 100;

    // Constant values for icons
    public static final int SAD = 1;
    public static final int HAPPY = 2;
    public static final int BOY = 1;
    public static final int GIRL = 2;

    // Constant size for dummy data sets
    private static final int COLUMN_SIZE = 4;
    private static final int ROW_SIZE = 6;

    // Drawables
    @DrawableRes
    private final int mBoyDrawable;
    @DrawableRes
    private final int mGirlDrawable;
    @DrawableRes
    private final int mHappyDrawable;
    @DrawableRes
    private final int mSadDrawable;

    List<ColumnHeader> listColumnHeader;
    List<RowHeader> listRowHeader;
    List<List<Cell>> listCell;
    ArrayList<MyRow> mListMyRows;
    int sizeColumnList;

    public TableViewModel(ArrayList<MyRow> mListMyRows) {
        // initialize drawables
        mBoyDrawable = R.drawable.ic_male;
        mGirlDrawable = R.drawable.ic_female;
        mHappyDrawable = R.drawable.ic_happy;
        mSadDrawable = R.drawable.ic_sad;
        this.mListMyRows = mListMyRows;
        getSimpleRowHeaderList();
        getRandomColumnHeaderList();
        getCellListForSortingTest();
    }

    @NonNull
    private List<RowHeader> getSimpleRowHeaderList() {
        Log.e("ERROR","RowHeader");
        listRowHeader = new ArrayList<>();
        for (int i = 0; i < mListMyRows.size(); i++) {
            RowHeader header = new RowHeader(String.valueOf(i), mListMyRows.get(i).getName() + i);
            listRowHeader.add(header);
        }

        return listRowHeader;
    }

    /**
     * This is a dummy model list test some cases.
     */
    @NonNull
    private List<ColumnHeader> getRandomColumnHeaderList() {
        listColumnHeader = new ArrayList<>();

        if (mListMyRows.size() > 0) {
            for (int i = 0; i < mListMyRows.get(0).getListMyCloumn().size(); i++) {
                String title = mListMyRows.get(0).getListMyCloumn().get(i).getMyDate();
                ColumnHeader header = new ColumnHeader(String.valueOf(i), title);
                listColumnHeader.add(header);
            }
        }
        return listColumnHeader;
    }

    /**
     * This is a dummy model list test some cases.
     */
    @NonNull
    private List<List<Cell>> getCellListForSortingTest() {
        listCell = new ArrayList<>();
        for (int i = 0; i < mListMyRows.size(); i++) {
            List<Cell> cellList = new ArrayList<>();
            for (int j = 0; j < mListMyRows.get(i).getListMyCloumn().size(); j++) {

                Object text = mListMyRows.get(i).getListMyCloumn().get(j).getVal();
                String id = j + "-" + i;
                Cell cell;
                cell = new Cell(id, text);
                cellList.add(cell);
            }
            listCell.add(cellList);
        }
        return listCell;
    }

    public void addNewRec() {
        for (int i = 0; i < mListMyRows.size(); i++) {
            sizeColumnList = mListMyRows.get(i).getListMyCloumn().size() - 1;
            Object text = "i"+mListMyRows.get(i).getListMyCloumn().get(sizeColumnList).getVal();
            String id = sizeColumnList + "-" + i;
            Cell cell = new Cell(id, text);
            getCellList().get(i).add(cell);
        }
    }

    @DrawableRes
    public int getDrawable(int value, boolean isGender) {
        if (isGender) {
            return value == BOY ? mBoyDrawable : mGirlDrawable;
        } else {
            return value == SAD ? mSadDrawable : mHappyDrawable;
        }
    }

    @NonNull
    public List<List<Cell>> getCellList() {
        return listCell;
    }

    @NonNull
    public List<RowHeader> getRowHeaderList() {
        return listRowHeader;
        //return getSimpleRowHeaderList();
    }

    @NonNull
    public List<ColumnHeader> getColumnHeaderList() {
        return listColumnHeader;
    }
}
