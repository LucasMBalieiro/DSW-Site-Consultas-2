package dsw.trabalho.SistemaConsultasMedicas.Controllers;

import dsw.trabalho.SistemaConsultasMedicas.Dtos.ConsultaRecordDto;
import dsw.trabalho.SistemaConsultasMedicas.Dtos.PacienteRecordDto;
import dsw.trabalho.SistemaConsultasMedicas.Models.Entities.ConsultaModel;
import dsw.trabalho.SistemaConsultasMedicas.Models.Entities.PacienteModel;
import dsw.trabalho.SistemaConsultasMedicas.Models.ValueObjects.Email;
import dsw.trabalho.SistemaConsultasMedicas.Repositories.PacienteRepository;
import dsw.trabalho.SistemaConsultasMedicas.Service.Spec.IConsultaService;
import dsw.trabalho.SistemaConsultasMedicas.Service.Spec.IMedicoService;
import dsw.trabalho.SistemaConsultasMedicas.Service.Spec.IPacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    IPacienteService paciente;

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

    @GetMapping("/listarConsultas")
    public String listarConsultas(ModelMap model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = null;
        if(principal instanceof UserDetails){
            email = ((UserDetails)principal).getUsername();
        }

        UUID idPaciente = paciente.getIdByEmail(new Email(email));
        model.addAttribute("consultas", consulta.buscarPorMedico(idPaciente));
        return "medico/lista";
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
        return "redirect:/paciente/listaConsultas";
    }

    @GetMapping("/editarConsulta/{id}")
    public String preeditarConsulta(@PathVariable("id") UUID id, ModelMap model){
        model.addAttribute("consultas", consulta.buscarPorID(id));
        return "paciente/cadastroConsulta";
    }

    @PostMapping("/editarConsulta")
    public String editarConsulta(@Valid ConsultaRecordDto consultaRecordDto, BindingResult result, RedirectAttributes attr) {
        Integer errors = 0;
        if (result.getFieldError("horarioConsulta") != null)
            errors += 1;


        if (result.getFieldErrorCount() > errors+1) {
            System.out.println("Falhou");

            return "paciente/cadastroConsulta";
        }

        ConsultaModel consulta0 = new ConsultaModel();
        consulta.salvar(consulta0);
        attr.addFlashAttribute("sucess", "Consultado editada com sucesso.");
        return "redirect:/paciente/listaConsulta";
    }

    @GetMapping("/excluirConsulta/{id}")
    public String excluirConsultaPorId(@PathVariable("id") UUID id, RedirectAttributes attr) {
        consulta.excluirPorID(id);
        attr.addFlashAttribute("sucess", "Consulta excluída com sucesso.");
        return "redirect:/paciente/listarConsulta";
    }

}
