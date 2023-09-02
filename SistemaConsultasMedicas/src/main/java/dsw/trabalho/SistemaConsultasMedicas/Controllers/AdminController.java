package dsw.trabalho.SistemaConsultasMedicas.Controllers;


import dsw.trabalho.SistemaConsultasMedicas.Dtos.ConsultaRecordDto;
import dsw.trabalho.SistemaConsultasMedicas.Dtos.MedicoRecordDto;
import dsw.trabalho.SistemaConsultasMedicas.Dtos.PacienteRecordDto;
import dsw.trabalho.SistemaConsultasMedicas.Models.Entities.ConsultaModel;
import dsw.trabalho.SistemaConsultasMedicas.Models.Entities.MedicoModel;
import dsw.trabalho.SistemaConsultasMedicas.Models.Entities.PacienteModel;
import dsw.trabalho.SistemaConsultasMedicas.Repositories.ConsultaRepository;
import dsw.trabalho.SistemaConsultasMedicas.Repositories.MedicoRepository;
import dsw.trabalho.SistemaConsultasMedicas.Repositories.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    ConsultaRepository consultaRepository; //ponto de injecao

    @Autowired
    MedicoRepository medicoRepository; //ponto de injecao

    @Autowired
    PacienteRepository pacienteRepository; //ponto de injecao

    private final PasswordEncoder encoder;

    public AdminController(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @GetMapping("/listarPaciente")
    public String listarPaciente(ModelMap model){
        model.addAttribute("paciente", pacienteRepository.findAll());
        return "admin/listaPaciente";
    }
    @GetMapping("/cadastrarPaciente")
    public String cadastrarPaciente(){
        return "/admin/cadastroPaciente";
    }

    @PostMapping("/salvarPaciente")
    public String salvarPaciente(@Valid PacienteRecordDto clienteRecordDto, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "admin/cadastroPaciente";
        }

        PacienteModel paciente = new PacienteModel();
        BeanUtils.copyProperties(clienteRecordDto, paciente);
        paciente.setSenha(encoder.encode(paciente.getSenha()));
        pacienteRepository.save(paciente);
        attr.addFlashAttribute("sucess", "Cliente inserido com sucesso");
        return "redirect:/admin/listarPaciente";
    }

    @GetMapping("/editarPaciente/{id}")
    public String preeditarPaciente(@PathVariable("id") UUID id, ModelMap model){
        model.addAttribute("paciente_id", pacienteRepository.findById(id));
        return "admin/cadastroPaciente";
    }

    @PostMapping("/editarPaciente")
    public String editarPaciente(@Valid PacienteRecordDto pacienteRecordDto, BindingResult result, RedirectAttributes attr) {
        Integer errors = 0;
        if (result.getFieldError("CPF") != null)
            errors += 1;
        if (result.getFieldError("email") != null)
            errors += 1;
        if (result.getFieldError("telefone") != null)
            errors += 1;

        if (result.getFieldErrorCount() > errors+1 || result.getFieldError("senha") != null || result.getFieldError("nome") != null || result.getFieldError("sexo") != null || result.getFieldError("dataNascimento") != null || result.getFieldError("papel") != null) {
            System.out.println("Falhou");

            return "admin/cadastroPaciente";
        }
        PacienteModel paciente = new PacienteModel();
        paciente.setSenha(encoder.encode(paciente.getSenha()));
        pacienteRepository.save(paciente);
        attr.addFlashAttribute("sucess", "Cliente editado com sucesso.");
        return "redirect:/admin/listarPaciente";
    }

    @GetMapping("/excluirPaciente/{id}")
    public String excluirPacientePorId(@PathVariable("id") UUID id, RedirectAttributes attr) {
        pacienteRepository.deleteById(id);
        attr.addFlashAttribute("sucess", "Locação excluída com sucesso.");
        return "redirect:/admin/listarPaciente";
    }

    @GetMapping("/listarMedico")
    public String listarMedico(ModelMap model){
        model.addAttribute("medico", medicoRepository.findAll());
        return "admin/listaMedico";
    }

    @GetMapping("/cadastrarMedico")
    public String cadastrarMedico(){
        return "/admin/cadastromedico";
    }

    @PostMapping("/salvarMedico")
    public String salvar(@Valid MedicoRecordDto medicoRecordDto, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "admin/cadastroMedico";
        }

        MedicoModel medico = new MedicoModel();
        BeanUtils.copyProperties(medicoRecordDto, medico);
        medico.setSenha(encoder.encode(medico.getSenha()));
        attr.addFlashAttribute("sucess", "Cliente inserido com sucesso");
        medicoRepository.save(medico);
        return "redirect:/admin/listarMedico";
    }

    @GetMapping("/editarMedico/{id}")
    public String preeditarMedico(@PathVariable("id") UUID id, ModelMap model){
        model.addAttribute("medico_id", medicoRepository.findById(id));
        return "admin/cadastroMedico";
    }

    @PostMapping("/editarMedico")
    public String editarMedico(@Valid MedicoRecordDto medicoRecordDto, BindingResult result, RedirectAttributes attr) {
        Integer errors = 0;
        if (result.getFieldError("CRM") != null)
            errors += 1;
        if (result.getFieldError("email") != null)
            errors += 1;
        if (result.getFieldError("telefone") != null)
            errors += 1;

        if (result.getFieldErrorCount() > errors+1 || result.getFieldError("senha") != null || result.getFieldError("nome") != null || result.getFieldError("sexo") != null || result.getFieldError("dataNascimento") != null || result.getFieldError("papel") != null) {
            System.out.println("Falhou");

            return "admin/cadastroMedico";
        }
        MedicoModel medico = new MedicoModel();
        medico.setSenha(encoder.encode(medico.getSenha()));
        medicoRepository.save(medico);
        attr.addFlashAttribute("sucess", "Cliente editado com sucesso.");
        return "redirect:/admin/listarMedico";
    }

    @GetMapping("/excluirMedico/{id}")
    public String excluirMedicoPorId(@PathVariable("id") UUID id, RedirectAttributes attr) {
        medicoRepository.deleteById(id);
        attr.addFlashAttribute("sucess", "Locação excluída com sucesso.");
        return "redirect:/admin/listarmMedico";
    }

}
