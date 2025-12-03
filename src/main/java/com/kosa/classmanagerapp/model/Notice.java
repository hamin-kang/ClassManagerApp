package com.kosa.classmanagerapp.model;

public class Notice extends BaseEntity {
    String content;
    Notice(){}
    public void setContent(String content){
        this.content = content;
    }
    public String getContent(){
        return content;
    }
    @Override
    public String toString() {
        return content;
    }
}
