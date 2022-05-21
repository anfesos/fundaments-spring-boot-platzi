package com.studyplatzi.springboot.fundaments.configuration;

import com.studyplatzi.springboot.fundaments.been.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfigurationBean {
    @Bean
    public MyBean beanOperation(){
        //return new MyBeanImplement(); //Se devuelve la implementación del Bean, clase donde se implmento la interfaz
        //Al crear metodos propios a nivel de arquitectura es mucho mejor, ya que con solo cammbiar acá en la interfaz no modifica
        return new MyBeanImplement2();
    }

    @Bean
    public MyOperation beanOperationOperation(){
        return new MyOperationImplement();
    }

    @Bean
    public MyBeanWithDependency beanOperationOperationWithDependency(MyOperation myOperation){
        return new MyBeanWithDependencyImplement(myOperation);
    }
}
