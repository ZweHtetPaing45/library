package com.library.zweibrary.admin_nine.nineService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.library.zweibrary.admin_nine.nine.nine;
import com.library.zweibrary.admin_nine.nineRepo.nineRepo;

@Service
public class nineService {

    @Autowired
    private nineRepo ninerepo;

     public void saveNine(MultipartFile file) throws IOException{
        nine nine=new nine();
        nine.setName(file.getOriginalFilename());
        nine.setType(file.getContentType());
        nine.setData(file.getBytes());
        ninerepo.save(nine);
    }

}