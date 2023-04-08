package com.moh.hamadpulse.bean;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.moh.hamadpulse.R;
import com.moh.hamadpulse.fragment.TreatmentPlanFragment;
import com.moh.hamadpulse.fragment.VanteliationFragment;

public class MainActivity2 extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        B b[] = new B[]{
                new B("simple_adapter", VanteliationFragment.class),
                new B("Treatment_adapter", TreatmentPlanFragment.class)
//				new B("style_adapter", StyleTable.class),
//				new B("family_adapter", FamilyTable.class),
        };
        setListAdapter(new ArrayAdapter<B>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, b));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        startActivity(new Intent(this,
                ((B) l.getItemAtPosition(position)).class1));
    }

    private class B {
        private final String string;
        private final Class<? extends Fragment> class1;

        B(String string, Class<? extends Fragment> class1) {
            this.string = string;
            this.class1 = class1;
        }

        @Override
        public String toString() {
            return string;
        }
    }
}
