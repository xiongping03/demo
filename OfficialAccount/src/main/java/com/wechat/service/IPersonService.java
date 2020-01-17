package com.wechat.service;

import java.util.List;

import com.wechat.model.Person;

public interface IPersonService {


    /**
     * 加载全部的person
     * @return
     */
    List<Person> loadPersons();
	
}
