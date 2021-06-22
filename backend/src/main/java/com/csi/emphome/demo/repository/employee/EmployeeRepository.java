package com.csi.emphome.demo.repository.employee;

import com.csi.emphome.demo.domain.employee.EmployeeItem;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface EmployeeRepository extends JpaRepository<EmployeeItem,Integer>, JpaSpecificationExecutor<EmployeeItem> {
    EmployeeItem findBycard(String card);
    EmployeeItem findByid(int id);
//    EmployeeItem findByjobidAndnameAndcardAndsexAndPhoneAnddeptid(int job,String name,String card,int sex,String phone,int deptid);
//    @Override
//    public List<EmployeeItem> findVenueList(int job,String name,String card,int sex,String phone,int deptid) {
//        Specification<EmployeeItem> specification=new Specification<EmployeeItem>() {
//            @Override
//            public javax.persistence.criteria.Predicate toPredicate(Root<EmployeeItem> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
//                List<Predicate> listAnd=new ArrayList<>(); //组装and语句
//                if(job!=0) {
//                    listAnd.add(criteriaBuilder.equal(root.get("jobid"), job)); //年龄
//                }
//                if(!StringUtils.isBlank(name)) {
//                    listAnd.add( criteriaBuilder.like(root.get("name"), "%" + name));  //姓名 模糊查询
//                }
//                if(!StringUtils.isBlank(card)) {
//                    listAnd.add(criteriaBuilder.like(root.get("card"), card)); //年龄
//                }
//                if(sex!=0) {
//                    listAnd.add(criteriaBuilder.equal(root.get("sex"), "%" + sex + "%")); //出生地 模糊查询
//                }
//                if(!StringUtils.isBlank(phone)) {
//                    listAnd.add(criteriaBuilder.like(root.get("phone"), phone)); //年龄
//                }
//
//                Predicate predicateAnd = criteriaBuilder.and(listAnd.toArray(new Predicate[listAnd.size()])); //AND查询加入查询条件
////                List<Predicate> listOr = new ArrayList<>();///组装or语句
////                if(hobbies!=null && hobbies.length>0) {
////                    for (String hoobbie : hobbies) {
////                        //爱好多选 用OR链接
////                        listOr.add(criteriaBuilder.equal(root.get("hobbie"), hoobbie));
////                    }
////                }
////                Predicate predicateOR = criteriaBuilder.or(listOr.toArray(new Predicate[listOr.size()])); //OR查询加入查询条件
//                return criteriaQuery.where(predicateAnd).getRestriction();
//            }
//        };
//        List<EmployeeItem> list=EmployeeRepository.findAll(specification).toList();
//        return list;
//    }
@Transactional
@Modifying
@Query(value="delete from test where id=?1 ",nativeQuery=true)
    int deleteByPrimaryKey(String id);


    Page<EmployeeItem> findAll(Specification<EmployeeItem> specification, Pageable var2);   //传入Specification对象
}
