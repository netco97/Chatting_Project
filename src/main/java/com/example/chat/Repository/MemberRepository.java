package com.example.chat.Repository;

import com.example.chat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository <User,Long>{

}
