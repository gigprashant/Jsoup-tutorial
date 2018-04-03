package com.mycompany.jsouptutorial;

import java.util.List;

/**
 *
 * @author prashant
 */
public class StockResponse {

    private String message;
    private boolean success;
    private List<StockParam> stockParams;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<StockParam> getStockParams() {
        return stockParams;
    }

    public void setStockParams(List<StockParam> stockParams) {
        this.stockParams = stockParams;
    }

}
