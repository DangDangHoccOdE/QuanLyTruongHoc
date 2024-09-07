package vn.springboot.QuanLyTruongHoc.service.inter;

import vn.springboot.QuanLyTruongHoc.entity.RegisterNotebook;

public interface IRegisterNotebookService {
     void save(RegisterNotebook registerNotebook);

     RegisterNotebook getRegisterNotebookByClassId(int id);
}
