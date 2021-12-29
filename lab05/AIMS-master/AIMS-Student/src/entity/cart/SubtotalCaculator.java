package entity.cart;

public interface SubtotalCaculator {
    public default int calSubtotal(){
        return 0;
    }
}
