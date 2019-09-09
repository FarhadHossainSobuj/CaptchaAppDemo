package com.farhad.tablerowexample;


import android.content.ClipData;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, View.OnDragListener {
    ImageView img1, img2, img3, img4, img5, img6, img7, img8, img9, img10, img11, img12, img13, img14, img15, img16;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);
        img5 = findViewById(R.id.img5);
        img6 = findViewById(R.id.img6);
        img7 = findViewById(R.id.img7);
        img8 = findViewById(R.id.img8);
        img9 = findViewById(R.id.img9);
        img10 = findViewById(R.id.img10);
        img11 = findViewById(R.id.img11);
        img12 = findViewById(R.id.img12);
        img13 = findViewById(R.id.img13);
        img14 = findViewById(R.id.img14);
        img15 = findViewById(R.id.img15);
        img16 = findViewById(R.id.img16);


        img1.setOnTouchListener(this);
        img2.setOnTouchListener(this);
        img3.setOnTouchListener(this);
        img4.setOnTouchListener(this);
        img5.setOnTouchListener(this);
        img6.setOnTouchListener(this);
        img7.setOnTouchListener(this);
        img8.setOnTouchListener(this);
        img9.setOnTouchListener(this);
        img10.setOnTouchListener(this);
        img11.setOnTouchListener(this);
        img12.setOnTouchListener(this);
        img13.setOnTouchListener(this);
        img14.setOnTouchListener(this);
        img15.setOnTouchListener(this);
        img16.setOnTouchListener(this);

//        findViewById(R.id.img1).setOnDragListener(this);
//        findViewById(R.id.img2).setOnDragListener(this);
//        findViewById(R.id.img3).setOnDragListener(this);
//        findViewById(R.id.img4).setOnDragListener(this);
         findViewById(R.id.container1).setOnDragListener(this);
        findViewById(R.id.container2).setOnDragListener(this);
        findViewById(R.id.container3).setOnDragListener(this);
        findViewById(R.id.container4).setOnDragListener(this);
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean onTouch(View view, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);

            ClipData data = ClipData.newPlainText("id", view.getResources().getResourceEntryName(view.getId()));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                view.startDragAndDrop(data, shadowBuilder, view, 0);
            } else {
                view.startDrag(data, shadowBuilder, view, 0);
            }

            view.setVisibility(View.INVISIBLE);
            return true;
        }
        return false;
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        switch (event.getAction()) {
            // signal for the start of a drag and drop operation
            case DragEvent.ACTION_DRAG_STARTED:
                // do nothing
                break;

            // the drag point has entered the bounding box of the View
            case DragEvent.ACTION_DRAG_ENTERED:
                v.setBackgroundColor(0xFFFFF6F9);
                break;

            // the user has moved the drag shadow outside the bounding box of the View
            case DragEvent.ACTION_DRAG_EXITED:
                v.setBackgroundColor(v.getId() == R.id.container1 ? 0xFFE8E6E7 : 0xFFB1BEC4);
                break;

            // the drag and drop operation has concluded
            case DragEvent.ACTION_DRAG_ENDED:
                v.setBackgroundColor(v.getId() == R.id.container1 ? 0xFFE8E6E7 : 0xFFB1BEC4);
                break;

            //drag shadow has been released,the drag point is within the bounding box of the View
            case DragEvent.ACTION_DROP:
                View view = (View) event.getLocalState();
                // we want to make sure it is dropped only to left and right parent view
                if (v.getId() == R.id.container1 || v.getId() == R.id.container2 || v.getId() == R.id.container3 || v.getId() == R.id.container4) {

                    ViewGroup source = (ViewGroup) view.getParent();
                    source.removeView(view);

                    LinearLayout target = (LinearLayout) v;
                    target.addView(view);

                    String id = event.getClipData().getItemAt(0).getText().toString();
                    Toast.makeText(this, id + " dropped!", Toast.LENGTH_SHORT).show();
                }
                // make view visible as we set visibility to invisible while starting drag
                view.setVisibility(View.VISIBLE);
                break;
        }

        return true;
    }
}

