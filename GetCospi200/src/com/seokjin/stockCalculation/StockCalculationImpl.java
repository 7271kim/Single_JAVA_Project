package com.seokjin.stockCalculation;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.seokjin.calculation.CalculationImpl;
import com.seokjin.getCharcter.GetCarcterSet;
import com.seokjin.getCharcter.GetCarcterSetImpl;
import com.seokjin.getUrlInfomation.JsoupGetUrlImpl;
import com.seokjin.readWriteExcel.ExcelWriteReadImpl;

public class StockCalculationImpl implements StockCalculation {

    private String inOutFile;
    
    public StockCalculationImpl(String inOutFile) {
        this.inOutFile = inOutFile;
    }
    
    @Override
    public void getValueOnedeviationModi() {
        String readFileString = inOutFile;
        String writreFileString= inOutFile;
        JsoupGetUrlImpl getUrlImpl = new JsoupGetUrlImpl();
        CalculationImpl cal = new CalculationImpl();
        ExcelWriteReadImpl excel = new ExcelWriteReadImpl(readFileString, writreFileString );
        XSSFWorkbook workbook = excel.readExcel();
        XSSFRow row;
        XSSFCell cell;
        String code;
        String url;
        String deviation;
        System.out.println("시작");
        try {
            XSSFSheet sheet =  workbook.getSheetAt(0);
            int rowNum = sheet.getPhysicalNumberOfRows();
            for( int index = 1; index < rowNum; index++ ){
                System.out.println("index : "+index);
                row = sheet.getRow(index);
                cell = row.getCell(1);
                code = cell.getStringCellValue();
                url = "http://finance.naver.com/item/sise_day.nhn?code="+code+"&page=";
                ArrayList<Double> deviations = new ArrayList<>();
                int count = 1;
                Thread.sleep(100);
                for( int pageIndex = 1; pageIndex <= 2; pageIndex++ ){
                    String realurl = url + pageIndex;
                    System.out.println("이름 : " + row.getCell(0));
                    for( int eqIndex = 0; eqIndex <=17; eqIndex++ ){
                        Elements elements = getUrlImpl.getUrlNodes(realurl, "tr:eq("+eqIndex+") td:eq(1) .tah.p11");
                        for(Element el : elements){
                            String elText = el.text().replaceAll(",", "");
                            if(!elText.equals("")){
                                double elTextValue= Double.valueOf(elText);
                                deviations.add(elTextValue);
                                if( pageIndex == 1 && count == 1 ){
                                    excel.createCell(workbook, 0, index, 2, elText, CellType.NUMERIC);
                                }
                                if(count == 5){
                                    deviation = String.valueOf(cal.mean(deviations) - 1.6 * cal.deviation(deviations));
                                    excel.createCell(workbook, 0, index, 3, deviation, CellType.NUMERIC);
                                    deviation = String.valueOf(cal.mean(deviations) + 1.6 * cal.deviation(deviations));
                                    excel.createCell(workbook, 0, index, 4, deviation, CellType.NUMERIC);
                                }
                                if(count == 10){
                                    deviation = String.valueOf(cal.mean(deviations) - 1.6 * cal.deviation(deviations));
                                    excel.createCell(workbook, 0, index, 5, deviation, CellType.NUMERIC);
                                    deviation = String.valueOf(cal.mean(deviations) + 1.6 * cal.deviation(deviations));
                                    excel.createCell(workbook, 0, index, 6, deviation, CellType.NUMERIC);
                                }
                                if(count == 20){
                                    deviation = String.valueOf(cal.mean(deviations) - 1.6 * cal.deviation(deviations));
                                    excel.createCell(workbook, 0, index, 7, deviation, CellType.NUMERIC);
                                    deviation = String.valueOf(cal.mean(deviations) + 1.6 * cal.deviation(deviations));
                                    excel.createCell(workbook, 0, index, 8, deviation, CellType.NUMERIC);
                                }
                                count++;
                            }
                        }
                    }
                }
            }
            excel.writeExcel(workbook);
            System.out.println("끝");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    @Override
    public void getValueOnedeviationAdd() {
        String readFileString = inOutFile;
        String writreFileString= inOutFile;
        GetCarcterSetImpl charcterSetImpl = new GetCarcterSetImpl();
        JsoupGetUrlImpl getUrlImpl = new JsoupGetUrlImpl();
        CalculationImpl cal = new CalculationImpl();
        ExcelWriteReadImpl excel = new ExcelWriteReadImpl(readFileString, writreFileString );
        XSSFWorkbook workbook = excel.readExcel();
        XSSFRow row;
        XSSFCell cell;
        String code;
        String url;
        String deviation;
        System.out.println("시작");
        try {
            XSSFSheet sheet =  workbook.getSheetAt(0);
            int rowNum = sheet.getPhysicalNumberOfRows();
            for( int index = 0; index < rowNum; index++ ){
                System.out.println("index : "+index);
                row = sheet.getRow(index);
                cell = row.getCell(1);
                code = cell.getStringCellValue();
                url = "http://finance.naver.com/item/sise_day.nhn?code="+code+"&page=";
                ArrayList<Double> deviations = new ArrayList<>();
                int count = 1;
                Thread.sleep(100);
                for( int pageIndex = 1; pageIndex <= 2; pageIndex++ ){
                    String realurl = url + pageIndex;
                    System.out.println("이름 : " + row.getCell(0));
                    for( int eqIndex = 0; eqIndex <=17; eqIndex++ ){
                        Elements elements = getUrlImpl.getUrlNodes(realurl, "tr:eq("+eqIndex+") td:eq(1) .tah.p11");
                        for(Element el : elements){
                            String elText = el.text().replaceAll(",", "");
                            if(!elText.equals("")){
                                double elTextValue= Double.valueOf(elText);
                                deviations.add(elTextValue);
                                if( pageIndex == 1 && count == 1 ){
                                    excel.createRowCell(workbook, 0, index, elText, CellType.NUMERIC);
                                }
                                if(count == 5){
                                    deviation = String.valueOf(cal.mean(deviations) - 1.6 * cal.deviation(deviations));
                                    excel.createRowCell(workbook, 0, index, deviation, CellType.NUMERIC);
                                    deviation = String.valueOf(cal.mean(deviations) + 1.6 * cal.deviation(deviations));
                                    excel.createRowCell(workbook, 0, index, deviation, CellType.NUMERIC);
                                }
                                if(count == 10){
                                    deviation = String.valueOf(cal.mean(deviations) - 1.6 * cal.deviation(deviations));
                                    excel.createRowCell(workbook, 0, index, deviation, CellType.NUMERIC);
                                    deviation = String.valueOf(cal.mean(deviations) + 1.6 * cal.deviation(deviations));
                                    excel.createRowCell(workbook, 0, index, deviation, CellType.NUMERIC);
                                }
                                if(count == 20){
                                    deviation = String.valueOf(cal.mean(deviations) - 1.6 * cal.deviation(deviations));
                                    excel.createRowCell(workbook, 0, index, deviation, CellType.NUMERIC);
                                    deviation = String.valueOf(cal.mean(deviations) + 1.6 * cal.deviation(deviations));
                                    excel.createRowCell(workbook, 0, index, deviation, CellType.NUMERIC);
                                }
                                count++;
                            }
                        }
                    }
                }
            }
            excel.writeExcel(workbook);
            System.out.println("끝");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    @Override
    public void getTop30() {
        String readFileString =inOutFile;
        String writreFileString=inOutFile;
        GetCarcterSetImpl charcterSetImpl = new GetCarcterSetImpl();
        ExcelWriteReadImpl excel = new ExcelWriteReadImpl(readFileString, writreFileString );
        XSSFWorkbook workbook = excel.readExcel();
        int count = 1;
        try {
            //간략화된 것 , 접속 1
            String url = "http://finance.naver.com/sise/lastsearch2.nhn";
            JsoupGetUrlImpl jsop = new JsoupGetUrlImpl();
            Elements elements = jsop.getUrlNodes(url, ".box_type_l tbody tr");
            //System.out.println(elements);
            for( Element el : elements){
                String ratio = el.select(".number").eq(0).text();
                String upDown = el.select("td .tah.p11").eq(1).text();
                if(upDown.indexOf("+") > -1){
                    String code = el.select("a").attr("href");
                    String company = el.select("a").text();
                    upDown  = upDown.replaceAll("%", "");
                    code = charcterSetImpl.cuttingBackString(code, "=");
                    if(!code.equals("")){
                        excel.createCell(workbook, 0, count, 0, company, CellType.STRING);
                        excel.createCell(workbook, 0, count, 1, code, CellType.STRING);
                        excel.createCell(workbook, 0, count, 2, ratio, CellType.STRING);
                        excel.createCell(workbook, 0, count, 3, upDown, CellType.NUMERIC);
                        count ++;
                    }
                    System.out.println(code + " " + company);
                }
            }
           
                    
           excel.writeExcel(workbook);
            System.out.println("-----끝------");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    @Override
    public void getAnalSite() {
        String readFileString = inOutFile;
        String writreFileString= inOutFile;
        JsoupGetUrlImpl getUrlImpl = new JsoupGetUrlImpl();
        CalculationImpl cal = new CalculationImpl();
        ExcelWriteReadImpl excel = new ExcelWriteReadImpl(readFileString, writreFileString );
        XSSFWorkbook workbook = excel.readExcel();
        XSSFRow row;
        XSSFCell cell;
        String code;
        String url;
        String deviation;
        System.out.println("시작");
        url = "http://dart.fss.or.kr/dsab001/search.ax";
        XSSFSheet sheet =  workbook.getSheetAt(0);
        int rowNum = sheet.getPhysicalNumberOfRows();
        for( int index = 0; index < rowNum; index++ ){
            System.out.println("index : "+index);
            HashMap<String, String> dataMaps = new HashMap<>();
            row = sheet.getRow(index);
            cell = row.getCell(1);
            code = cell.getStringCellValue();
            dataMaps.put("textCrpNm",  code );
            dataMaps.put("publicType", "A001");
            Elements elements =  getUrlImpl.getPostUrlNodes(url, dataMaps, "tbody a");
            for( Element el : elements){
                String analUrl = el.attr("href");
                if(analUrl.indexOf("rcpNo=") > -1){
                    analUrl = "http://dart.fss.or.kr"+analUrl;
                    excel.createRowCell(workbook, 0, index, analUrl, CellType.STRING);
                    break;
                }
            }
        }
        
        excel.writeExcel(workbook);
        System.out.println("끝");
        
    }
    
    @Override
    public void getResultTrace() {
        String readFileString =inOutFile;
        String writreFileString=inOutFile;
        GetCarcterSetImpl charcterSetImpl = new GetCarcterSetImpl();
        JsoupGetUrlImpl getUrlImpl = new JsoupGetUrlImpl();
        CalculationImpl cal = new CalculationImpl();
        ExcelWriteReadImpl excel = new ExcelWriteReadImpl(readFileString, writreFileString );
        XSSFWorkbook workbook = excel.readExcel();
        XSSFRow row;
        XSSFCell cell;
        String code;
        String url;
        String deviation;
        System.out.println("시작");
        try {
            XSSFSheet sheet =  workbook.getSheetAt(0);
            int rowNum = sheet.getPhysicalNumberOfRows();
            for( int index = 1; index < rowNum; index++ ){
                System.out.println("index : "+index);
                row = sheet.getRow(index);
                cell = row.getCell(1);
                code = cell.getStringCellValue();
                url = "http://finance.naver.com/item/sise_day.nhn?code="+code+"&page=1";
                Thread.sleep(100);
                String realurl = url;
                System.out.println("이름 : " + row.getCell(0));
                Element element = getUrlImpl.getUrlNodes(realurl, "tr:eq(2) td:eq(1) .tah.p11").first();
                String elText = element.text().replaceAll(",", "");
                excel.createCell(workbook, 0, index, 2, elText, CellType.NUMERIC);
                element = getUrlImpl.getUrlNodes(realurl, "tr:eq(2) td:eq(2) .tah.p11").first();
                if(element.toString().indexOf("red") > 0){
                    elText = "+";
                } else {
                    elText = "-";
                }
                elText += element.text().replaceAll(",", "");
                excel.createCell(workbook, 0, index, 3, elText, CellType.NUMERIC);
            }
            excel.writeExcel(workbook);
            System.out.println("끝");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    @Override
    public void getAllStockKospi() {
        String readFileString =inOutFile;
        String writreFileString=inOutFile;
        JsoupGetUrlImpl getUrlImpl = new JsoupGetUrlImpl();
        ExcelWriteReadImpl excel = new ExcelWriteReadImpl(readFileString, writreFileString );
        GetCarcterSet charItem = new GetCarcterSetImpl();
        XSSFWorkbook workbook = excel.readExcel();
        XSSFRow row;
        XSSFCell cell;
        String sbmitUrl = "https://finance.naver.com/sise/field_submit.nhn";
        int page = 1;
        int count = 1;
        System.out.println("시작");
        for( int indexPage=0; indexPage < 30; indexPage++ ){
            String formData = "<form name='field_form'>"+
                    "<input type='hidden' name='menu' value='market_sum'>"+
                    "<input type='hidden' name='returnUrl' value='https://finance.naver.com/sise/sise_market_sum.nhn?&page="+page+"'>"+
                        "<div class='subcnt_sise_item sub810'><div class='subcnt_sise_item_top sub810t'>"+
                            "<table summary='원하시는 항목을 선택하여 결과를 보실 수 있습니다.' cellpadding='0' cellspacing='0' class='item_list'>"+
                            "<caption>항목 선택표</caption>"+
                            "<colgroup><col width='62'><col width='84'><col width='111'><col width='97'><col width='116'><col width='85'></colgroup>"+
                                "<tbody><tr>"+
                                    "<td class=''><input type='checkbox' id='option1' name='fieldIds' value='quant'> <label for='option1'>거래량</label></td>"+                      
                                    "<td><input type='checkbox' id='option2' name='fieldIds' value='ask_buy'> <label for='option2'>매수호가</label></td>"+
                                    "<td><input type='checkbox' id='option3' name='fieldIds' value='amount'> <label for='option3'>거래대금</label>(백만)</td>"+
                                    "<td class=''><input type='checkbox' id='option4' name='fieldIds' value='market_sum'> <label for='option4'>시가총액</label>(억)</td>"+             
                                    "<td><input type='checkbox' id='option5' name='fieldIds' value='operating_profit'> <label for='option5'>영업이익</label>(억)</td>"+                     
                                    "<td class=''><input type='checkbox' id='option6' name='fieldIds' value='per' > <label for='option6'>PER</label>(배)</td>"+                 
                                "</tr>"+
                                "<tr>"+                            
                                    "<td><input type='checkbox' id='option7' name='fieldIds' value='open_val' checked=''> <label for='option7'>시가</label></td>"+                      
                                    "<td><input type='checkbox' id='option8' name='fieldIds' value='ask_sell'> <label for='option8'>매도호가</label></td>"+
                                    "<td><input type='checkbox' id='option9' name='fieldIds' value='prev_quant'> <label for='option9'>전일거래량</label></td>"+
                                    "<td><input type='checkbox' id='option10' name='fieldIds' value='property_total'> <label for='option10'>자산총계</label>(억)</td>"+
                                    "<td><input type='checkbox' id='option11' name='fieldIds' value='operating_profit_increasing_rate'> <label for='option11'>영업이익증가율</label></td>"+
                                    "<td class=''><input type='checkbox' id='option12' name='fieldIds' value='roe'> <label for='option12'>ROE</label>(%)</td>"+
                                "</tr>"+
                                "<tr>"+
                                    "<td><input type='checkbox' id='option13' name='fieldIds' value='high_val' checked=''> <label for='option13'>고가</label></td>"+
                                    "<td><input type='checkbox' id='option14' name='fieldIds' value='buy_total'> <label for='option14'>매수총잔량</label></td>"+
                                    "<td class=''><input type='checkbox' id='option15' name='fieldIds' value='frgn_rate'> <label for='option15'>외국인비율</label></td>"+
                                    "<td><input type='checkbox' id='option16' name='fieldIds' value='debt_total'> <label for='option16'>부채총계</label>(억)</td>"+
                                    "<td><input type='checkbox' id='option17' name='fieldIds' value='net_income' checked=''> <label for='option17'>당기순이익</label>(억)</td>"+
                                    "<td><input type='checkbox' id='option18' name='fieldIds' value='roa'> <label for='option18'>ROA</label>(%)</td>"+
                                "</tr>"+       
                                "<tr>"+
                                    "<td><input type='checkbox' id='option19' name='fieldIds' value='low_val'> <label for='option19'>저가</label></td>"+
                                    "<td><input type='checkbox' id='option20' name='fieldIds' value='sell_total'> <label for='option20'>매도총잔량</label></td>"+   
                                    "<td class=''><input type='checkbox' id='option21' name='fieldIds' value='listed_stock_cnt'> <label for='option21'>상장주식수</label>(천주)</td>"+
                                    "<td><input type='checkbox' id='option22' name='fieldIds' value='sales'> <label for='option22'>매출액</label>(억)</td>"+
                                    "<td><input type='checkbox' id='option23' name='fieldIds' value='eps'> <label for='option23'>주당순이익</label>(원)</td>"+
                                    "<td><input type='checkbox' id='option24' name='fieldIds' value='pbr'> <label for='option24'>PBR</label>(배)</td>"+
                                "</tr>"+               
                                "<tr>"+
                                "<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>"+
                                    "<td><input type='checkbox' id='option25' name='fieldIds' value='sales_increasing_rate'> <label for='option25'>매출액증가율</label></td>"+
                                    "<td><input type='checkbox' id='option26' name='fieldIds' value='dividend'> <label for='option26'>보통주배당금</label>(원)</td>"+
                                    "<td><input type='checkbox' id='option27' name='fieldIds' value='reserve_ratio'> <label for='option27'>유보율</label>(%)</td>"+
                                "</tr>"+
                            "</tbody></table>"+
                            "<div class='item_btn'>"+
                                "<a href='javascript:fieldSubmit()'><img src='https://ssl.pstatic.net/imgstock/images5/btn_apply.gif' alt='적용하기' width='55' height='18'></a><a href='javascript:fieldDefault()'><img src='https://ssl.pstatic.net/imgstock/images5/btn_initialize.gif' alt='초기항 목으로' width='76' height='18'></a>"+
                            "</div>"+
                        "</div></div>"+
                    "</form>";
            page++;
            Elements elements =  getUrlImpl.getPostUrlForm(sbmitUrl, formData, "tbody tr");
            for(Element el : elements){
                Element companyEl ;
                if(!el.select("td a.tltle").isEmpty()){
                    String profit = el.select("td.number").get(6).text();
                    String todayPrice = el.select("td.number").get(0).text();
                    String hiPrice = el.select("td.number").get(5).text();
                    String startPrice = el.select("td.number").get(4).text();
                    
                    todayPrice = todayPrice.replaceAll(",", "");
                    hiPrice = hiPrice.replaceAll(",", "");
                    startPrice = startPrice.replaceAll(",", "");
                    if(profit.indexOf("-") == -1 && profit.indexOf("N") == -1){
                        companyEl = el.select("a.tltle").get(0);
                        String href = companyEl.attr("href");
                        String company = companyEl.text();
                        String code = charItem.cuttingBackString(href, "=");
                        // 여기 중 
                        excel.createCell(workbook, 0, count, 0, company, CellType.STRING);
                        excel.createCell(workbook, 0, count, 1, code, CellType.STRING);
                        excel.createCell(workbook, 0, count, 2, todayPrice, CellType.NUMERIC);
                        excel.createCell(workbook, 0, count, 3, startPrice, CellType.NUMERIC);
                        excel.createCell(workbook, 0, count, 4, hiPrice, CellType.NUMERIC);
                        count++;
                        System.out.println("code : "+ code + " company :  " + company);
                        /*System.out.println("hiPrice : " + hiPrice);
                        System.out.println("profit : " + profit);
                        System.out.println("todayPrice : " + todayPrice);
                        System.out.println("startPrice : " + startPrice);
                        System.out.println("-------------------------------");*/
                    }
                }
            }
        }
        excel.writeExcel(workbook);
        System.out.println("끝");
        
    }

    @Override
    public void getHiprice() {
        String readFileString =inOutFile;
        String writreFileString=inOutFile;
        JsoupGetUrlImpl getUrlImpl = new JsoupGetUrlImpl();
        ExcelWriteReadImpl excel = new ExcelWriteReadImpl(readFileString, writreFileString );
        XSSFWorkbook workbook = excel.readExcel();
        XSSFRow row;
        XSSFCell cell;
        String code;
        String url;
        String deviation;
        System.out.println("시작");
        try {
            XSSFSheet sheet =  workbook.getSheetAt(0);
            int rowNum = sheet.getPhysicalNumberOfRows();
            for( int index = 1; index < rowNum; index++ ){
                System.out.println("index : "+index);
                row = sheet.getRow(index);
                cell = row.getCell(1);
                code = cell.getStringCellValue();
                url = "http://finance.naver.com/item/sise_day.nhn?code="+code+"&page=1";
                String realurl = url;
                System.out.println("이름 : " + row.getCell(0));
                Element element = getUrlImpl.getUrlNodes(realurl, "tr:eq(2) td:eq(1) .tah.p11").first();
                String todayPrice = element.text();
                todayPrice = todayPrice.replaceAll(",", "");
                element = getUrlImpl.getUrlNodes(realurl, "tr:eq(2) td:eq(4) .tah.p11").first();
                String highPrice = element.text();
                highPrice = highPrice.replaceAll(",", "");
                element = getUrlImpl.getUrlNodes(realurl, "tr:eq(2) td:eq(3) .tah.p11").first();
                String startPrice = element.text();
                startPrice = startPrice.replaceAll(",", "");
                
                excel.createRowCell(workbook, 0, index, todayPrice, CellType.NUMERIC);
                excel.createRowCell(workbook, 0, index, startPrice, CellType.NUMERIC);
                excel.createRowCell(workbook, 0, index, highPrice, CellType.NUMERIC);
            }
            excel.writeExcel(workbook);
            System.out.println("끝");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    
    public String getInOutFile() {
        return inOutFile;
    }
    public void setInOutFile(String inOutFile) {
        this.inOutFile = inOutFile;
    }
   
}
