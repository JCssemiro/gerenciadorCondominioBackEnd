package cassemiro.juan.seucondominio.services;


import cassemiro.juan.seucondominio.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login){
        return funcionarioRepository.findByEmail(login).orElseThrow(()-> new UsernameNotFoundException("Usuário não encontrado!"));
    }
}
