package springcloud.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springcloud.springboot.entity.UserInfo;

@Repository
public interface UserInfoDao extends JpaRepository<UserInfo, Integer> {

}
