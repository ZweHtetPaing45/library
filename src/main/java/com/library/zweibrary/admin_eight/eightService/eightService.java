package com.library.zweibrary.admin_eight.eightService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.library.zweibrary.admin_eight.eight.eight;
import com.library.zweibrary.admin_eight.eightRepo.eightRepo;

@Service
public class eightService {

    @Autowired
    private eightRepo eightrepo;

    public void saveEight(MultipartFile file) throws IOException{
        eight eight=new eight();
        eight.setName(file.getOriginalFilename());
        eight.setType(file.getContentType());
        eight.setData(file.getBytes());
        eightrepo.save(eight);
    }
}