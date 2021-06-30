package com.csi.emphome.demo.service.employee.impl;
import com.csi.emphome.demo.domain.employee.EmployeeItem;
import com.csi.emphome.demo.repository.employee.EmployeeRepository;
import com.csi.emphome.demo.service.employee.EmployeeService;
import com.csi.emphome.demo.service.employee.dto.EmployeeListQuery;
import com.csi.emphome.demo.service.employee.dto.EmployeeTemp;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 员工管理后台处理
 * @author 老实敦厚的小刘
 * @date 2021/6/18
 */

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     *初始化分页，仅在页面最初生成时使用
     * @param data 表单页号和限制行数
     * @return 单页列表
     */
    @Override
    public HashMap<String, Object> fetchListFunc(EmployeeListQuery data) {
        HashMap<String, Object> responseData = new HashMap<>();
        List<EmployeeItem> list = employeeRepository.findAll(PageRequest.of(data.getPage()-1, data.getLimit())).toList();
        responseData.put("total",employeeRepository.count());
        responseData.put("items",list);
        System.out.println(list);
        HashMap<String, Object> response = new HashMap<>();
        response.put("code",20000);
        response.put("data",responseData);
        return response;
    }

    /**
     * 增加用户
     * @param data 前端页面增加用户功能传入数据
     * @return 成功或失败参数
     */
    @Override
    public HashMap<String, Object> createListItemFunc(EmployeeTemp data) {
        int resCode = 20001;
        String resData = "failed";
        System.out.println(data.toString());
        EmployeeItem tag_item = employeeRepository.findByid(data.getId());
        if (tag_item == null){
            EmployeeItem temp_item = new EmployeeItem(data.getId(),data.getName(),data.getCard(),data.getSex(),data.getJobid(),data.getEducation(),data.getEmail(),data.getTel(),data.getPhone(),
                    data.getParty(),data.getQqnum(),data.getAddress(),data.getPost(),data.getBirthday(),data.getRace(),data.getSpeciality(),data.getHobby(),data.getRemark(),data.getDeptid());
            employeeRepository.save(temp_item);
            System.out.println(temp_item.toString());
            System.out.println("hello");
            resCode = 20000;
            resData = "success";
        }

        HashMap<String, Object> response = new HashMap<>();
        response.put("code",resCode);
        response.put("data",resData);
        return response;
    }

    /**
     * 删除用户
     * @param data 前端页面删除用户功能传入数据
     * @return 成功或失败参数
     */
    @Override
    public HashMap<String, Object> deleteListItemFunc(EmployeeTemp data) {
        int resCode = 20001;
        String resData = "failed";

        EmployeeItem tag_item = employeeRepository.findByid(data.getId());
        if (tag_item != null){
            System.out.println(tag_item.toString());
            employeeRepository.delete(tag_item);
            resCode = 20000;
            resData = "success";
        }

        HashMap<String, Object> response = new HashMap<>();
        response.put("code",resCode);
        response.put("data",resData);
        return response;
    }

    /**
     * 更新用户数据
     * @param data 前端页面编辑用户功能传入数据
     * @return 成功或失败参数
     */
    @Override
    public HashMap<String, Object> updateListItemFunc(EmployeeTemp data) {
        int resCode = 20001;
        String resData = "failed";

        EmployeeItem tag_item = employeeRepository.findByid(data.getId());
        if (tag_item != null){
            tag_item.setName(data.getName());
            tag_item.setSex(data.getSex());
            tag_item.setPhone(data.getPhone());
            tag_item.setEmail(data.getEmail());
            tag_item.setJobid(data.getJobid());
            tag_item.setEducation(data.getEducation());
            tag_item.setCard(data.getCard());
            tag_item.setDeptid(data.getDeptid());
            tag_item.setAddress(data.getAddress());
            tag_item.setCreatedate(data.getCreatedate());
            System.out.println(tag_item.toString());
            employeeRepository.save(tag_item);
            resCode = 20000;
            resData = "success";
        }

        HashMap<String, Object> response = new HashMap<>();
        response.put("code",resCode);
        response.put("data",resData);
        return response;
    }

    /**
     * 查询用户，初始化后分页功能
     * @param data 前端页面查询用户功能传入数据
     * @param query 表单页号和限制行数
     * @return 符合条件的单页用户列表
     */
    @Override
    public HashMap<String, Object> searchListItemFunc(EmployeeTemp data,EmployeeListQuery query) {
        HashMap<String, Object> responseData = new HashMap<>();
        int resCode = 20001;
        String resData = "failed";

//        EmployeeItem tag_item = employeeRepository.findByjobidAndnameAndcardAndsexAndPhoneAnddeptid(data.getJobid(),data.getName(),data.getCard(),data.getSex(),data.getPhone(),data.getDeptid());
        System.out.println("hello world!!!");
        EmployeeItem test = new EmployeeItem(data.getDeptid(),data.getJobid(),data.getName(),data.getCard(),data.getPhone(),data.getSex());
        List<EmployeeItem> list = getDataIllustrationList(test,query).toList();
        System.out.println(list);
        System.out.println("hello,world!!!");

        if (list != null){
            responseData.put("total",employeeRepository.count());
            responseData.put("items",list);
            HashMap<String, Object> response = new HashMap<>();
            response.put("code",20000);
            response.put("data",responseData);
            return response;
        }

        HashMap<String, Object> response = new HashMap<>();
        response.put("code",resCode);
        response.put("data",resData);
        return response;
    }

    /**
     * JPA联合查询函数
     * @param test 查询数据
     * @param data 表单页号和限制行数
     * @return 符合条件的单页用户列表
     */
    public Page<EmployeeItem> getDataIllustrationList(EmployeeItem test,EmployeeListQuery data) {
        Page<EmployeeItem> test2 = employeeRepository.findAll(new Specification<EmployeeItem>(){
            @Override
            public Predicate toPredicate(Root<EmployeeItem> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();

                if(test.getJobid()!=0){
                    predicates.add(cb.equal(root.get("jobid"), test.getJobid()));
                }

                if(StringUtils.isNotBlank(test.getName())){
                    predicates.add(cb.like(root.get("name"), "%"+test.getName()+"%" ));
                }
                if(StringUtils.isNotBlank(test.getCard())){
                    predicates.add(cb.like(root.get("card"), "%"+test.getCard()+"%"  ));
                }
                if(test.getSex()!=0){
                    predicates.add(cb.equal(root.get("sex"), test.getSex() ));
                }
                if(StringUtils.isNotBlank(test.getPhone())){
                    predicates.add(cb.like(root.get("phone"), "%"+test.getPhone()+"%"  ));
                }
                if(test.getDeptid()!=0){
                    predicates.add(cb.equal(root.get("deptid"), test.getDeptid() ));
                }

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));  //将上面满足条件的项用and拼接起,来进行查询，当然此处也可以改为or或者like等等，视情况而定
            }

        }, PageRequest.of(data.getPage()-1, data.getLimit()));
        return test2;
    }
}
