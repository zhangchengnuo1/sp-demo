package demo.demoproject.controller;

import com.github.pagehelper.PageInfo;
import demo.demoproject.entity.Test;
import demo.demoproject.entity.Userinfo;
import demo.demoproject.service.TestService;
import demo.demoproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang3.StringUtils;
import java.util.Random;


@ResponseBody
@RestController
@RequestMapping("/demoproject/test")
public class TestController {

    @Autowired
    private TestService testService ;
    @Autowired
    private UserService userService;
    @GetMapping("/hello")
    public String hello(){
        System.out.println("hello");
        return "hello";
    }

    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    public Test test(@PathVariable Integer id){
        System.out.println("id:"+id);
        return testService.getById(id);
    }
    @RequestMapping(value = "/getuser/{id}",method = RequestMethod.GET)
    public Userinfo test2(@PathVariable Integer id){
        System.out.println("id:"+id);
        return userService.getById(id);
    }
    @ResponseBody
    @RequestMapping(value = "/login}",method = RequestMethod.POST)
    public Userinfo login(@PathVariable String user, String pass){
        System.out.println("id:" + user);
        return userService.getByuser(user,pass);
    }
    //    分页查询
    @ResponseBody
    @RequestMapping("/sa")
    public PageInfo<Test> findAll(int page, int pageSize){

        return  testService.SelectAll(page,pageSize);
    }


    @ResponseBody
    @RequestMapping("/loginUser")
    public Object login(Userinfo userinfo){
//        判断是否为空
        if(StringUtils.isNotBlank(userinfo.getUser()) && StringUtils.isNotBlank(userinfo.getPass())) {
//           不为空，到user中查找
            Userinfo users = userService.loginQuery(userinfo);
//           返回信息
            if(users != null) {
                System.out.println(users);
                return users;
            }else{
                return "fail";
            }
        }else {
            return "fail";
        }
    }
    @RequestMapping("/regist")
    @ResponseBody
    public Object insertQuery(Userinfo userinfo) {
        if(StringUtils.isNotBlank(userinfo.getUser()) && StringUtils.isNotBlank(userinfo.getPass())) {
            //随机生成1000以内整数，用于主键，主键不为空
            Random random = new Random();
            int k = random.nextInt();
            int j = Math.abs(k % 1000);
            userinfo.setId(j);
            int count = userService.insertQuery(userinfo);
            if(count == 1) {
                System.out.println(userinfo);
                return "success";
            }else {
                return "fail";
            }
        }else {
            return "fail";
        }
    }
}