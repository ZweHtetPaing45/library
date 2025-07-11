package com.library.zweibrary.admin_ten.tenRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.zweibrary.admin_ten.ten.ten;

@Repository
public interface tenRepo extends JpaRepository<ten, Long> {

}
