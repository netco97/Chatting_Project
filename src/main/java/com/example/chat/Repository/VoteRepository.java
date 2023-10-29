package com.example.chat.Repository;

import com.example.chat.entity.VoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<VoteEntity, String> {

    //Native Query (SQL문을 DB에 직접)
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE vote_table v SET v.pro=v.pro+?1 , v.con=v.con+?2 where v.room_Id=?3", nativeQuery = true)
    void updateVote(int pro, int con, String roomId);

    //Native Query
    @Query(value = "select pro from vote_table where room_Id=?1" , nativeQuery = true)
    int proCount(String roomId);
    @Query(value = "select con from vote_table where room_Id=?1", nativeQuery = true)
    int conCount(String roomId);

}
