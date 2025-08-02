package br.com.fiap.foodtech.foodtech.validation;

import br.com.fiap.foodtech.foodtech.entities.Cliente;
import br.com.fiap.foodtech.foodtech.entities.Gestor;
import br.com.fiap.foodtech.foodtech.entities.Usuario;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Component
public class TipoUsuarioValidator {

    private static final String MENSAGEM_EXCECAO_CLIENTE = "esse tipo de usuário não pode ser salvo em Cliente";
    private static final String MENSAGEM_EXCECAO_GESTOR = "esse tipo de usuário não pode ser salvo em Gestor";


    public String validandoTipoUsuario(Usuario usuario) {
        if (usuario instanceof Cliente) {
            String retornaTipoUsuarioCliente = validandoTipoUsuarioCliente(usuario.getTipoUsuario());
            return retornaTipoUsuarioCliente;
        }else if(usuario instanceof Gestor){
            String retornaTipoUsuarioGestor = validandoTipoUsuarioGestor(usuario.getTipoUsuario());
            return retornaTipoUsuarioGestor;
        }
        return null;
    }


    private String validandoTipoUsuarioCliente(String tipoUsuario) {
        if(!tipoUsuario.toUpperCase().equals("CLIENTE")) {
            throw new DataIntegrityViolationException(MENSAGEM_EXCECAO_CLIENTE);
        }
        return tipoUsuario.toUpperCase();
    }

    private String validandoTipoUsuarioGestor(String tipoUsuario) {
        if(!tipoUsuario.replaceAll(" ","").toUpperCase().equals("DONODERESTAURANTE")) {
            throw new DataIntegrityViolationException(MENSAGEM_EXCECAO_GESTOR);
        }
        return tipoUsuario.replaceAll(" ", "").toUpperCase();
    }
}
