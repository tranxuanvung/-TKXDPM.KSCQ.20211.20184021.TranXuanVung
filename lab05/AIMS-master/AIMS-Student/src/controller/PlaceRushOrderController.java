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

public class PlaceRushOrderController extends BaseController implements ShippingFeeCaculator{

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
        ValidationController vali = new ValidationController();
        Cart.getCart().checkAvailabilityOfProduct(); //--> 'Cart' object is static*
        if(!vali.validateDate(expectedDate, currDate)) {
            throw new InvalidDeliveryInfoException("Chosen date is invalid");
        }
        if(!vali.validateRushAddress(address)) {
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
        ValidationController vali = new ValidationController();
        LOGGER.info("Process Rush Order Delivery date");
        LOGGER.info("expected: "+ expectedDate.toString() + ",current:" + currDate.toString());
        vali.validateDate(expectedDate, currDate);
    }


    /**
     * This method determines if the express shipping rush information is valid or not
     * @param info
     * @throws InterruptedException
     * @throws IOException
     */
    public void validateRushDeliveryInfo(HashMap<String, String> info) throws InterruptedException, IOException{
        ValidationController vali = new ValidationController();
        if(vali.validatePhoneNumber(info.get("phone")) && vali.validateName(info.get("name"))&&vali.validateRushAddress("address")){}
        else throw new InvalidDeliveryInfoException("Some info is invalid");
    }


    /**
     * This method calculates the shipping fees of order
     * @param amount
     * @return shippingFee
     */
    @Override
    public int calculateShippingFee(int amount){
        Random rand = new Random();
        int fees = (int)( ( (rand.nextFloat()*10)/100 ) * amount );
        LOGGER.info("Order Amount: " + amount + " -- Shipping Fees: " + fees);
        return fees;
    }
}
