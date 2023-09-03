package dsw.trabalho.SistemaConsultasMedicas.Controllers;

import dsw.trabalho.SistemaConsultasMedicas.Dtos.ConsultaRecordDto;
import dsw.trabalho.SistemaConsultasMedicas.Models.Entities.ConsultaModel;
import dsw.trabalho.SistemaConsultasMedicas.Repositories.PacienteRepository;
import dsw.trabalho.SistemaConsultasMedicas.Service.Spec.IConsultaService;
import dsw.trabalho.SistemaConsultasMedicas.Service.Spec.IMedicoService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Controller
@RequestMapping("/paciente")
public class PacienteController {


    @Autowired
    IMedicoService medico;

    @Autowired
    IConsultaService consulta;

    private final PasswordEncoder encoder;

    //Precisa disso??
    public PacienteController(PasswordEncoder encoder, PacienteRepository pacienteRepository) {
        this.encoder = encoder;
    }


    @GetMapping("/listarMedicos")
    public String listaMedicos(ModelMap model){
        model.addAttribute("medico", medico.buscarTodos());
        return "paciente/listaMedicos";
    }

    @GetMapping("/listarMedicos/{especialidade}")
    public String listaMedicosEspecialidade(ModelMap model, @PathVariable("especialidade") String especialidade){
        model.addAttribute("medico", medico.buscarPorEspecialidade(especialidade));
        return "paciente/listaMedicos";
    }

    @GetMapping("/listarConsultas/{id}")
    public String listaConsultas(ModelMap model, @PathVariable("id") UUID id){
        model.addAttribute("consulta", consulta.buscarPorPaciente(id));
        return "paciente/listaConsultas";
    }

    @GetMapping("/cadastrarConsulta")
    public String cadastraConsulta(){
        return "/paciente/cadastroConsulta";
    }

    @PostMapping("/salvarConsulta")
    public String salvaConsulta(@Valid ConsultaRecordDto consultaRecordDto, BindingResult result, RedirectAttributes attributes){

        if(result.hasErrors())
            return "paciente/cadastroConsulta";

        ConsultaModel consulta0 = new ConsultaModel();
        BeanUtils.copyProperties(consultaRecordDto, consulta0);
        attributes.addFlashAttribute("sucess", "{consulta.sucess}");
        consulta.salvar(consulta0);
        return "redirect:/paciente/listaMedicos";
    }

}
