package com.example.andrew.cardapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andrew.cardapplication.AsyncTasks.SendAsyncTask;
import com.example.andrew.cardapplication.AsyncTasks.ValidateAsyncTask;
import com.example.andrew.cardapplication.Models.ItemObject;
import com.example.andrew.cardapplication.Models.JsonValidate;
import com.example.andrew.cardapplication.R;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Andrew on 09.10.2017.
 */

public class CustomAdapter extends BaseAdapter {
    private LayoutInflater layoutinflater;
    private List<ItemObject> listStorage;
    private Context context;
    SendAsyncTask myAsyncTask;

    public CustomAdapter(Context context, List<ItemObject> customizedListView) {
        this.context = context;
        layoutinflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listStorage = customizedListView;
    }

    @Override
    public int getCount() {
        return listStorage.size();
    }
    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder listViewHolder;
        if(convertView == null) {
            listViewHolder = new ViewHolder();
            convertView = layoutinflater.inflate(R.layout.card_list, parent, false);
            listViewHolder.title = (TextView) convertView.findViewById(R.id.first_string);
            listViewHolder.one = (TextView) convertView.findViewById(R.id.second_string);
            listViewHolder.two = (TextView) convertView.findViewById(R.id.third_string);
            listViewHolder.three = (TextView) convertView.findViewById(R.id.fourth_string);

            listViewHolder.editText = (EditText) convertView.findViewById(R.id.editText);
            listViewHolder.btn = (Button) convertView.findViewById(R.id.button);
            convertView.setTag(listViewHolder);
        }else{
            listViewHolder = (ViewHolder)convertView.getTag();
        }
        String titleString = "";
        switch (position){
            case 0:
                titleString = "IP";
                listViewHolder.one.setVisibility(View.VISIBLE);
                listViewHolder.two.setVisibility(View.GONE);
                listViewHolder.three.setVisibility(View.GONE);
                listViewHolder.editText.setVisibility(View.GONE);
                listViewHolder.btn.setVisibility(View.GONE);
                break;
            case 1:
                titleString = "DateTime";
                listViewHolder.one.setVisibility(View.VISIBLE);
                listViewHolder.two.setVisibility(View.VISIBLE);
                listViewHolder.three.setVisibility(View.VISIBLE);
                listViewHolder.editText.setVisibility(View.GONE);
                listViewHolder.btn.setVisibility(View.GONE);
                break;
            case 2:
                titleString = "Headers";
                listViewHolder.one.setVisibility(View.VISIBLE);
                listViewHolder.two.setVisibility(View.VISIBLE);
                listViewHolder.three.setVisibility(View.VISIBLE);
                listViewHolder.editText.setVisibility(View.GONE);
                listViewHolder.btn.setVisibility(View.GONE);
                break;
            case 3:
                titleString = "Echo Json";
                listViewHolder.one.setVisibility(View.GONE);
                listViewHolder.two.setVisibility(View.GONE);
                listViewHolder.three.setVisibility(View.GONE);
                listViewHolder.editText.setVisibility(View.VISIBLE);
                listViewHolder.editText.setText("key/value/one/two");
                listViewHolder.btn.setVisibility(View.VISIBLE);
                listViewHolder.btn.setText("return JSON");
                break;
            case 4:
                titleString = "Validate";
                listViewHolder.one.setVisibility(View.GONE);
                listViewHolder.two.setVisibility(View.GONE);
                listViewHolder.three.setVisibility(View.GONE);
                listViewHolder.editText.setVisibility(View.VISIBLE);
                listViewHolder.editText.setText("{\"key\":\"value\"}");
                listViewHolder.btn.setVisibility(View.VISIBLE);
                listViewHolder.btn.setText("Validate");
                break;
        }
        listViewHolder.title.setText(titleString);
        listViewHolder.one.setText(listStorage.get(position).getOne());
        listViewHolder.two.setText(listStorage.get(position).getTwo());
        listViewHolder.three.setText(listStorage.get(position).getThree());
        listViewHolder.btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (position == 3)
                    LoadAsyncTask(listViewHolder.editText.getText().toString());
                else if (position == 4)
                    try {
                        JsonValidate jsonValidate =
                                LoadValidateAsyncTask(listViewHolder.editText.getText().toString());
                        setInfoParse(listViewHolder, jsonValidate);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        });
        return convertView;
    }

    private void setInfoParse(ViewHolder vh, JsonValidate jv){
        vh.one.setVisibility(View.VISIBLE);
        vh.two.setVisibility(View.VISIBLE);
        vh.three.setVisibility(View.VISIBLE);
        if (jv.getValidate()){
            Toast.makeText(context, "VALIDATE TRUE", Toast.LENGTH_SHORT).show();
            vh.one.setText(jv.getObjOrArray());
            if (!jv.getEmpty())
                vh.two.setText("not empty & size=" + jv.getSize());
            else
                vh.two.setText("empty");
            vh.three.setText("parse time(nanoseconds) = " + jv.getParseTime());
        }
        else {
            Toast.makeText(context, "VALIDATE FALSE", Toast.LENGTH_SHORT).show();
            vh.one.setText(jv.getObjOrArray());
            vh.two.setText(jv.getError());
            vh.three.setText(jv.getErrorInfo());
        }
    }

    private void LoadAsyncTask(String str) {
        new SendAsyncTask(context).execute(str);
    }

    private JsonValidate LoadValidateAsyncTask(String str) throws ExecutionException, InterruptedException {
        return new ValidateAsyncTask(context).execute(str).get();
    }

    static class ViewHolder{
        TextView title;
        TextView one;
        TextView two;
        TextView three;
        EditText editText;
        Button btn;
    }
}