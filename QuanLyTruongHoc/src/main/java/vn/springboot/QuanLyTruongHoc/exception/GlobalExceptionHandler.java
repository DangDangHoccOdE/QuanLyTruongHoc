package vn.springboot.QuanLyTruongHoc.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import vn.springboot.QuanLyTruongHoc.utils.Log;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDenied(){
        return "/error/AccessDenied";
    }

    @ExceptionHandler({NoResourceFoundException.class, NullPointerException.class,EntityNotFoundException.class})
    public String handleNoResourceFoundException(Exception e) {
        Log.info("NoResourceFoundException occurred: " + e.getMessage());
        return "/error/NoResourceFound";
    }
}

