package com.ljt.dataanalyzedemo;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 2017/8/17.
 */

public class PullService {
    public static List<Person> getPersons(InputStream inStream) throws Exception{
        Person person = null;
        List<Person> persons=null;
        XmlPullParser pullParser = Xml.newPullParser();
        pullParser.setInput(inStream,"UTF-8");
        int event = pullParser.getEventType();
        while(event!=XmlPullParser.END_DOCUMENT){
            switch (event) {
                case XmlPullParser.START_DOCUMENT:
                    persons=new ArrayList<Person>();
                    break;
                case  XmlPullParser.START_TAG:
                    if("person".equals(pullParser.getName())){
                        int id = new Integer(pullParser.getAttributeValue(0));
                        person = new Person();
                        person.setId(id);
                    }
                    if(person!=null){
                        if("name".equals(pullParser.getName())){
                            person.setName(pullParser.nextText());
                        }
                        if("age".equals(pullParser.getName())){
                            person.setAge(new Short(pullParser.nextText()));
                        }
                    }
                    break;
                case  XmlPullParser.END_TAG:
                    if("person".equals(pullParser.getName())){
                        persons.add(person);
                        person=null;
                    }
                    break;
            }
            event=pullParser.next();
        }
        return persons;
    }
}