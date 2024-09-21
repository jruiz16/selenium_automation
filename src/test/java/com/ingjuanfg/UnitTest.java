package com.ingjuanfg;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitTest {

    @BeforeAll
    public static void antesQueTodo(){
        System.out.println("Antes que todo");
    }

    @BeforeEach
    public void antesCadaUno(){
        System.out.println("Antes cada Metodo");
    }

    @Test
    public void testSuma(){
        int a = 1;
        int b = 2;
        int result;
        result = a + b;
        assertEquals(3, result);
    }

    @Test
    public void resta(){
        //Arrange
        int temp;
        Calculadora calculadora = new Calculadora();
        //Act
        temp = calculadora.getNumber();
        //Assert
        assertEquals(2, temp);
    }

    @AfterAll
    public static void despuesQueTodo(){
        System.out.println("Despues que todo");
    }

    @AfterEach
    public void despuesCadaUno(){
        System.out.println("Despues cada Metodo");
    }

}
