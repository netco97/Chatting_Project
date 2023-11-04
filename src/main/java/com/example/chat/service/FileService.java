package com.example.chat.service;

import com.example.chat.Handler.FileHandler;
import com.example.chat.Repository.FileRepository;
import com.example.chat.entity.FileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FileService {
    private final FileRepository fileRepository;

    private final FileHandler fileHandler;

    @Autowired
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
        this.fileHandler = new FileHandler();
    }

    public void addBoard(
            FileEntity fileEntity,
            List<MultipartFile> files, String roomId
    ) throws Exception {
        // 파일을 저장하고 그 FileEntity 에 대한 list 를 가지고 있는다
        List<FileEntity> list = fileHandler.parseFileInfo(roomId, files);

        if (list.isEmpty()){
            // TODO : 파일이 없을 땐 어떻게 해야할까.. 고민을 해보아야 할 것
        }
        // 파일에 대해 DB에 저장하고 가지고 있을 것
        else{
            List<FileEntity> pictureBeans = new ArrayList<>();
            for (FileEntity fileentites : list) {
                pictureBeans.add(fileRepository.save(fileentites));
            }
        }


    }

    public List<FileEntity> findFiles() {
        return fileRepository.findAll();
    }

    public Optional<FileEntity> findFile(String roomId) {
        return fileRepository.findById(roomId);
    }

}
