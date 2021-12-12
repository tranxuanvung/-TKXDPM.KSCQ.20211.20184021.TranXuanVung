package controller;


import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import common.exception.InvalidDeliveryInfoException;
import common.exception.MediaNotAvailableException;
import entity.cart.Cart;
import entity.cart.CartMedia;
import entity.invoice.Invoice;
import entity.order.Order;
import entity.order.OrderMedia;

public class PlaceRushOrderController extends BaseController{

    /**
     * Work to do:
     * placeRushOrder(cart: Cart, deliveryInfo: String, deliveryStruction: String, requireDate: date)
     */


    /**
     * For logging purpose
     */
    private static Logger LOGGER = utils.Utils.getLogger(PlaceRushOrderController.class.getName());


    /**
     * This method checks the avalibility for Rush Order of the products in the Cart
     * @throws SQLException
     */
    public void placeRushOrder(Date expectedDate,Date currDate, String address) throws SQLException{
        Cart.getCart().checkAvailabilityOfProduct(); //--> 'Cart' object is static*
        if(!validateDate(expectedDate, currDate)) {
            throw new InvalidDeliveryInfoException("Chosen date is invalid");
        }
        if(!validateAddress(address)) {
            throw new InvalidDeliveryInfoException("Address does not support Rush Order");
        }
    }


    /**
     * This method processes purchase information
     * @param info
     * @throws InterruptedException
     * @throws IOException
     */
    public void processRushDeliveryInfo(HashMap info) throws InterruptedException, IOException{
        LOGGER.info("Process Rush Order Delivery Info");
        LOGGER.info(info.toString());
        validateRushDeliveryInfo(info);
    }


    /**
     * This method determines whether the date of receipt and order in Rush Order is valid or not
     * @param expectedDate
     * @param currDate
     * @throws InterruptedException
     * @throws IOException
     */
    public void processRushOrderDate(Date expectedDate, Date currDate) throws InterruptedException, IOException {
        LOGGER.info("Process Rush Order Delivery date");
        LOGGER.info("expected: "+ expectedDate.toString() + ",current:" + currDate.toString());
        validateDate(expectedDate, currDate);
    }


    /**
     * This method determines if the express shipping rush information is valid or not
     * @param info
     * @throws InterruptedException
     * @throws IOException
     */
    public void validateRushDeliveryInfo(HashMap<String, String> info) throws InterruptedException, IOException{
        if(validatePhoneNumber(info.get("phone")) && validateName(info.get("name"))){}
        else throw new InvalidDeliveryInfoException("Some info is invalid");
    }


    /**
     * This method checks the validity of the phone number
     * @param phoneNumber
     * @return
     */
    public boolean validatePhoneNumber(String phoneNumber) { // Kiểm tra các điều kiện không hợp lệ của số điện thoại
        if(phoneNumber.length() != 10) return false; // trường hợp số điện thoại lớn hơn 10 số
        if(phoneNumber.charAt(0) != '0') return false; // trường hợp số điện thoại không bắt đầu bằng 0
        try {
            Integer.parseInt(phoneNumber);
        }catch(NumberFormatException e) {
            return false;
        }
        return true;
    }


    /**
     * This method checks the validity of the name
     * @param name
     * @return
     */
    public boolean validateName(String name) { // Kiểm tra các trường hợp không hợp lệ của tên - chứa các ký tự đặc biệt
        if(name.length() == 0 || name.trim().length() == 0) return false; // trường hợp tên rỗng
        for(int i=0; i<name.length(); i++){ // Xét chuỗi tên để check xem có những ký tự không hợp lệ hay không
            int a = name.charAt(i);
            if(a!=32){
                if((a<65 || (a>90 && a<97) || a>122)){
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Check the address supports fast delivery or not
     * @param address
     * @return
     */
    public boolean validateAddress(String address) { // Kiểm tra địa chỉ nhận hàng có hỗ trợ giao hàng nhanh hay không
        if(address.length() == 0 || address.trim().length() == 0) return false; // trường hợp địa chỉ rỗng
        String[] trueArray = {"hanoi", "ha noi", "haf noij", "hn", "hcm", "hochiminh", "hof chis minh", "hà nội", "hồ chí minh"};
        // mảng chứa tên các tỉnh hỗ trợ giao hàng nhanh
        for(int i = 0; i < trueArray.length; i++) { // Xét địa chỉ giao hàng xem có bao gồm những tỉnh hỗ trợ giao hàng nhanh hay không
            if(address.toLowerCase().endsWith(trueArray[i])){
                return true;
            }
        }
        return false;
    }


    /**
     * This method determine the validity of order date and receipt date
     * @param expectedDate
     * @param date
     * @return
     */
    public boolean validateDate(Date expectedDate, Date date) { // Kiểm tra ngày nhận hàng và ngày đặt hàng
        // có hợp lệ theo logic thời gian hay không
        Date currDate = Calendar.getInstance().getTime();
        if (expectedDate.after(date)) return true;
        return false;
    }


    /**
     * This method calculates the shipping fees of order
     * @param order
     * @return shippingFee
     */
    public int calculateShippingFee(Order order){
        Random rand = new Random();
        int fees = (int)( ( (rand.nextFloat()*10)/100 ) * order.getAmount() );
        LOGGER.info("Order Amount: " + order.getAmount() + " -- Shipping Fees: " + fees);
        return fees;
    }
}
