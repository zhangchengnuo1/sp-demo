package demo.demoproject.service;

import demo.demoproject.dao.UserinfoMapper;
import demo.demoproject.entity.Userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserinfoMapper userinfoMapper;

    public Userinfo getById(Integer id){
        return userinfoMapper.selectByPrimaryKey(id);
    }
    public Userinfo getByuser(String user, String pass) {

        return userinfoMapper.selectByUser(user,pass);
    }
    public Userinfo loginQuery(Userinfo userinfo) {
//        返回dao层的查询结果
        return userinfoMapper.loginQuery(userinfo);
    }
    //    注册

    public int insertQuery(Userinfo userinfo) {
        return userinfoMapper.insertQuery(userinfo);
    }

}
