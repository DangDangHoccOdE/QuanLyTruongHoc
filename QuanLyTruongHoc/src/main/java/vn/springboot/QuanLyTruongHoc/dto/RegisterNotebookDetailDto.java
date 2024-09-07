package vn.springboot.QuanLyTruongHoc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class RegisterNotebookDetailDto {
    private String className;

    private String teacherId;

    private String semester;

    @NotBlank(message = "Thời gian dạy là bắt buộc")
    private String timeTeaching;

    @NotEmpty(message = "Phải ghi nội dung mỗi buổi học")
    private String content;

    private String lesson;

    public RegisterNotebookDetailDto() {
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getTimeTeaching() {
        return timeTeaching;
    }

    public void setTimeTeaching(String timeTeaching) {
        this.timeTeaching = timeTeaching;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
