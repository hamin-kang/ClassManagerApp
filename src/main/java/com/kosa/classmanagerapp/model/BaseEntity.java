package com.kosa.classmanagerapp.model;

public class BaseEntity {
    private Long id;

    public Long getId() {
        return id;

    }

    // int형으로 받아오기
    public int getIdInt() {
        return id != null ? id.intValue() : 0; // Long → int 변환
    }



    public void setId(Long id) {
        this.id = id;
    }
}
