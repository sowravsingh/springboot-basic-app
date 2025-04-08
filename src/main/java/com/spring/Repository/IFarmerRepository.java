package com.spring.Repository;

import com.spring.DTO.FarmerDto;
import com.spring.DTO.FarmerDto2;
import com.spring.Entities.FarmerDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface IFarmerRepository extends JpaRepository<FarmerDetails,Long> {

    @EntityGraph(attributePaths = "fieldDetailsList")
    public List<FarmerDetails> findFarmerDetailsByName(String farmer);

    public List<FarmerDetails> findFarmerDetailsByNameAndPhone(String name,String phone);

    public Page<FarmerDetails> findFarmerDetailsByNameStartsWith(String name, Pageable pageable);


    @Query("select u from farmer u where u.name = :name and u.phone = :phone")
    public List<FarmerDetails> getFarmerDetailsByNameAndPhone(@Param("name") String name, @Param("phone") String phone);


//    @Query("select f.name ,fl.fieldName from farmer f join f.fieldDetails fl where fl.fieldName =:fieldName")
//    public List<Object[]> getFarmerDetailsByFieldName(@Param("fieldName") String name);

//    @Query("select new com.spring.DTO.FarmerDto(f.name ,fl.fieldName) from farmer f join f.fieldDetails fl where fl.fieldName =:fieldName")
//    public List<FarmerDto> getFarmerDetailsByFieldNameAsDto(@Param("fieldName") String name);

    @Query("select f from farmer f join  f.fieldDetailsList fl where f.name =:farmerName")
    public List<FarmerDetails> getFarmerDetailsByFarmerName(@Param("farmerName") String name);


    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("delete from farmer f where f.name =:name")
    public void deleteFarmerByName(@Param("name") String name);


    @Query(name = "fetchByNameQuery")
    public List<FarmerDetails> findFarmerDetailsByName2(@Param("name") String name);

    @Query(value = "select * from farmer where name =:farmerName", nativeQuery = true)
    public List<FarmerDetails> getFarmerDetailsByNativeQuery(@Param("farmerName") String name);

    @Query(name = "namedNativeQuery", nativeQuery = true)
    public List<FarmerDto2> getFarmerDataByNativeQuery(@Param("farmerName") String name);

    @Query(value = "select name, phone from farmer where name =:farmerName", nativeQuery = true)
    public List<Object[]> getFarmerDataByNativeQuery2(@Param("farmerName") String name);

}
