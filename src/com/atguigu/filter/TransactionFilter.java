package com.atguigu.filter;

import com.atguigu.utils.JdbcUtils;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class TransactionFilter implements Filter {

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse,
            final FilterChain filterChain) throws IOException, ServletException {

        try {
            filterChain.doFilter(servletRequest,servletResponse);
            JdbcUtils.commitAndClose();//提交事务
        } catch (Exception e) {
            JdbcUtils.rollbackAndClose();//回滚事务
            e.printStackTrace();
            throw new RuntimeException(e);//把异常抛给Tomcat统一展示友好界面提示
        }

    }

    @Override
    public void destroy() {

    }
}
