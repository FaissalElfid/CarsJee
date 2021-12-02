package org.primefaces.freya.domain;

public enum InventoryStatus {
    INSTOCK("V.I.P"),
    OUTOFSTOCK("Old"), 
    LOWSTOCK("New");
 
    private String text;
 
    InventoryStatus(String text) {
        this.text = text;
    }
 
    public String getText() {
        return text;
    }
}