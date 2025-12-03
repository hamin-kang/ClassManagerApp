package com.kosa.classmanagerapp.dao;

import com.kosa.classmanagerapp.model.Notice;

import java.util.List;

public interface NoticeMapper {
    List<Notice> findAll();
}
