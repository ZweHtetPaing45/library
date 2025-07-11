package com.library.zweibrary.admin_three.threeRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.zweibrary.admin_three.three.three;

@Repository
public interface threeRepo extends JpaRepository<three, Long> {

}
