package vn.springboot.QuanLyTruongHoc.service.inter;

import vn.springboot.QuanLyTruongHoc.dto.ParentDto;
import vn.springboot.QuanLyTruongHoc.entity.Parent;
import vn.springboot.QuanLyTruongHoc.entity.Student;

public interface IParentService {
     void saveParent(Parent parent);

     Parent findParentByStudentId(String studentId);

     ParentDto getParentDto(Student student);
}
