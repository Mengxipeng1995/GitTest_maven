package com.mxp.serviceImpl;

import com.mxp.mapper.EmpMapper;
import com.mxp.model.Emp;
import com.mxp.services.EmpServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mxp on 2017/7/4.
 */
@Service
public class EmpServicesImpl implements EmpServices {

    @Autowired
    private EmpMapper empmapper;

    @Override
    public List<Emp> getAllEmp() {
        List<Emp> empList = empmapper.selectByExampleWithDept(null);
        return empList;
    }
}
