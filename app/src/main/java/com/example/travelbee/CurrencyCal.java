package com.example.travelbee;

public class CurrencyCal {
    protected Double convertUSDToSLruppee(Double amount) {
        Double ans = (amount * 198.23) ;
        return ans;
    }
    protected Double convertEURToSLruppee(Double value) {
        Double ans = (value * 229.69) ;
        return ans;
    }
    protected Double convertAUDToSLruppee(Double value) {
        Double ans = (value * 143.56) ;
        return ans;
    }
    protected Double convertCADToSLruppee(Double value) {
        Double ans = (value * 156.32);
        return ans;
    }
    protected Double convertINRToSLruppee(Double value) {
        Double ans = (value * 2.67) ;
        return ans;
    }
}
