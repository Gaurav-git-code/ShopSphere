package com.darknightcoder.security;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class HeaderMutatingRequest  extends HttpServletRequestWrapper {

    private final String userId;
    private final String role;

    public HeaderMutatingRequest(HttpServletRequest request,
                                 String userId,
                                 String role){
        super(request);
        this.userId = userId;
        this.role = role;
    }

    @Override
    public String getHeader (String name){
       if ("X-User-Id".equalsIgnoreCase(name)) return userId;
       if ("X-User-Role".equalsIgnoreCase(name)) return role;
       return super.getHeader(name);
    }

    @Override
    public Enumeration<String> getHeaders(String name){
        if ("X-User-Id".equalsIgnoreCase(name)){
            return Collections.enumeration(List.of(userId));
        }
        if ("X-User-Role".equalsIgnoreCase(name)){
            return Collections.enumeration(List.of(role));
        }
        return super.getHeaders(name);
    }

    @Override
    public Enumeration<String> getHeaderNames (){
        List<String> names = Collections.list(super.getHeaderNames());
        names.add("X-User-Id");
        names.add("X-User-Role");
        return Collections.enumeration(names);
    }

}
