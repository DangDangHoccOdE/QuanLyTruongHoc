package vn.springboot.QuanLyTruongHoc.dto;

import jakarta.validation.constraints.*;

public class SubjectDto {
    @NotBlank(message = "Tên không được bỏ trống")
    private String name;

    @Size(min = 5,message = "Phải chứa ít nhất 5 kí tự")
    @Size(max = 200,message = "Chứa nhiều nhất 200 kí tự")
    private String description;

    private String teacherId;

    @Min(value = 1,message = "Số tiết phải lớn hơn 1")
    @Max(value = 75,message = "Số tiết phải nhỏ hơn 75")
    @NotNull(message = "Thông tin bắt buộc")
    private Integer numberOfPeriods;

    public SubjectDto() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getNumberOfPeriods() {
        return numberOfPeriods;
    }

    public void setNumberOfPeriods(Integer numberOfPeriods) {
        this.numberOfPeriods = numberOfPeriods;
    }
}
