/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicarion.cliente;

import aplication.dao.ClienteDAO;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author unkwow
 */
public class ClienteDAOTeste {
    private ClienteDAO dao= new ClienteDAO();
    public ClienteDAOTeste() {
    }
    
    @Test
    public void testeCFFInd(){
        assertEquals(dao.clienteCPF("111.111.111-11").getNumHabilitacao(),"1235");
    }
    
}
