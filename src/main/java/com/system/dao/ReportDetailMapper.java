package com.system.dao;

import com.system.pojo.ReportDetail;

import java.util.List;
import java.util.Map;

public interface ReportDetailMapper {
    int deleteByPrimaryKey(Integer detailId);

    int insert(ReportDetail record);

    int insertSelective(ReportDetail record);

    ReportDetail selectByPrimaryKey(Integer detailId);

    int updateByPrimaryKeySelective(ReportDetail record);

    int updateByPrimaryKey(ReportDetail record);

    List<ReportDetail> selectReportDetailData(Map<String,Object> paramMap);
}