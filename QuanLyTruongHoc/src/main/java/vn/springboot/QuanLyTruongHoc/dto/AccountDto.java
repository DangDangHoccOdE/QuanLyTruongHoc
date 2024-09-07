package vn.springboot.QuanLyTruongHoc.dto;

import jakarta.validation.constraints.Pattern;

public class AccountDto {
    @Pattern(regexp = "\\d{10}", message = "Mã Giáo Viên phải chứa 10 kí tự !")
    private String id;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email không đúng định dạng")
    private String email;

    @Pattern(regexp = "^.{8,20}$", message = "Mật khẩu phải chứa từ 8-20 ký tự")
    private String password;

    @Pattern(regexp = "^.{8,20}$", message = "Mật khẩu phải chứa từ 8-20 ký tự")
    private String passwordNew;

    @Pattern(regexp = "^.{8,20}$", message = "Mật khẩu phải chứa từ 8-20 ký tự")
    private String previousPassword;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountDto() {
    }

    public String getPasswordNew() {
        return passwordNew;
    }


    public String getPreviousPassword() {
        return previousPassword;
    }

    public void setPasswordNew(String passwordNew) {
        this.passwordNew = passwordNew;
    }

    public void setPreviousPassword(String previousPassword) {
        this.previousPassword = previousPassword;
    }
}
