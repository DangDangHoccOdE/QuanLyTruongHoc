package vn.springboot.QuanLyTruongHoc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.springboot.QuanLyTruongHoc.entity.RegisterNotebook;

@Repository
public interface RegisterNotebookDao extends JpaRepository<RegisterNotebook , Integer> {
     RegisterNotebook getRegisterNotebookByClassroomId(int id);
}
