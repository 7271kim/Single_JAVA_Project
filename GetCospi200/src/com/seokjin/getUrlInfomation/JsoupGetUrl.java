package com.seokjin.getUrlInfomation;

import java.util.HashMap;

import org.jsoup.select.Elements;

public interface JsoupGetUrl {
    Elements getUrlNodes( String url , String selector); // url의 selector를 Elements 형식으로 가지고 온다.
    Elements getPostUrlNodes( String url, HashMap<String, String> dataMaps , String selector); // post 형 url의 값들을  dataMaps집어넣고  selector를 입력해 Elements 형식으로 가지고 온다.
    Elements getPostUrlForm( String url, String formData , String selector); // post 형 form을 강제로 집어 넣고 selector를 가지고 온다.
}
