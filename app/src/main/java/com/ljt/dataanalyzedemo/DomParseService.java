package com.ljt.dataanalyzedemo;

import android.icu.util.RangeValueIterator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by 1 on 2017/8/17.
 */

public class DomParseService {
    public static List<Person> getPersons(InputStream inStream) throws Exception{
        ArrayList<Person> persons = new ArrayList<Person>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(inStream);
        Element root = document.getDocumentElement();
        NodeList personNodes = root.getElementsByTagName("person");
        for(int i=0;i<personNodes.getLength();i++){
            Element personElement = (Element) personNodes.item(i);
            int id = new Integer(personElement.getAttribute("id"));
            Person person = new Person();
            person.setId(id);
            NodeList childNodes = personElement.getChildNodes();
            for(int y=0;y<childNodes.getLength();y++){
                if(childNodes.item(y).getNodeType()== Node.ELEMENT_NODE){
                    if("name".equals(childNodes.item(y).getNodeName())){
                        String name = childNodes.item(y).getFirstChild().getNodeValue();
                        person.setName(name);
                    }else if("age".equals(childNodes.item(y).getNodeName())){
                        String age = childNodes.item(y).getFirstChild().getNodeValue();
                        person.setAge(new Short(age));
                    }
                }
            }
            persons.add(person);
        }
        inStream.close();
        return persons;
    }
}