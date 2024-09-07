package vn.springboot.QuanLyTruongHoc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class ParentDto {
    @NotBlank(message = "Họ tên phụ huynh không được bỏ trống")
    private String parentName;

    @Pattern(regexp = "\\d{10}",message = "Số điện thoại phải có 10 số")
    private String parentPhone;

    @NotBlank(message = "Ngày sinh không được bỏ trống")
    private String parentDate;

    @NotBlank(message = "Địa chỉ phụ huynh không thể bỏ trống")
    private String parentAddress;


    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentPhone() {
        return parentPhone;
    }

    public void setParentPhone(String parentPhone) {
        this.parentPhone = parentPhone;
    }

    public String getParentDate() {
        return parentDate;
    }

    public void setParentDate(String parentDate) {
        this.parentDate = parentDate;
    }

    public String getParentAddress() {
        return parentAddress;
    }

    public void setParentAddress(String parentAddress) {
        this.parentAddress = parentAddress;
    }
}
