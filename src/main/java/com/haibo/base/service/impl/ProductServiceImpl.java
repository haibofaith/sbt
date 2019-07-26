package com.haibo.base.service.impl;

import com.haibo.base.entity.UserInfo;
import com.haibo.base.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author:haibo.xiong
 * @date:2019/4/25
 * @description:
 */
@Service
public class ProductServiceImpl implements ProductService{
    @Qualifier("jdbcProductService")
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<UserInfo> getList() {
        String sql = "select id,name,sex from t_2";
        return (List<UserInfo>) jdbcTemplate.query(sql, new RowMapper<UserInfo>(){
            @Override
            public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                UserInfo userInfo = new UserInfo();
                userInfo.setUserId(rs.getInt("id"));
                userInfo.setUserName(rs.getString("name"));
                return userInfo;
            }
        });
    }

    @Qualifier("jdbcUserService")
    @Autowired
    private JdbcTemplate jdbcTemplate2;

    @Override
    public Integer getSumSalary() {
        String sql = "select sum(salary) sumcount from jeecg_demo";
        return jdbcTemplate2.queryForObject(sql,Integer.class);
    }
}
