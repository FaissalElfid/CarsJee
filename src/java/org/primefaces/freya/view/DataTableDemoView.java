/**
 *  Copyright 2009-2020 PrimeTek.
 *
 *  Licensed under PrimeFaces Commercial License, Version 1.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  Licensed under PrimeFaces Commercial License, Version 1.0 (the "License");
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.primefaces.freya.view;

import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.freya.domain.Customer;
import org.primefaces.freya.service.CustomerService;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

@Named
@ViewScoped
public class DataTableDemoView implements Serializable {

    private List<Customer> customers1;

    private List<Customer> customers2;

    private Customer selectedCustomer;

    private List<Customer> selectedCustomers;

    @Inject
    private CustomerService service;

    @PostConstruct
    public void init() {
        this.customers1 = this.service.getCustomers(50);
        this.customers2 = this.service.getCustomers(50);
    }
    
    public List<Customer> getCustomers1() {
        return customers1;
    }

    public List<Customer> getCustomers2() {
        return customers2;
    }

    public List<Customer> getSelectedCustomers() {
        return selectedCustomers;
    }

    public void setSelectedCustomers(List<Customer> selectedCustomers) {
        this.selectedCustomers = selectedCustomers;
    }

    public Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    public void setSelectedCustomer(Customer selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }
}