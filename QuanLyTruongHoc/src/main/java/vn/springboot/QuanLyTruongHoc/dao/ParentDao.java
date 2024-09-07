package vn.springboot.QuanLyTruongHoc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.springboot.QuanLyTruongHoc.entity.Parent;

@Repository
public interface ParentDao extends JpaRepository<Parent,Integer> {
    Parent findParentByStudent_StudentId(String id);
}
