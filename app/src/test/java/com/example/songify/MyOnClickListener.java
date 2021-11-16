package com.example.songify;

import android.view.View;
import android.widget.Toast;

public class MyOnClickListener implements View.OnClickListener {
    public void onClick(View v) {
        Toast.makeText(v.getContext(), "HASIDOPULSADO", Toast.LENGTH_SHORT).show();
    }
}
