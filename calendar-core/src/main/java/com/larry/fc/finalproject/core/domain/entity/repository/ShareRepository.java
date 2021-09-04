package com.larry.fc.finalproject.core.domain.entity.repository;

import com.larry.fc.finalproject.core.domain.RequestStatus;
import com.larry.fc.finalproject.core.domain.entity.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ShareRepository extends JpaRepository<Share, Long> {

    @Query("SELECT s FROM Share s WHERE (s.fromUserId = ?1 or s.toUserId = ?1) and s.requestStatus = ?2 and s.direction = ?3")
    List<Share> findAllByBiDirection(Long fromUserId,
                                     RequestStatus requestStatus,
                                     Share.Direction direction
    );

    List<Share> findAllByToUserIdAndRequestStatusAndDirection(Long fromUser,
                                                              RequestStatus requestStatus,
                                                              Share.Direction direction
    );
}
