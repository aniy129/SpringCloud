package springcloud.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springcloud.springboot.dao.UserInfoDao;
import springcloud.springboot.entity.UserInfo;

import java.util.List;

@Service
public class UserInfoService {
    @Autowired
    UserInfoDao userInfoDao;

    @Transactional(rollbackFor = {Exception.class})
    public boolean add(UserInfo userInfo) {
        userInfo = userInfoDao.save(userInfo);
        boolean rs = userInfo.getId() > 0;
        return rs;
    }

    public UserInfo getOne(Integer id) {
        return userInfoDao.getOne(id);
    }

    public List<UserInfo> getAll() {
        return userInfoDao.findAll();
    }

}
