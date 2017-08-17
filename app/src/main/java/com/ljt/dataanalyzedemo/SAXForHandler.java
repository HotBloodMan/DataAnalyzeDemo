package com.ljt.dataanalyzedemo;


import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 2017/8/17.
 */

public class SAXForHandler extends DefaultHandler {

    private static final String TAG="SAXForHandler";
    private List<Person> persons;
    private String  perTag;
    Person person;

    public List<Person> getPersons(){
        return persons;
    }
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        persons=new ArrayList<Person>();
        Log.d(TAG,"****startDocument()*****");
    }
    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if("person".equals(localName)){
            for(int i=0;i<attributes.getLength();i++){
                Log.i(TAG,""+attributes.getLocalName(i)
                        +"attributes"+attributes.getValue(i)
                );
                person=new Person();
                person.setId(Integer.valueOf(attributes.getValue(i)));
            }
        }
        perTag=localName;
        Log.i(TAG, qName+"*****startElement----");
    }
    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        super.characters(ch, start, length);
        String data=new String(ch,start,length).trim();
        if(!"".equals(data.trim())){
            Log.i(TAG,"content:"+data.trim());
        }
        if("name".equals(perTag)){
            person.setName(data);
        }else if("age".equals(perTag)){
            person.setAge(new Short(data));
        }
    }


    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        super.endElement(uri, localName, qName);
        Log.i(TAG,qName+"***endElement***");
        if("person".equals(localName)){
            persons.add(person);
            person=null;
        }
        perTag=null;
    }
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        Log.i(TAG,"***endDocument()****");
    }
}
