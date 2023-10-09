package com.example.chat.Repository;

import com.example.chat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository <User,Long>{

}
