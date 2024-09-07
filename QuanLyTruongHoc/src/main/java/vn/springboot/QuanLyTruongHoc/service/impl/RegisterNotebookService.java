package vn.springboot.QuanLyTruongHoc.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.springboot.QuanLyTruongHoc.dao.RegisterNotebookDao;
import vn.springboot.QuanLyTruongHoc.entity.RegisterNotebook;
import vn.springboot.QuanLyTruongHoc.service.inter.IRegisterNotebookService;

@Service
public class RegisterNotebookService implements IRegisterNotebookService {
    private final RegisterNotebookDao registerNotebookDao;

    @Autowired
    public RegisterNotebookService(RegisterNotebookDao registerNotebookDao) {
        this.registerNotebookDao = registerNotebookDao;
    }

    @Override
    @Transactional
    public void save(RegisterNotebook registerNotebook) {
        registerNotebookDao.save(registerNotebook);
    }

    @Override
    public RegisterNotebook getRegisterNotebookByClassId(int id) {
        return registerNotebookDao.getRegisterNotebookByClassroomId(id);
    }
}
