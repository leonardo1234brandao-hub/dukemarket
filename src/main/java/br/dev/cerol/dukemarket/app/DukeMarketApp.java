/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.dev.cerol.dukemarket.app;

import br.dev.cerol.dukemarket.bean.Produto;
import br.dev.cerol.dukemarket.dao.ProdutoDAO;
import java.sql.SQLException;

/**
 *
 * @author sesi2dib
 */
public class DukeMarketApp {
    
    public static void main(String[] args) throws SQLException {
        Produto pAux;
        ProdutoDAO pDAO = new ProdutoDAO();

        pAux = new Produto();
        pAux.setCodBarras("11111111");
        pAux.setDescricao("Mouse Logitec Bluetooth");
        pAux.setQtd(50);
        pAux.setValorCompra(80.00);
        pAux.setValorVenda(167.00);

        pDAO.create(pAux);


        pAux = new Produto();
        pAux.setCodBarras("22222222");
        pAux.setDescricao("Teclado USB Gamer");
        pAux.setQtd(80);
        pAux.setValorCompra(104.00);
        pAux.setValorVenda(220.00);

        pDAO.create(pAux);


        pAux = new Produto();
        pAux.setCodBarras("33333333");
        pAux.setDescricao("Microcomputador Dell");
        pAux.setQtd(110);
        pAux.setValorCompra(2400.00);
        pAux.setValorVenda(3200.00);

        pDAO.create(pAux);

        pDAO.getResults().forEach(p -> {System.out.println(p.toString());});
        
        pDAO.delete(3);
        
        pDAO.getResults() .forEach(p -> {
            System.out.println(p.toString());
        });
        
        pAux = pDAO.getResultById(1);
        pAux.setQtd(100);
        pDAO.update(pAux);
        
        
        pDAO.getResults() .forEach(p -> {
            System.out.println(p.toString());
        });  
    }
}
