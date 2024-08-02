package cassemiro.juan.seucondominio.services;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class MailService {

    @Autowired
    private JavaMailSender emailSender;

    public void enviarEmailRedefinicaoSenha(String dest, String token) throws MessagingException, IOException {
        try {
            MimeMessage emailCliente = emailSender.createMimeMessage();

            MimeMessageHelper helperCliente = new MimeMessageHelper(emailCliente, true, "UTF-8");

            String htmlCliente = lerConteudoHTML("src/main/resources/templates/emailRedefinicaoSenha.html");

            htmlCliente = htmlCliente.replace("{{CODE}}",token);
            helperCliente.setText(htmlCliente, true);
            helperCliente.setTo(dest);
            helperCliente.setSubject("Redefinição de Senha");

            emailSender.send(emailCliente);
        }catch(RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }

    }

    private String lerConteudoHTML(String caminho) throws IOException {
        Path path = Paths.get(caminho);
        byte[] fileBytes = Files.readAllBytes(path);
        return new String(fileBytes, StandardCharsets.UTF_8);
    }

}

