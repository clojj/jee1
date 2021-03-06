package com.airhacks.workshops.business.mybusiness.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name = "CHILD_ENTITY")
public class ChildEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "child_entity_seq")
    @SequenceGenerator(name = "child_entity_seq", sequenceName = "CHILD_ENTITY_ID_SEQ", allocationSize = 1)
    private long id;

    private String field1;
    private String field2;

    @Lob
    private byte[] mybytes;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public byte[] getMybytes() {
        return mybytes;
    }

    public void setMybytes(byte[] mybytes) {
        this.mybytes = mybytes;
    }

    @Override
    public String toString() {
        return "ChildEntity{" +
                "id=" + id +
                ", field1='" + field1 + '\'' +
                ", field2='" + field2 + '\'' +
                ", mybytes=" + Arrays.toString(mybytes) +
                '}';
    }
}
