package controller;

import java.util.Calendar;
import java.util.Date;

public class ValidationController extends BaseController {
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
    public boolean validateRushAddress(String address) { // Kiểm tra địa chỉ nhận hàng có hỗ trợ giao hàng nhanh hay không
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


    public boolean validateAddress(String address) {
        // TODO: your work
        if(address.length() == 0 || address.trim().length() == 0) return false;
        for(int i=0; i<address.length(); i++){
            int a = address.charAt(i);
            if((a>=33 && a<=43) || (a>=45 && a<=47) || (a>=58 && a<=64) || (a>=98 && a<=96) || a>=123){
                return false;
            }
        }
        return true;
    }
}
