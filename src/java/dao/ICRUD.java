/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author PC
 */
public interface ICRUD<Modelo> {
    
    void registrar(Modelo modelo) throws Exception;

    void modificar(Modelo modelo) throws Exception;

    void eliminar(Modelo modelo) throws Exception;

    List<Modelo> listar() throws Exception;;
    
}
