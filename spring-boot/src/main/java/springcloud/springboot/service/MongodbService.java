package springcloud.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import springcloud.springboot.entity.UserInfo;

import java.util.List;

@Service
public class MongodbService {
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private MongoTemplate mongoTemplate;

    public boolean save() {
        List<UserInfo> list = userInfoService.getAll();
        for (UserInfo user : list) {
            mongoTemplate.save(user);
        }
        return true;
    }

    public List<UserInfo> getAll() {
        List<UserInfo> all = mongoTemplate.findAll(UserInfo.class);
        return all;
    }

    public List<UserInfo> search() {
        Query query = new Query(Criteria.where("name").is("小红").andOperator(Criteria.where("id").is(3)));
        List<UserInfo> list = mongoTemplate.find(query, UserInfo.class);
        return list;
    }
}
