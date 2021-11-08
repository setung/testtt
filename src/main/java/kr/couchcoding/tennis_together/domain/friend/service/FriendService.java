package kr.couchcoding.tennis_together.domain.friend.service;

import kr.couchcoding.tennis_together.controller.friends.dto.FollowResponseDTO;
import kr.couchcoding.tennis_together.domain.court.model.Court;
import kr.couchcoding.tennis_together.domain.friend.dao.FriendDAO;
import kr.couchcoding.tennis_together.domain.friend.model.FrdList;
import kr.couchcoding.tennis_together.domain.user.model.User;
import kr.couchcoding.tennis_together.exception.CustomException;
import kr.couchcoding.tennis_together.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class FriendService {
    
    @Autowired
    FriendDAO friendDAO; // DAO 주입,삽입

    @Transactional
    public void followFriend(User user, User friend) {// 어떤유저가 누굴 팔로하는지
        // 친구를 가져오는 로직 추가되어야 함
        FrdList existData = friendDAO.findByUserAndFrdUser(user, friend);
        if(existData == null) {
            FrdList frdList = FrdList.builder().frdUser(friend).user(user).build(); // 친구목록 한 줄 추가
            friendDAO.save(frdList); // 저장
        } else {
            throw new CustomException(ErrorCode.EXIST_FRIEND);
        }
    }


    public Page<FollowResponseDTO> getFollowList(User user, Pageable pageable) {
        return friendDAO.findByUser(user,pageable); // jpa에서 get의 동작방식이 다르기 때문에 jpa에서는 -> find
    }

}
