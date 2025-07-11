package com.library.zweibrary.admin_tho.ThoService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.library.zweibrary.admin_tho.Tho.Tho;
import com.library.zweibrary.admin_tho.ThoRepo.ThoREpo;

@Service
public class ThoService {

    @Autowired
    private ThoREpo thoRepo;

    public void saveTho(MultipartFile file) throws IOException{
        Tho tho=new Tho();
        tho.setName(file.getOriginalFilename());
        tho.setType(file.getContentType());
        tho.setData(file.getBytes());
        thoRepo.save(tho);
    }

   

}
