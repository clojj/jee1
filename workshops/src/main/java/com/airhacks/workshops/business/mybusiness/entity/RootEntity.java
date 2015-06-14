package com.airhacks.workshops.business.mybusiness.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name = "ROOT_ENTITY")
public class RootEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "root_entity_seq")
    @SequenceGenerator(name = "root_entity_seq", sequenceName = "ROOT_ENTITY_ID_SEQ", allocationSize = 1)
    private long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="ROOT_ID", referencedColumnName="ID")
    private Set<ChildEntity> childEntities = new HashSet<ChildEntity>(0);

    private String field1;
    private String field2;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<ChildEntity> getChildEntities() {
        return childEntities;
    }

    public void setChildEntities(Set<ChildEntity> childEntities) {
        this.childEntities = childEntities;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }
}
