package com.studyplatzi.springboot.fundaments.been;

//La implementación es la clase que representa el Bean
public class MyBeanWithPropertiesImplement implements MyBeanWithProperties {

    private  String name;
    private String lastName;

    public MyBeanWithPropertiesImplement(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    @Override
    public String function() {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append(name).append("-").append(lastName).toString();
    }

}
