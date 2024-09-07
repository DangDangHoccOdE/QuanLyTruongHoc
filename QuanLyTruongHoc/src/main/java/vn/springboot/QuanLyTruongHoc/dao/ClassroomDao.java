package vn.springboot.QuanLyTruongHoc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.springboot.QuanLyTruongHoc.entity.Classroom;
import java.util.*;

@Repository
public interface ClassroomDao extends JpaRepository<Classroom, Integer> {
     Classroom findClassroomByClassName(String name);
}
