package com.seokjin.getUrlInfomation;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.select.Elements;

public class JsoupGetUrlImpl implements JsoupGetUrl {

    @Override
    public Elements getUrlNodes(String url, String selector) {
        Elements elements = new Elements();
        try {
            
            Document document = Jsoup.connect(url).timeout(5000).get();
            if( document != null ){
                elements = document.select(selector);
                if( elements.isEmpty() ){
                   Element elementNull = new Element("empty");
                   elements.add(elementNull);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return elements;
    }
   
    @Override
    public Elements getPostUrlNodes(String url, HashMap<String, String> dataMaps, String selector) {
        Elements elements = new Elements();
        try {
            
            Document document = Jsoup.connect(url).timeout(5000).data(dataMaps).post();
            if( document != null ){
                elements = document.select(selector);
                if( elements.isEmpty() ){
                   Element elementNull = new Element("empty");
                   elements.add(elementNull);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return elements;
    }

    @Override
    public Elements getPostUrlForm(String url, String formData, String selector) {
        Elements elements = new Elements();
        Document doc = Jsoup.parse(formData);
        FormElement form = (FormElement) doc.select("form").first();
        List<Connection.KeyVal> data = form.formData();
        try {
            
            Document document = Jsoup.connect(url).timeout(5000).data(data).post();
            if( document != null ){
                elements = document.select(selector);
                if( elements.isEmpty() ){
                   Element elementNull = new Element("empty");
                   elements.add(elementNull);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return elements;
    }
}
