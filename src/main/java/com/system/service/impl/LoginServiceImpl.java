package com.system.service.impl;

import com.system.dao.StaffMapper;
import com.system.pojo.Staff;
import com.system.pojo.StaffExample;
import com.system.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jacey on 2017/6/29.
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private StaffMapper staffMapper;


    public Staff findById(String id) throws Exception {
        StaffExample staffExample = new StaffExample();

        StaffExample.Criteria criteria = staffExample.createCriteria();
        criteria.andStaffIdEqualTo(id);

        List<Staff> list = staffMapper.selectByExample(staffExample);

        return list.get(0);
    }

    public void save(Staff staff) throws Exception {
        staffMapper.insert(staff);
    }

    public void removeById(String id) throws Exception {
        StaffExample staffExample = new StaffExample();

        StaffExample.Criteria criteria = staffExample.createCriteria();
        criteria.andStaffIdEqualTo(id);

        staffMapper.deleteByExample(staffExample);
    }

    @Override
    public void updateById(String id, Staff staff) {
        StaffExample staffExample = new StaffExample();

        StaffExample.Criteria criteria = staffExample.createCriteria();
        criteria.andStaffIdEqualTo(id);

        staffMapper.updateByExample(staff, staffExample);
    }

}
