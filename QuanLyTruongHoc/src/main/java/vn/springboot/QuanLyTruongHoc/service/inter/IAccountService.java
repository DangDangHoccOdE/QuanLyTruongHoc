package vn.springboot.QuanLyTruongHoc.service.inter;

import vn.springboot.QuanLyTruongHoc.entity.Account;

public interface IAccountService {
     Account saveAccount(Account account);

     Account findAccountByEmail(String email);
}
