package vn.springboot.QuanLyTruongHoc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.springboot.QuanLyTruongHoc.entity.Account;

@Repository
public interface AccountDao extends JpaRepository<Account, String> {
     Account findAccountByEmail(String email);
}
