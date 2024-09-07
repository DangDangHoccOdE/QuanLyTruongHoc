package vn.springboot.QuanLyTruongHoc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.springboot.QuanLyTruongHoc.entity.RegisterNotebookDetail;

import java.util.*;
@Repository
public interface RegisterNotebookDetailDao extends JpaRepository<RegisterNotebookDetail,Integer> {
     List<RegisterNotebookDetail> getRegisterNoteDetailByClassroomId(int id);
}
