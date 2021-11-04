package kr.couchcoding.tennis_together.domain.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.couchcoding.tennis_together.domain.user.dao.UserRepository;

@Service
public class UserService implements UserDetailsService{
    @Autowired
    UserRepository userRepository;

    /**
     * User uid로 가져온다(오버라이드용)
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {        
        return userRepository.findById(username).get();
    }
}