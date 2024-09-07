package vn.springboot.QuanLyTruongHoc.dto;


import jakarta.validation.constraints.*;

public class StudentDto {
    @Pattern(regexp = "\\d{10}",message = "Mã học sinh phải có 10 số")
    private String studentId;

    @NotBlank(message = "Tên học sinh không được bỏ trống")
    private String name;

    private String className;

    @NotBlank(message = "Giới tính không được bỏ trống")
    private String gender;


    @NotBlank(message = "Ngày sinh không được bỏ trống")
    private String dateOfBirth;


    @NotBlank(message = "Địa chỉ không được bỏ trống")
    private String address;

    @Pattern(regexp = "\\d{10}",message = "Số điện thoại phải có 10 số")
    private String phone;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",message = "Email không đúng định dạng")
    private String studentEmail;

    private String studentRole;

    public StudentDto() {
        this.studentRole="ROLE_STUDENT";
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentRole() {
        return studentRole;
    }

    public void setStudentRole(String studentRole) {
        this.studentRole = studentRole;
    }
}
