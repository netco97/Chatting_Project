package com.example.chat.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(name = "file_table")
public class FileEntity extends BaseTimeEntity{

    @Id
    private String roomId;

    @Column
    private String originalFileName;

    @Column
    private String storedFileName;

    private long fileSize;

}