package vn.springboot.QuanLyTruongHoc.utils;

import org.springframework.stereotype.Component;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class ConvertString {
    public Date convertStringToDate(String stringDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date ;
        try {
            date =  sdf.parse(stringDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return new Date(date.getTime());

    }
}
