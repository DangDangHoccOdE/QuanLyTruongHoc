package vn.springboot.QuanLyTruongHoc.service.inter;

import vn.springboot.QuanLyTruongHoc.entity.Roles;

public interface IRolesService {
     Roles save(Roles roles);

     Roles findRolesByName(String rolesName);
}
