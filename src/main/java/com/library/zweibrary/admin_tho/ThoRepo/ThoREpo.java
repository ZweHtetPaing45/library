package com.library.zweibrary.admin_tho.ThoRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.zweibrary.admin_tho.Tho.Tho;

@Repository
public interface ThoREpo extends JpaRepository<Tho, Long> {

}
