package demo.demoproject.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import demo.demoproject.dao.TestMapper;
import demo.demoproject.dao.UserinfoMapper;
import demo.demoproject.entity.Test;
import demo.demoproject.entity.Userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    private TestMapper testMapper ;

    public Test getById(Integer id){

        return testMapper.selectByPrimaryKey(id);
    }

    public PageInfo<Test> SelectAll(int page, int pageSize) {

        PageHelper.startPage(page, pageSize);//改写语句实现分页查询
        List<Test> all = testMapper.selectAll();
        PageInfo<Test> info = new PageInfo<>(all);
        return info;
    }
}