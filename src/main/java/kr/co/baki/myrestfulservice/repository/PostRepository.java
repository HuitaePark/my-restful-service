package kr.co.baki.myrestfulservice.repository;

import kr.co.baki.myrestfulservice.bean.Post;
import kr.co.baki.myrestfulservice.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{
}
