package management.bean;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class TienIch {
	
    public static String dateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = dateFormat.format(date);
        return formattedDate;
    }
    
    public static String toVND (int n) {
    	DecimalFormat decimalFormat = new DecimalFormat("#,### đ");
        String formattedAmount = decimalFormat.format(n);
        return formattedAmount;
	}
    
    public static String toVND (Double n) {
    	DecimalFormat decimalFormat = new DecimalFormat("#,### đ");
        String formattedAmount = decimalFormat.format(n);
        return formattedAmount;
	}
    
    public static boolean isExpired_day(Date purchaseDate, int expirationDays) {
        // Lấy ngày hiện tại
        Calendar currentCalendar = Calendar.getInstance();
        Date currentDate = currentCalendar.getTime();
        
        // Tạo một Calendar từ ngày mua và thiết lập ngày lớn hơn theo số tháng và ngày hết hạn
        Calendar expirationCalendar = Calendar.getInstance();
        expirationCalendar.setTime(purchaseDate);

        expirationCalendar.add(Calendar.DAY_OF_MONTH, expirationDays);
        Date expirationDate = expirationCalendar.getTime();
        
        // Kiểm tra xem ngày hiện tại có lớn hơn ngày hết hạn hay không
        return currentDate.after(expirationDate);
    }
    
    public static boolean isExpired_month(Date purchaseDate, int expirationMonths) {
        // Lấy ngày hiện tại
        Calendar currentCalendar = Calendar.getInstance();
        Date currentDate = currentCalendar.getTime();
        
        // Tạo một Calendar từ ngày mua và thiết lập ngày lớn hơn theo số tháng và ngày hết hạn
        Calendar expirationCalendar = Calendar.getInstance();
        expirationCalendar.setTime(purchaseDate);
        expirationCalendar.add(Calendar.MONTH, expirationMonths);
        Date expirationDate = expirationCalendar.getTime();
        
        // Kiểm tra xem ngày hiện tại có lớn hơn ngày hết hạn hay không
        return currentDate.after(expirationDate);
    }
    
    
}
