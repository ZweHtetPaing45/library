package com.library.zweibrary.admin_two.twoRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.zweibrary.admin_two.two.Two;

@Repository
public interface TwoRepo extends JpaRepository<Two, Long> {

}
