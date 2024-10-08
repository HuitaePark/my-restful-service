package kr.co.baki.myrestfulservice.dao;

import kr.co.baki.myrestfulservice.bean.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {
    private static List<User>users =new ArrayList<User>();

    private static int usersCount = 3;

    static {
        users.add(new User(1, new Date(),"케네스","test1","701010-1111111"));
        users.add(new User(2, new Date(),"소마즈","test2","801010-1111111"));
        users.add(new User(3, new Date(),"리소스","test3","901010-1111111"));
    }
    //전체목록
    public List<User> findAll(){
        return users;
    }
    //저장 하기
    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++usersCount);
        }
        if (user.getJoinDate()==null){
            user.setJoinDate(new Date());
        }
        users.add(user);

        return user;
    }
    //개별 목록
    public User findOne(int id){
        for (User user : users) {
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }
    public User deleteById(int id){
        Iterator<User> Iterator = users.iterator();
        while(Iterator.hasNext()) {
            User user = Iterator.next();
            if (user.getId() == id) {
                Iterator.remove();
                return user;
            }
        }
        return null;
    }
}