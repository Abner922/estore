package com.briup.service.impl;


import com.briup.bean.Customer;
import com.briup.dao.CustomerMapper;
import com.briup.service.ICustomerService;
import com.briup.util.MyBatisSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;

/**
 * @author SDX
 * @create 2022-09-22 11:11
 */

public class CustomerServiceImpl implements ICustomerService {
    SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
    CustomerMapper mapper = sqlSession.getMapper(CustomerMapper.class);

    @Override
    public void register(Customer customer) {

        //1.判断数据是否合法
        if(customer.getName() == null || customer.getName().trim().equals("")){
            throw new RuntimeException("用户名不能为空");
        }
        //填空 判断其他数据是否为空
        if(customer.getPassword() == null || customer.getPassword().trim().equals("")){
            throw new RuntimeException("密码不能为空");
        }
        if(customer.getZipCode() == null || customer.getZipCode().trim().equals("")){
            throw new RuntimeException("邮编不能为空");
        }if(customer.getAddress() == null || customer.getAddress().trim().equals("")){
            throw new RuntimeException("地址不能为空");
        }if(customer.getTelephone() == null || customer.getTelephone().trim().equals("")){
            throw new RuntimeException("电话不能为空");
        }if(customer.getEmail() == null || customer.getEmail().trim().equals("")){
            throw new RuntimeException("电子邮箱不能为空");
        }
        //2.调用对应的dao层方法

        //3.判断用户名在数据库中是否存在
        Customer customerByName = mapper.findCustomerByName(customer.getName());
        if(customerByName != null){
            throw new RuntimeException("用户名已存在");
        }
        //4.存入数据库
        mapper.saveCustomer(customer);
        sqlSession.commit();
    }

    @Override
    public Customer findCustomerByName(String name) {
        return null;
    }


    @Override
    public Customer login(String name, String password) {
        //如果没有捕获到异常，则跳转到index.jsp如果捕获到，则跳转到
        if(name == null || name.trim().equals("")){
            throw new RuntimeException("用户名不能为空");
        }
        if(password == null || password.trim().equals("")){
            throw new RuntimeException("密码不能为空");
        }
        Customer customerByName = mapper.findCustomerByName(name);
        if(!customerByName.getPassword().equals(password)){
            throw new RuntimeException("密码错误！");
        }
        return customerByName;
    }
}
