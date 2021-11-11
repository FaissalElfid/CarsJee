package org.primefaces.freya.view;

import bean.Car;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.Visibility;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import service.CarFacade;

@Named
@ViewScoped
public class CrudDemoView implements Serializable {

    private List<Car> cars;

    private Car selectedCar;

    private List<Car> selectedCars;

    @Inject
    private CarFacade carService;

    @Inject
    private CarFacade carFacade;

    @PostConstruct
    public void init() {
//        this.cars = this.carService.getCars();
        this.cars = this.carFacade.findAll();
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
        if (this.selectedCar.getId() == null) {
//            this.cars.add(this.selectedCar);
            System.out.println(selectedCar.getMatricule() + " " + selectedCar.getDateAchat().toString());
            this.carFacade.create(selectedCar);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Car Added"));
        } else {
            this.carFacade.edit(selectedCar);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Car Updated"));
        }

        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-cars");
    }

    public void deleteCar() {
        this.carFacade.remove(selectedCar);
        this.selectedCar = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Car Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-cars");
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedCars()) {
            int size = this.selectedCars.size();
            return size > 1 ? size + " carss selected" : "1 Car selected";
        }

        return "Delete";
    }

    public boolean hasSelectedCars() {
        return this.selectedCars != null && !this.selectedCars.isEmpty();
    }

    public void deleteSelectedCars() {
        this.carFacade.removeAll(this.selectedCars);
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
