package dsw.trabalho.SistemaConsultasMedicas.Controllers;


import dsw.trabalho.SistemaConsultasMedicas.Dtos.MedicoRecordDto;
import dsw.trabalho.SistemaConsultasMedicas.Models.Entities.ConsultaModel;
import dsw.trabalho.SistemaConsultasMedicas.Models.Entities.MedicoModel;
import dsw.trabalho.SistemaConsultasMedicas.Models.ValueObjects.Crm;
import dsw.trabalho.SistemaConsultasMedicas.Models.ValueObjects.Email;
import dsw.trabalho.SistemaConsultasMedicas.Repositories.ConsultaRepository;
import dsw.trabalho.SistemaConsultasMedicas.Repositories.MedicoRepository;
import dsw.trabalho.SistemaConsultasMedicas.Service.Spec.IConsultaService;
import dsw.trabalho.SistemaConsultasMedicas.Service.Spec.IMedicoService;
import dsw.trabalho.SistemaConsultasMedicas.Service.impl.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Controller
@RequestMapping(path = "/medico")
public class MedicoController {

    @Autowired
    IMedicoService medico;
    @Autowired
    IConsultaService consulta;

    private final PasswordEncoder encoder;

    public MedicoController(PasswordEncoder encoder, MedicoRepository medicoRepository) { this.encoder = encoder; }

    @GetMapping("/listarConsultas")
    public String listarConsultas(ModelMap model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = null;
        if(principal instanceof UserDetails){
            email = ((UserDetails)principal).getUsername();
        }

        UUID idMedico = medico.getIdByEmail(new Email(email));
        model.addAttribute("consultas", consulta.buscarPorMedico(idMedico));
        return "medico/lista";
    }




}
