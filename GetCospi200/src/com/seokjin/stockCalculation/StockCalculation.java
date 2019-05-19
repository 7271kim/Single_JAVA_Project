package com.seokjin.stockCalculation;

public interface StockCalculation {
    void getValueOnedeviationModi(); // 엑셀 파일을 읽어서 해당 code 바로 옆 셀부터 현재 가격 5일선 -1.6 5일선 +1.6 10일선 -1.6 10일선 +1.6 20일선 -1.6 20일선 +1.6 이순으로 작성 ( 기존 것 삭제됨 )
    void getValueOnedeviationAdd(); // 엑셀 파일을 읽어서 해당 code 맨 마지막 셀부터 현재 가격 5일선 -1.6 5일선 +1.6 10일선 -1.6 10일선 +1.6 20일선 -1.6 20일선 +1.6 이순으로 작성 ( 이어 붙임 )
    void getTop30(); // 해당 엑셀파일 2번째줄 부터 가장 뜨거운 top 30종목을  종목    종목코드    검색비율    등락률 순으로 작성
    void getAnalSite(); // 엑셀 파일을 읽어서 해당 code 맨 마지막 셀에 종목 공시 사이트 붙임
    void getResultTrace(); // 엑셀 파일을 읽어서 해당 code 옆에 현재가격   등락을 붙임 ( 수정됨 )
    void getAllStockKospi(); // 특정 엑셀 파일에 전 코스피 종목 중 영업이익이 + 인 종목 중 가지고 오기 
    void getHiprice();// 엑셀 파일을 읽어서 해당 code 옆에 고가를 이어 붙임 
    void setInOutFile( String inOutFile);
}
