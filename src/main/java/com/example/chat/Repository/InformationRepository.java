package com.example.chat.Repository;

import com.example.chat.entity.InformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface InformationRepository extends JpaRepository<InformationEntity, String> {

    //Native Query (SQL문을 DB에 직접)
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE information_table i SET i.pro=i.pro+?1 , i.con=i.con+?2 where i.room_Id=?3", nativeQuery = true)
    void updateVote(int pro, int con, String roomId);

    //Native Query
    @Query(value = "select pro from information_table where room_Id=?1" , nativeQuery = true)
    int proCount(String roomId);
    @Query(value = "select con from information_table where room_Id=?1", nativeQuery = true)
    int conCount(String roomId);

}
