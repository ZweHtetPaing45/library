package com.library.zweibrary.admin_two.two_Service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.library.zweibrary.admin_two.two.Two;
import com.library.zweibrary.admin_two.twoRepo.TwoRepo;

@Service
public class TwoService {

    @Autowired
    public TwoRepo twoRepo;

    public void saveTwo(MultipartFile file) throws IOException{
        Two two=new Two();
        two.setName(file.getOriginalFilename());
        two.setType(file.getContentType());
        two.setData(file.getBytes());
        twoRepo.save(two);
    }

}
