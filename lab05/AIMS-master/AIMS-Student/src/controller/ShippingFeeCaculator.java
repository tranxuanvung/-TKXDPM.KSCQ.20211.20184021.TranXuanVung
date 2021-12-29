package controller;

public interface ShippingFeeCaculator {
    public default int calculateShippingFee(int amount){
        return amount;
    }
}
