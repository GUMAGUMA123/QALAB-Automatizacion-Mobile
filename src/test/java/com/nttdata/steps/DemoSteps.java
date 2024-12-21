package com.nttdata.steps;

import com.nttdata.screens.DemosScreens;
import org.junit.Assert;

public class DemoSteps {
    DemosScreens demosScreens;

    public void validarProductos(){
        demosScreens.isProductsDisplay();
    }

    public void agregarProducto(int unidades, String producto) {

        demosScreens.seleccionarProducto(unidades,producto);
    }

    public void validarCarritoActualizado() {
        Assert.assertEquals(demosScreens.productoGaleria(), demosScreens.productoCarrito());
    }
}
