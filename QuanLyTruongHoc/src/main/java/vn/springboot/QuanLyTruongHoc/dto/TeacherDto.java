package vn.springboot.QuanLyTruongHoc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.*;

public class TeacherDto {
    @Pattern(regexp = "^\\w{10}$", message = "Mã phải chứa đúng 10 ký tự")
    private String id;

    @NotBlank(message = "Tên không được bỏ trống !")
    private String name;

    @NotBlank(message = "Ngày sinh không được bỏ trống !")
    private String dateOfBirth;

    @NotBlank(message = "Giới tính không được bỏ trống !")
    private String gender;

    @NotBlank(message = "Địa chỉ không được bỏ trống !")
    private String address;

    @Pattern(regexp = "\\d{10}", message = "Số điện thoại phải chứa 10 kí tự")
    private String phone;

    private List<String> rolesName;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email không đúng định dạng")
    private String emailTeacher;

    public TeacherDto() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getRolesName() {
        return rolesName;
    }

    public void setRolesName(List<String> rolesName) {
        this.rolesName = rolesName;
    }

    public String getEmailTeacher() {
        return emailTeacher;
    }

    public void setEmailTeacher(String emailTeacher) {
        this.emailTeacher = emailTeacher;
    }


}



