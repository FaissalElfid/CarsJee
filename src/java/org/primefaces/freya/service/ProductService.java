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
package org.primefaces.freya.service;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.primefaces.freya.domain.InventoryStatus;
import org.primefaces.freya.domain.Product;


@Named
@ApplicationScoped
public class ProductService {
    
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1000, "f230fh0g3", "Bamboo 56", "Car Description", "Mercedes.png", 650000, "Mercedes", 24, InventoryStatus.INSTOCK, 5));
        products.add(new Product(1001, "nvklal433", "Black G5", "Car Description", "Mercedes.png", 720000, "Mercedes", 61, InventoryStatus.INSTOCK, 4));
        products.add(new Product(1002, "zz21cz3c1", "Blue B5", "Car Description", "Renault.png", 790000, "Renault", 2, InventoryStatus.LOWSTOCK, 3));
        products.add(new Product(1003, "244wgerg2", "Blue T4", "Car Description", "Volkswagen.png", 290000, "Volkswagen", 25, InventoryStatus.INSTOCK, 5));
        products.add(new Product(1004, "h456wer53", "T4 2021", "Car Description", "Mercedes.png", 150000, "Mercedes", 73, InventoryStatus.INSTOCK, 4));
        products.add(new Product(1005, "av2231fwg", "Brown Purse", "Car Description", "Mercedes.png", 1200000, "Mercedes", 0, InventoryStatus.OUTOFSTOCK, 4));
        products.add(new Product(1006, "bib36pfvm", "Chakra Bracelet", "Car Description", "Mercedes.png", 320000, "Mercedes", 5, InventoryStatus.LOWSTOCK, 3));
        products.add(new Product(1007, "mbvjkgip5", "E6", "Car Description", "Mercedes.png", 340000, "Mercedes", 23, InventoryStatus.INSTOCK, 5));
        products.add(new Product(1008, "vbb124btr", "G7", "Car Description", "Audi.png", 990000, "Audi", 2, InventoryStatus.LOWSTOCK, 4));
        products.add(new Product(1009, "cm230f032", "Set 87", "Car Description", "Audi.png", 2990000, "Audi", 63, InventoryStatus.INSTOCK, 3));
        products.add(new Product(1010, "plb34234v", "Gold R3", "Car Description", "Mercedes.png", 240000, "Mercedes", 0, InventoryStatus.OUTOFSTOCK, 4));
        products.add(new Product(1011, "4920nnc2d", "Green Earbuds", "Car Description", "Audi.png", 890000, "Audi", 23, InventoryStatus.INSTOCK, 4));
        products.add(new Product(1012, "250vm23cc", "Green T5", "Car Description", "Volkswagen.png", 490000, "Volkswagen", 74, InventoryStatus.INSTOCK, 5));
        products.add(new Product(1013, "fldsmn31b", "Grey T5", "Car Description", "Volkswagen.png", 480000, "Volkswagen", 0, InventoryStatus.OUTOFSTOCK, 3));
        products.add(new Product(1014, "waas1x2as", "H5", "Car Description", "Audi.png", 1750000, "Audi", 8, InventoryStatus.LOWSTOCK, 5));
        products.add(new Product(1015, "vb34btbg5", "Light Green T5", "Car Description", "Volkswagen.png", 490000, "Volkswagen", 34, InventoryStatus.INSTOCK, 4));
        products.add(new Product(1016, "k8l6j58jl", "Lime Band", "Car Description", "Renault.png", 790000, "Renault", 12, InventoryStatus.INSTOCK, 3));
        products.add(new Product(1017, "v435nn85n", "Mini couper", "Car Description", "Volkswagen.png", 850000, "Volkswagen", 42, InventoryStatus.INSTOCK, 4));
        products.add(new Product(1018, "09zx9c0zc", "Painted Case", "Car Description", "Mercedes.png", 560000, "Mercedes", 41, InventoryStatus.INSTOCK, 5));
        products.add(new Product(1019, "mnb5mb2m5", "Band", "Car Description", "Renault.png", 790000, "Renault", 63, InventoryStatus.INSTOCK, 4));
        products.add(new Product(1020, "r23fwf2w3", "Pink T6", "Car Description", "Mercedes.png", 1100000, "Mercedes", 0, InventoryStatus.OUTOFSTOCK, 4));
        products.add(new Product(1021, "pxpzczo23", "Purple Band", "Car Description", "Renault.png", 790000, "Renault", 6, InventoryStatus.LOWSTOCK, 3));
        products.add(new Product(1022, "2c42cb5cb", "Purple Gemstone Necklace", "Car Description", "Mercedes.png", 450000, "Mercedes", 62, InventoryStatus.INSTOCK, 4));
        products.add(new Product(1023, "5k43kkk23", "Purple T5", "Car Description", "Volkswagen.png", 490000, "Volkswagen", 2, InventoryStatus.LOWSTOCK, 5));
        products.add(new Product(1024, "lm2tny2k4", "S5 Smart", "Car Description", "Volkswagen.png", 640000, "Volkswagen", 0, InventoryStatus.INSTOCK, 4));
        products.add(new Product(1025, "nbm5mv45n", "V5", "Car Description", "Volkswagen.png", 780000, "Volkswagen", 52, InventoryStatus.INSTOCK, 4));
        products.add(new Product(1026, "zx23zc42c", "Teal T5", "Car Description", "Volkswagen.png", 490000, "Volkswagen", 3, InventoryStatus.LOWSTOCK, 3));
        products.add(new Product(1027, "acvx872gc", "Yellow E6", "Car Description", "Audi.png", 890000, "Audi", 35, InventoryStatus.INSTOCK, 3));
        products.add(new Product(1028, "tx125ck42", "Yoga Mat", "Car Description", "Renault.png", 200000, "Renault", 15, InventoryStatus.INSTOCK, 5));
        products.add(new Product(1029, "gwuby345v", "Yoga Set", "Car Description", "Renault.png", 200000, "Renault", 25, InventoryStatus.INSTOCK, 8));

        return products;
    }

}