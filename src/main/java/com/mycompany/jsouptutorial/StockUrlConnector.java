package com.mycompany.jsouptutorial;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author prashant
 */
public class StockUrlConnector {

    public StockResponse callStockAPI(String stockUrl) {

        System.out.println("entering callStockAPI");
        StockResponse stockResponse = new StockResponse();

        try {
            HttpsCertification.byPassHttpsCertification();

            Document doc = Jsoup.connect(stockUrl).get();

            Element table = doc.select("table").get(0);

            Elements stockValueRows = table.select("tr");
            System.out.println("stockValueRows : " + stockValueRows);

            List<StockParam> stockParamter = stockListParser(stockValueRows);
            if (stockParamter.size() > 0) {
                stockResponse.setSuccess(true);
                stockResponse.setStockParams(stockParamter);
                stockResponse.setMessage("Stock Obtained Successfully.");
            }

        } catch (IOException | KeyManagementException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return stockResponse;
    }

    private List<StockParam> stockListParser(Elements rows) {
        List<StockParam> sp = new ArrayList<>();

        String[] paramArray = new String[11];

        int arrayIndex = 0;
        for (int i = 4; i <= rows.size() - 1; i++) {
            paramArray[arrayIndex] = rows.get(i).select("td").get(1).text();
            arrayIndex++;
        }

        StockParam param = new StockParam();
        param.setNameSymbol(paramArray[0]);
        param.setAddress(paramArray[1]);
        param.setEmail(paramArray[2]);
        param.setWebsite(paramArray[3]);
        param.setTradedPrice(paramArray[4]);
        param.setCharge(paramArray[5]);
        param.setListedShare(paramArray[6]);
        param.setPaidUpValue(paramArray[7]);
        param.setTotalPaidUpValue(paramArray[8]);
        param.setMarketPrice(paramArray[9]);
        param.setMarketCapitalization(paramArray[10]);

        sp.add(param);

        return sp;
    }

    public static void main(String[] args) {
        StockResponse stockResponse = new StockResponse();
        String stockUrl = "http://nepalstock.com.np/company/display/140";
        StockUrlConnector stockUrlConnector = new StockUrlConnector();

        stockResponse = stockUrlConnector.callStockAPI(stockUrl);

        System.out.println("stock response is obtained: " + stockResponse.isSuccess());
        System.out.println("stock response message: " + stockResponse.getMessage());
        System.out.println("stock response params: " + stockResponse.getStockParams().toString());
    }
}
