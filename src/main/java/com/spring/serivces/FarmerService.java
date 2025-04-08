package com.spring.serivces;

import com.spring.DTO.FarmerDto;
import com.spring.DTO.FarmerDto2;
import com.spring.Entities.FarmerDetails;
import com.spring.Entities.FieldDetails;
import com.spring.Repository.IFarmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FarmerService {

    @Autowired
    IFarmerRepository farmerRepository;

    @Autowired
    EntityManager entityManager;


    public void saveFarmerDetails(FarmerDetails farmerDetails){
        farmerRepository.save(farmerDetails);
    }

    public FarmerDetails getFarmerDetailsById(Long id){
        return farmerRepository.findById(id).get();
    }

    public List<FarmerDetails> getFarmerDetailsByName(String farmerName){
        return farmerRepository.findFarmerDetailsByName2(farmerName);
    }

    public List<FarmerDetails> getFarmerDetailsByNameAndPhone(String farmerName,String phone){
        return farmerRepository.findFarmerDetailsByNameAndPhone(phone,farmerName);
    }

    public List<FarmerDetails> getFarmerDetailsWithLimit(String name, int limit, int pageNumber){
        Sort sort = Sort.by(Sort.Order.asc("name"),Sort.Order.desc("phone"));
        Pageable pageable=PageRequest.of(pageNumber,limit, sort);
        Page<FarmerDetails> farmerDetails = farmerRepository.findFarmerDetailsByNameStartsWith(name, pageable);
        System.out.println("total pages are "+farmerDetails.getTotalPages());
        System.out.println("total elements are "+farmerDetails.getTotalElements());
        return farmerDetails.getContent();
    }



    public List<FarmerDetails> getFarmerDetailsByNameQuery(String name,String phone){
        return farmerRepository.getFarmerDetailsByNameAndPhone(name, phone);
    }

    public List<FarmerDto> getFarmerDetailsByFieldName(String name){
        List<FarmerDto> farmerDtos = new ArrayList<>();


//        List<Object[]> objectList = farmerRepository.getFarmerDetailsByFieldName(name);
//
//        for (Object[] object: objectList){
//            String farmerName = (String) object[0];
//            String fieldName = (String) object[1];
//
//            FarmerDto farmerDto=  new FarmerDto(farmerName,fieldName);
//            farmerDtos.add(farmerDto);
//        }


        return farmerDtos;
    }

    public void deleteFarmerDataByName(String name){
        farmerRepository.deleteFarmerByName(name);
    }

    public List<FarmerDto> getFarmerDetailsAsDTO(String name){
        //return farmerRepository.getFarmerDetailsByFieldNameAsDto(name);
        return null;
    }


    public List<FarmerDetails> getFarmerDetailsByNativeQuery(String name){
        return farmerRepository.getFarmerDetailsByNativeQuery(name);
    }

    public List<FarmerDto2> getFarmerDTOByNativeQuery(String name){
        return farmerRepository.getFarmerDataByNativeQuery(name);
    }

    public List<FarmerDto2> getFarmerDTOByNativeQuery2(String name){
        List<Object[]> result = farmerRepository.getFarmerDataByNativeQuery2(name);
        return result.stream().map(objects -> new FarmerDto2((String) objects[0],(String) objects[1])).collect(Collectors.toList());

    }


    public List<FarmerDetails> getFarmerDetailsByCriteriaApi(String name){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<FarmerDetails> criteriaQuery = cb.createQuery(FarmerDetails.class);
        Root<FarmerDetails> farmerDetailsRoot= criteriaQuery.from(FarmerDetails.class);

     //   Join<FarmerDetails,FieldDetails> fieldDetailsJoin= farmerDetailsRoot.join("fieldDetailsList",JoinType.INNER);

        criteriaQuery.select(farmerDetailsRoot);
//     criteriaQuery.multiselect(farmerDetailsRoot.get("name"),fieldDetailsJoin.get("fieldName"));

        // for sorting
        criteriaQuery.orderBy(cb.desc(farmerDetailsRoot.get("name")));


        Predicate predicate = cb.equal(farmerDetailsRoot.get("name"),name);

//        Predicate predicate1 =  cb.equal(farmerDetailsRoot.get("name"),name);
//        Predicate predicate2 =  cb.equal(farmerDetailsRoot.get("phone"),"w4343");
//        Predicate finalPredicate =  cb.and(predicate1,predicate2);


        criteriaQuery.where(predicate);

        TypedQuery<FarmerDetails> query= entityManager.createQuery(criteriaQuery);

        query.setFirstResult(0);
        query.setMaxResults(10);

        List<FarmerDetails> resultList = query.getResultList();

        return resultList;


    }
    


    public List<FarmerDto2> getFarmerDetailsByDynamicQuery(String name){

        StringBuilder sb = new StringBuilder("select * from farmer where 1=1");
        List<Object> parameterList = new ArrayList<>();

        if (name != null){
            sb.append(" and name = ? ");
            parameterList.add(name);
        }

        sb.append(" order by name ");
        sb.append(" limit ? offset ?");
        parameterList.add(1);
        parameterList.add(0);

        Query query= entityManager.createNativeQuery(sb.toString());

        int count=1;
        for (Object obj : parameterList){
            query.setParameter(count,obj);
            count++;
        }

        List<Object[]> resultList = query.getResultList();

        return resultList.stream().map(objects -> new FarmerDto2(String.valueOf(objects[0]),String.valueOf(objects[1]))).collect(Collectors.toList());


    }
}
