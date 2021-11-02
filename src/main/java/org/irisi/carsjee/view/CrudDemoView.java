package org.irisi.carsjee.view;

import org.irisi.carsjee.bean.Car;
import org.irisi.carsjee.controller.CarController;
import org.primefaces.PrimeFaces;
import org.primefaces.event.ToggleEvent;
import org.irisi.carsjee.bean.Car;
//import org.irisi.carsjee.service.OrderService;
import org.irisi.carsjee.service.CarFacade;
import org.primefaces.model.Visibility;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Named
@ViewScoped
public class CrudDemoView implements Serializable {
    
    private List<Car> cars;

    private Car selectedCar;

    private List<Car> selectedCars;

    @Inject
    private CarController carController;

//    @Inject
//    private OrderService orderService;

    @PostConstruct
    public void init() {
        this.cars = this.carController.getItems();
//        this.cars = null;
    }
    
    public List<Car> getCars() {
        return cars;
    }

    public Car getSelectedCar() {
        return selectedCar;
    }

    public void setSelectedCar(Car selectedCar) {
        this.selectedCar = selectedCar;
    }

    public List<Car> getSelectedCars() {
        return selectedCars;
    }

    public void setSelectedCars(List<Car> selectedCars) {
        this.selectedCars = selectedCars;
    }

    public void openNew() {
        this.selectedCar = new Car();
    }

    public void saveCar() {
        if (this.selectedCar.getMatricule() == null) {
            this.selectedCar.setMatricule(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9));
            this.cars.add(this.selectedCar);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Car Added"));
        }
        else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Car Updated"));
        }
        
        PrimeFaces.current().executeScript("PF('manageCarDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-cars");
    }

    public void deleteCar() {
        this.cars.remove(this.selectedCar);
        this.selectedCar = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Car Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-cars");
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedCars()) {
            int size = this.selectedCars.size();
            return size > 1 ? size + " cars selected" : "1 car selected";
        }

        return "Delete";
    }

    public boolean hasSelectedCars() {
        return this.selectedCars != null && !this.selectedCars.isEmpty();
    }

    public void deleteSelectedCars() {
        this.cars.removeAll(this.selectedCars);
        this.selectedCars = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cars Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-cars");
    }

    public void onRowToggle(ToggleEvent event) {
        if (event.getVisibility() == Visibility.VISIBLE) {
            Car car = (Car) event.getData();
//            if (car.getOrders() == null) {
//                car.setOrders(orderService.getOrders((int) (Math.random() * 10)));
//            }
        }
    }
}