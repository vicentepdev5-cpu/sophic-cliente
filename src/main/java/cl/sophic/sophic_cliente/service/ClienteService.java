package cl.sophic.sophic_cliente.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.sophic.sophic_cliente.model.Cliente;
import cl.sophic.sophic_cliente.repository.ClienteRepository;

@Service
public class ClienteService {

    //Creación del objeto para usar en los metodos y que llama al repository
    @Autowired
    private ClienteRepository clienteRepo; 

    //Metodo que retorna la lista de clientes
    public List<Cliente> listaClientes(){
        return clienteRepo.findAll(); 
    }

    //Metodo que guarda un cliente en la bd
    public Cliente guardarCliente(Cliente nuevoCliente){
        return clienteRepo.save(nuevoCliente);
    }

    //Metodo que busca un cliente por id en la bd
    public Cliente buscarClientePorId(Integer id){
        return clienteRepo.findById(id).orElse(null);
    }

    //Metodo que elimina a un cliente en la bd mediante el id 
    public void eliminarCliente(Integer id){
        clienteRepo.deleteById(id);
    }

    //Metodo que modifica un cliente en la bd
    //Funciona como metodo PUT (Distinto de PATCH ya que este actualiza todo, el PATCH actualiza algo en especifico)
    public Cliente modificarCliente(Cliente clienteModificado){
        return clienteRepo.save(clienteModificado);
    }

}
