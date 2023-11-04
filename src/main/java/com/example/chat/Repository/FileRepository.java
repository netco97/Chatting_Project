package com.example.chat.Repository;

import com.example.chat.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity,String> {
}
