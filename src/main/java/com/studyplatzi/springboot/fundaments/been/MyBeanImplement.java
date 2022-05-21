package com.studyplatzi.springboot.fundaments.been;

public class MyBeanImplement implements MyBean{
    @Override
    public void print() {
        System.out.println("Hola desde my implementación propia del bean");
    }
}
