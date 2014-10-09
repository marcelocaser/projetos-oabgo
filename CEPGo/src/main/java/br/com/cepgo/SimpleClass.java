/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cepgo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author marcelocaser
 */
@RequestScoped
@Named
public class SimpleClass {

    public void doSomething() {
        System.out.println("Consider it done");
    }

    @PostConstruct
    public void initialize() {
        System.out.println("Starting");
    }

    @PreDestroy
    public void stop() {
        System.out.println("Stopping");
    }
}
