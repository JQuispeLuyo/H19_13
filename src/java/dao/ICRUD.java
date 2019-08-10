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
public interface ICRUD<T> {

    void registrar(T modelo) throws Exception;

    void modificar(T modelo) throws Exception;

    void eliminar(T modelo) throws Exception;

    List<T> listar() throws Exception;

}
