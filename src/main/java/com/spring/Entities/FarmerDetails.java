package com.spring.Entities;


import com.spring.DTO.FarmerDto2;
import org.hibernate.annotations.BatchSize;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity(name = "farmer")
@NamedQuery(name = "fetchByNameQuery", query = "select f from farmer f where f.name =:name")
@NamedNativeQuery(name = "namedNativeQuery",query = "select name, phone from farmer where name =:farmerName",resultSetMapping = "nativeQueryResultSetMapping")
@SqlResultSetMapping(name = "nativeQueryResultSetMapping",
        classes = @ConstructorResult(
                targetClass = FarmerDto2.class,
                columns = {
                        @ColumnResult(name = "name",type = String.class),
                        @ColumnResult(name = "phone",type = String.class)
                }
        )
)
public class FarmerDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    private String phone;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
    @JoinColumn(name = "farmer_id")
    private List<FieldDetails> fieldDetailsList;

    public List<FieldDetails> getFieldDetailsList() {
        return fieldDetailsList;
    }

    public void setFieldDetailsList(List<FieldDetails> fieldDetailsList) {
        this.fieldDetailsList = fieldDetailsList;
    }

    public FarmerDetails(Long id, String name, String phone, List<FieldDetails> fieldDetailsList) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.fieldDetailsList = fieldDetailsList;
    }

    @Override
    public String toString() {
        return "FarmerDetails{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", fieldDetailsList=" + fieldDetailsList +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public FarmerDetails() {
    }


}
