package com.ljt.dataanalyzedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity {

    private static InputStream is;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
//            sax_xml();
            testDom();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Person> sax_xml() throws Exception{
        is = getResources().openRawResource(R.raw.datasax);
        Log.d("TAG","is==="+is);
        SAXForHandler handler = new SAXForHandler();
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser parser = spf.newSAXParser();
        parser.parse(is,handler);
        List<Person> list = handler.getPersons();
        Log.d("TAG","list===="+list.toString());
        is.close();
        return list;
    }


    private static final String TAG="MainActivity";
    public void testDom() throws Exception{
        InputStream is = getResources().openRawResource(R.raw.datasax);
        List<Person> list = DomParseService.getPersons(is);
        for(Person person:list){
            String result = person.toString();
            Log.d(TAG,"result==__"+result);
        }
    }
}