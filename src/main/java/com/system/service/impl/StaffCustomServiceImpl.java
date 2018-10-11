package com.system.service.impl;

import com.system.dao.StaffCustomMapper;
import com.system.pojo.PagingVO;
import com.system.pojo.Staff;
import com.system.pojo.StaffCustom;
import com.system.service.StaffCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/*import com.system.mapper.StudentMapperCustom;*/


/**
 * Staff
 */
@Service
public class StaffCustomServiceImpl implements StaffCustomService {
    //使用spring 自动注入
    @Autowired
    private StaffCustomMapper staffCustomMapper;

    @Override
    public StaffCustom findById(String id) throws Exception {
        return staffCustomMapper.findById(id);
    }

    @Override
    public int getStaffCount() throws Exception {
        return staffCustomMapper.getStaffCount();
    }

    @Override
    public List<StaffCustom> findByPaging(int toPageNo) throws Exception {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);

        return staffCustomMapper.findByPaging(pagingVO);
    }

    @Override
    public void editStaff(Map<String, Object> paramMap) throws Exception {
        staffCustomMapper.editStaff(paramMap);
    }

    @Override
    public List<StaffCustom> findByName(String findByName) throws Exception {
        return staffCustomMapper.findByName(findByName);
    }

    //移除员工信息
    @Override
    public void removeById(String id) {
        staffCustomMapper.removeById(id);
    }

    //添加员工信息  初试密码为123
    public Boolean save(StaffCustom staffCustom) throws Exception {
        StaffCustom staff = staffCustomMapper.selectByPrimaryKey(staffCustom.getStaffId());
        if (staff == null) {
            staffCustomMapper.insert(staffCustom);
            return true;
        }
        return false;
    }

}
