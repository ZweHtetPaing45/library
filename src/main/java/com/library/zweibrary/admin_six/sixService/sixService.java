package com.library.zweibrary.admin_six.sixService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.library.zweibrary.admin_six.six.six;
import com.library.zweibrary.admin_six.sixRepo.sixRepo;

@Service
public class sixService {

    @Autowired
    private sixRepo sixrepo;

     public void saveSix(MultipartFile file) throws IOException{
        six six=new six();
        six.setName(file.getOriginalFilename());
        six.setType(file.getContentType());
        six.setData(file.getBytes());
        sixrepo.save(six);
    }

}
