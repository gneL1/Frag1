package com.example.frag1;



import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SoundFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
      //  return inflater.inflate(R.layout.activity_sound_fragment,null);
        //先将fragment声明layout文件，然后转换为一个view对象

        View view = inflater.inflate(R.layout.activity_sound_fragment,null);

        Button btn = (Button)view.findViewById(R.id.Btn_1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText)getActivity().findViewById(R.id.Et_1);
                String text = editText.getText().toString();
                Toast.makeText(getActivity(),text,Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}
