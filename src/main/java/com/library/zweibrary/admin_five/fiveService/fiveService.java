package com.library.zweibrary.admin_five.fiveService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.library.zweibrary.admin_five.five.five;
import com.library.zweibrary.admin_five.fiveRepo.fiveRepo;

@Service
public class fiveService {

    @Autowired
    private fiveRepo fiverepo;

     public void saveFive(MultipartFile file) throws IOException{
        five five=new five();
        five.setName(file.getOriginalFilename());
        five.setType(file.getContentType());
        five.setData(file.getBytes());
        fiverepo.save(five);
    }

}
