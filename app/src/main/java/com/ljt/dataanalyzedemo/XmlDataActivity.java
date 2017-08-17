package com.ljt.dataanalyzedemo;

import android.app.ListActivity;
import android.content.res.XmlResourceParser;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlDataActivity extends ListActivity {
    private SimpleAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_xml_data);

        List<Map<String,String>> data=parseXml(R.xml.dataxml);
        adapter = new SimpleAdapter(this,
                data,
                android.R.layout.simple_list_item_2,
                new String[]{"title","content"},
                new int[]{android.R.id.text1,android.R.id.text2});

        setListAdapter(adapter);
    }

    private List<Map<String, String>> parseXml(int msg){
        XmlResourceParser xpp = getResources().getXml(msg);
        List<Map<String,String>> list=null;
        Map<String,String> map=null;
        try {
            int event = xpp.getEventType();
            do {
                if(event==xpp.START_DOCUMENT){
                    list=new ArrayList<Map<String,String>>();
                }else if(event==xpp.START_TAG){
                    map=processStartTag(xpp,map);
                }else if(event==xpp.END_TAG&&"msg".equals(xpp.getName())){
                    map=processEndTag(list,map);
                }
                event=xpp.next();
            } while (event!=xpp.END_DOCUMENT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private Map<String, String> processStartTag(XmlResourceParser xpp,
                                                Map<String, String> map) throws Exception, Exception {
        if("msg".equals(xpp.getName())){
            map=new HashMap<String,String>();
        }else if("title".equals(xpp.getName())){
            map.put(xpp.getName(),xpp.nextText());

        }else if("content".equals(xpp.getName())){
            map.put(xpp.getName(),xpp.nextText());
        }
        return map;
    }

    private Map<String, String> processEndTag(List<Map<String, String>> list,

                                              Map<String, String> map) {
        Log.d("TAG","list= "+list.size());
        list.add(map);
        map=null;
        return map;
    }
}