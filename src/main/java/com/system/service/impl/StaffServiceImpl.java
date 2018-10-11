package com.system.service.impl;

/*import com.system.mapper.CollegeMapper;*/
import com.system.dao.LeaveMapper;
import com.system.dao.StaffMapper;
/*import com.system.mapper.StudentMapperCustom;*/
import com.system.pojo.*;
import com.system.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * Staff
 */
@Service
public class StaffServiceImpl implements StaffService {
    //使用spring 自动注入
    @Autowired
    private StaffMapper staffMapper;


    @Override
    public Staff findById(String id) throws Exception {
        return staffMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateMassage(Map<String, Object> paramMap) throws Exception {
        staffMapper.updateMassage(paramMap);
    }

    @Override
    public List<String> findAllStaff(int department) throws Exception {
        return  staffMapper.findAllStaff(department);
    }

}
