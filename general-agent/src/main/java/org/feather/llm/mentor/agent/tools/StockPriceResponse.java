package org.feather.llm.mentor.agent.tools;

import java.util.List;

/**
 * @projectName: feather-llmentor
 * @package: org.feather.llm.mentor.agent.tools
 * @className: StockPriceResponse
 * @author: feather
 * @description:
 * @since: 2026-06-26 3:36 PM
 * @version: 1.0
 */
public class StockPriceResponse {

    private List<Stock> stockList;

    private boolean isSuccess;

    private String errorMessage;

    public List<Stock> getStockList() {
        return stockList;
    }

    public void setStockList(List<Stock> stockList) {
        this.stockList = stockList;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
