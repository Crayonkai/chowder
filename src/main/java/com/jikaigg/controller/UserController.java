package com.jikaigg.controller;

import com.jikaigg.common.Result;
import com.jikaigg.domain.UserVo;
import com.jikaigg.orm.domain.BizUserDto;
import com.jikaigg.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/getOne")
    public Result getUser(@RequestBody UserVo userVo) {
        log.debug("userVo is : {}", userVo);
        List<BizUserDto> userDtos = null;
        String username = userVo.getUsername();
        if (StringUtils.isNotBlank(username))
            userDtos = userService.getOne(username);
        if (CollectionUtils.isEmpty(userDtos))
            return Result.fail("9999", "查询用户信息为空");
        return Result.ok(userDtos.get(0));

    }

}
