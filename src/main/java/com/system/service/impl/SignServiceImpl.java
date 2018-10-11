package com.system.service.impl;

import com.system.dao.SignMapper;
import com.system.pojo.Sign;
import com.system.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SignServiceImpl implements SignService {
    @Autowired
    private SignMapper signMapper;

    @Override
    public int getCountSign(String id) throws Exception {
        return signMapper.countById(id);
    }

    public List<Sign> findByPaging(Map<String, Object> paramMap) throws Exception {
        List<Sign> list = signMapper.findByPaging(paramMap);
        return list;
    }

    public void signIn(Sign sign) {
        signMapper.insert(sign);
    }

    @Override
    public int isSignIn(Sign signOut) {
        int isSignIn = 0;
        try {
            isSignIn = signMapper.isSignIn(signOut);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSignIn;
    }

    public void signOut(Sign sign) throws Exception {
        signMapper.signOut(sign);
    }

    @Override
    public List<Sign> showSignRecord(Map<String, Object> paramMap) throws Exception{
        return signMapper.showSignRecord(paramMap);
    }

    @Override
    public int getDepartmentSignCount(Map<String, Object> paramMap) throws Exception {
        return signMapper.departmentSignCount(paramMap);
    }

}
