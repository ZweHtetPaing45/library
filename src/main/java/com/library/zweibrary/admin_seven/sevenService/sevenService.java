package com.library.zweibrary.admin_seven.sevenService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.library.zweibrary.admin_seven.seven.seven;
import com.library.zweibrary.admin_seven.sevenRepo.sevenRepo;

@Service
public class sevenService {

    @Autowired
    private sevenRepo sevenrepo;

     public void saveSeven(MultipartFile file) throws IOException{
        seven seven=new seven();
        seven.setName(file.getOriginalFilename());
        seven.setType(file.getContentType());
        seven.setData(file.getBytes());
        sevenrepo.save(seven);
    }
}
