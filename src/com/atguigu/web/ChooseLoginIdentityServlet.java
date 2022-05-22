package com.atguigu.web;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChooseLoginIdentityServlet extends BaseServlet{

    protected void loginIdentity(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        //1、获取请求参数
        String text = req.getParameter("text");
        //2、将对应的地址保存在request域中
        if(text.equals("管理员")){
            req.getSession().setAttribute("address",1);
        }else if(text.equals("普通用户")){
            req.getSession().setAttribute("address",2);
        }
        //3、保存map
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("text",text);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);
    }
}
