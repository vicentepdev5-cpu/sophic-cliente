package cl.sophic.sophic_cliente.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cl.sophic.sophic_cliente.model.Cliente;
import cl.sophic.sophic_cliente.service.ClienteService;

@RestController
public class ClienteController {

    @Autowired
    private ClienteService clienteSer;


    @GetMapping("/clientes")
    @ResponseBody
    public ResponseEntity<List<Cliente>> retornaListaDeClientes(@RequestHeader("X-Rol-Empleado")String rol){
        if(!rol.equals("1") && !rol.equals("2")){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); //Retorna codigo 403 por si no tiene permisos
        }
    
        try{
            List<Cliente> clientes = clienteSer.listaClientes();
            return ResponseEntity.ok(clientes); //Retorna el codigo 200 con la lista completa
        }catch(Exception e){
            return ResponseEntity.internalServerError().build(); //Retorna el codigo 500 si falla la peticion
        }
    }

    @PostMapping("/clientes")
    @ResponseBody
    public ResponseEntity <Cliente> crearUnCliente(@RequestHeader ("X-Rol-Empleado")String rol, @RequestBody Cliente nuevoCliente){
        if(!rol.equals("1") && !rol.equals("2")){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        try{
            Cliente clienteGuardado = clienteSer.guardarCliente(nuevoCliente);
            return ResponseEntity.ok(clienteGuardado);
        }catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity <Cliente> modificarTodosLosDatosDeUnClientePorId(@RequestHeader("X-Rol-Empleado") String rol, @RequestBody Cliente clienteModificado, @PathVariable Integer id){
        if(!rol.equals("1") && !rol.equals("2")){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        try{
            clienteModificado.setId(id);
            Cliente clienteCambiado = clienteSer.modificarCliente(clienteModificado);
            return ResponseEntity.ok(clienteCambiado);
        }catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }

    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity <Void> eliminaUnClientePorId(@RequestHeader("X-Rol-Empleado") String rol, @PathVariable Integer id){
        if(!rol.equals("1") && !rol.equals("2")){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        try{
            clienteSer.eliminarCliente(id);
            return ResponseEntity.ok().build(); //Retorna codigo 200 indicando que se elimino correctamente
        }catch(Exception e){
            return ResponseEntity.internalServerError().build();

        }
    }

}
