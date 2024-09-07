package vn.springboot.QuanLyTruongHoc.dto;
import jakarta.validation.constraints.NotBlank;

import java.util.*;

public class ClassroomDto {
    @NotBlank(message = "Tên không thể bỏ trống")
    private String className;

    @NotBlank(message = "Khối học không thể bỏ trống")
    private String classBlock;
    private int schoolYear;

    private List<Integer> subjectsId;
    
    private String teacherId;

    public ClassroomDto() {
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassBlock() {
        return classBlock;
    }

    public void setClassBlock(String classBlock) {
        this.classBlock = classBlock;
    }

    public int getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(int schoolYear) {
        this.schoolYear = schoolYear;
    }

    public List<Integer> getSubjectsId() {
        return subjectsId;
    }

    public void setSubjectsId(List<Integer> subjectsId) {
        this.subjectsId = subjectsId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }
}
