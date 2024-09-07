package vn.springboot.QuanLyTruongHoc.service.inter;

import vn.springboot.QuanLyTruongHoc.dto.RegisterNotebookDetailDto;
import vn.springboot.QuanLyTruongHoc.entity.Classroom;
import vn.springboot.QuanLyTruongHoc.entity.RegisterNotebookDetail;
import vn.springboot.QuanLyTruongHoc.entity.Teacher;

import java.util.*;

public interface IRegisterNotebookDetailService {
     List<RegisterNotebookDetail> getRegisterNotebookDetailByClassId(int id);

     void saveRegisterNotebookDetail(RegisterNotebookDetail registerNotebookDetail);

     RegisterNotebookDetail findRegisterNotebookDetailById(int id);

     void delete(RegisterNotebookDetail registerNotebookDetail);

     RegisterNotebookDetailDto getRegisterNotebookDetailDto(RegisterNotebookDetail registerNotebookDetail);
     void saveRegisterNotebookDetail(RegisterNotebookDetailDto registerNotebookDetailDto, RegisterNotebookDetail registerNotebookDetail, int classId, Teacher teacher);

     List<RegisterNotebookDetail> findRegisterNotebookDetailByTeacher(Teacher teacher, Classroom classroom);
}

