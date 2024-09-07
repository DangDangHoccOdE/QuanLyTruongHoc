package vn.springboot.QuanLyTruongHoc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.springboot.QuanLyTruongHoc.entity.Roles;

@Repository
public interface RolesDao extends JpaRepository<Roles,Integer> {
     Roles findRolesByRoleName(String name);
}
