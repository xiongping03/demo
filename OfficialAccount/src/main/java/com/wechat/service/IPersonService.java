package com.wechat.service;

import java.util.List;

import com.wechat.model.Person;

public interface IPersonService {


    /**
     * ����ȫ����person
     * @return
     */
    List<Person> loadPersons();
	
}
