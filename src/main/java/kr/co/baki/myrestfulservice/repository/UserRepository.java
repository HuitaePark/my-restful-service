package kr.co.baki.myrestfulservice.repository;

import kr.co.baki.myrestfulservice.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


}
