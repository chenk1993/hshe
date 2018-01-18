package com.xt.hshe.core.web.controller;

import com.xt.hshe.core.pojo.HttpMsg;
import com.xt.hshe.core.pojo.Problem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class ProblemController extends BaseController{
    @GetMapping("/p")
    public HttpMsg problems(){
        return new HttpMsg<>(1,null, problemService.findAllProblems());
    }
    @GetMapping("/p/{id}")
    public HttpMsg problem(@PathVariable Long id){
        Problem problem = problemService.find(id);
        if (problem==null){
            return new HttpMsg(0,"不存在的~");
        }
        return new HttpMsg<>(1,null, problemService.find(id));
    }
}
