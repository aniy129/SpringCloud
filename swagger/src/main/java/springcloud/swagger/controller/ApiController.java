package springcloud.swagger.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import springcloud.swagger.model.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping("/api")
@Api(value = "ApiController",description = "Api接口，为第三方提供调用服务",basePath = "/api")
@RestController
public class ApiController {
    public ApiController() {
        users.put(1, new User(1, "小红", new Date()));
    }

    Map<Integer, User> users = Collections.synchronizedMap(new HashMap<>());

    @ApiOperation(value = "计算器", notes = "简单加法计算器")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "x", value = "第一个参数", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "y", value = "第二个参数", required = true, dataType = "int", paramType = "path")
    })
    @RequestMapping(value = "/add/{x}/{y}", method = RequestMethod.GET)
    public int add(
            @PathVariable("x") int x,
            @PathVariable("y") int y) {
        return x + y;
    }

    @ApiOperation(value = "获取用户", notes = "通过id获取一个用户")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "int", paramType = "path")
    @RequestMapping(value = "/getUser/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable("id") int id) {
        return users.get(id);
    }

    @ApiOperation(value = "添加用户", notes = "新增一个用户")
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public User addUser(@RequestBody User user) {
        user.setId(users.keySet().size() + 1);
        users.put(user.getId(), user);
        return user;
    }

    @ApiOperation(value = "用户列表", notes = "获取所有的用户")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<User> list() {
        return new ArrayList<>(users.values());
    }

    @ApiOperation(value = "删除用户", notes = "根据id删除用户")
    @RequestMapping(value = "/list/{id}", method = RequestMethod.DELETE)
    @ApiImplicitParam(value = "用户id", name = "id", required = true, example = "1", type = "int", paramType = "path")
    public boolean delUser(@PathVariable("id") int id) {
        User user = users.get(id);
        if (user == null) {
            return false;
        } else {
            users.remove(id);
            return true;
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {

        //转换日期
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
    }

}
