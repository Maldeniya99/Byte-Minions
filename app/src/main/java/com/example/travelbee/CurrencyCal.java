package com.example.travelbee;

public class CurrencyCal {
    protected Double convertSLruppeeToUSD(Double amount) {
        Double ans = (amount * 198.23) ;
        return ans;
    }
    protected Double convertSLrupeeToEUR(Double value) {
        Double ans = (value * 229.69) ;
        return ans;
    }
    protected Double convertSLrupeeToAUD(Double value) {
        Double ans = (value * 143.56) ;
        return ans;
    }
    protected Double convertSLrupeeToCAD(Double value) {
        Double ans = (value * 156.32);
        return ans;
    }
    protected Double convertSLrupeeToINR(Double value) {
        Double ans = (value * 2.67) ;
        return ans;
    }
}
