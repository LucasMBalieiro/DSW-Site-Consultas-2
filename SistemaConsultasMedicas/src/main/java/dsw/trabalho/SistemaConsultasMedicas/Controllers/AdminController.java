package dsw.trabalho.SistemaConsultasMedicas.Controllers;

import dsw.trabalho.SistemaConsultasMedicas.Dtos.MedicoRecordDto;
import dsw.trabalho.SistemaConsultasMedicas.Dtos.PacienteRecordDto;

import dsw.trabalho.SistemaConsultasMedicas.Models.Entities.MedicoModel;
import dsw.trabalho.SistemaConsultasMedicas.Models.Entities.PacienteModel;


import dsw.trabalho.SistemaConsultasMedicas.Service.Spec.IMedicoService;
import dsw.trabalho.SistemaConsultasMedicas.Service.Spec.IPacienteService;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Controller
@RequestMapping("/admin")
public class AdminController {

//    @Autowired
//    ConsultaRepository consultaRepository; //ponto de injecao

    @Autowired
    IMedicoService medico;//ponto de injecao

    @Autowired
    IPacienteService paciente; //ponto de injecao

    private final PasswordEncoder encoder;

    public AdminController(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @GetMapping("/listarPaciente")
    public String listarPaciente(ModelMap model){
        model.addAttribute("pacientes", paciente.buscarTodos());
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

        PacienteModel paciente0 = new PacienteModel();
        BeanUtils.copyProperties(clienteRecordDto, paciente0);
        paciente0.setSenha(encoder.encode(paciente0.getSenha()));
        paciente.salvar(paciente0);
        attr.addFlashAttribute("sucess", "Cliente inserido com sucesso");
        return "redirect:/admin/listarPaciente";
    }

    @GetMapping("/editarPaciente/{id}")
    public String preeditarPaciente(@PathVariable("id") UUID id, ModelMap model){
        model.addAttribute("paciente", paciente.buscarPorID(id));
        return "admin/cadastroPaciente";
    }

    @PostMapping("/editarPaciente")
    public String editarPaciente(@Valid PacienteModel pacienteRecordDto, BindingResult result, RedirectAttributes attr) {
        Integer errors = 0;
        if (result.getFieldError("CPF") != null)
            errors += 1;
        if (result.getFieldError("email") != null)
            errors += 1;
        if (result.getFieldError("telefone") != null)
            errors += 1;

        if (result.getFieldErrorCount() > errors+1 || result.getFieldError("senha") == null || result.getFieldError("nome") == null || result.getFieldError("sexo") == null || result.getFieldError("dataNascimento") == null || result.getFieldError("papel") == null) {
            System.out.println("Falhou");
            return "admin/cadastroPaciente";
        }
        PacienteModel paciente0 = paciente.buscarPorID(pacienteRecordDto.getUserID());
        if(paciente0.getSenha() != null && !paciente0.getSenha().equals("")){
            paciente0.setSenha(pacienteRecordDto.getSenha());
        }
        if(paciente0.getEmail() != null){
            paciente0.setEmail(pacienteRecordDto.getEmail());
        }
        if(paciente0.getNome() != null && !paciente0.getNome().equals("")){
            paciente0.setNome(pacienteRecordDto.getNome());
        }
        if(paciente0.getCpf() != null){
            paciente0.setCpf(pacienteRecordDto.getCpf());
        }
        if(paciente0.getSexo() != null && !paciente0.getSexo().equals("")){
            paciente0.setSexo(pacienteRecordDto.getSexo());
        }
        if(paciente0.getDataNascimento() != null){
            paciente0.setDataNascimento(pacienteRecordDto.getDataNascimento());
        }
        if(paciente0.getTelefone() != null){
            paciente0.setTelefone(pacienteRecordDto.getTelefone());
        }


        paciente.salvar(paciente0);
        attr.addFlashAttribute("sucess", "Cliente editado com sucesso.");
        return "redirect:/admin/listarPaciente";
    }

    @GetMapping("/excluirPaciente/{id}")
    public String excluirPacientePorId(@PathVariable("id") UUID id, RedirectAttributes attr) {
        paciente.excluirPorID(id);
        attr.addFlashAttribute("sucess", "Locação excluída com sucesso.");
        return "redirect:/admin/listarPaciente";
    }

    @GetMapping("/listarMedico")
    public String listarMedico(ModelMap model){
        model.addAttribute("medicos", medico.buscarTodos());
        return "admin/listaMedico";
    }

    @GetMapping("/cadastrarMedico")
    public String cadastrarMedico(){
        return "/admin/cadastroMedico";
    }

    @PostMapping("/salvarMedico")
    public String salvar(@Valid MedicoRecordDto medicoRecordDto, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "admin/cadastroMedico";
        }

        MedicoModel medico0 = new MedicoModel();
        BeanUtils.copyProperties(medicoRecordDto, medico0);
        medico0.setSenha(encoder.encode(medico0.getSenha()));
        attr.addFlashAttribute("sucess", "Cliente inserido com sucesso");
        medico.salvar(medico0);
        return "redirect:/admin/listarMedico";
    }

    @GetMapping("/editarMedico/{id}")
    public String preeditarMedico(@PathVariable("id") UUID id, ModelMap model){
        model.addAttribute("medico", medico.buscarMedicoPorID(id));
        return "admin/cadastroMedico";
    }

    @PostMapping("/editarMedico")
    public String editarMedico(@Valid @ModelAttribute("medico") MedicoModel medicoRecordDto, BindingResult result, RedirectAttributes attr) {
        Integer errors = 0;
        if (result.getFieldError("CRM") != null)
            errors += 1;
        if (result.getFieldError("email") != null)
            errors += 1;
        if (result.getFieldError("telefone") != null)
            errors += 1;


        System.out.println("\n\n" +
                medicoRecordDto.getEmail() + "\n" +
                medicoRecordDto.getSenha() + "\n" +
                medicoRecordDto.getCrm() + "\n" +
                medicoRecordDto.getEspecialidade() + "\n"
                + "\n\n");

        if (result.getFieldErrorCount() > errors+1 || result.getFieldError("senha") != null || result.getFieldError("nome") != null || result.getFieldError("sexo") != null || result.getFieldError("dataNascimento") != null ){
            System.out.println("Falhou");

            return "admin/cadastroMedico";
        }
        MedicoModel medico0 = medico.buscarMedicoPorID(medicoRecordDto.getUserID());
        if(medico0.getSenha() != null && !medico0.getSenha().equals("")){
            medico0.setSenha(medicoRecordDto.getSenha());
        }
        if(medico0.getEmail() != null){
            medico0.setEmail(medicoRecordDto.getEmail());
        }
        if(medico0.getNome() != null && !medico0.getNome().equals("")){
            medico0.setNome(medicoRecordDto.getNome());
        }
        if(medico0.getCrm() != null){
            medico0.setCrm(medicoRecordDto.getCrm());
        }
        if(medico0.getEspecialidade() != null && !medico0.getEspecialidade().equals("")){
            medico0.setEspecialidade(medicoRecordDto.getEspecialidade());
        }
        if(medico0.getSenha() != null){
            medico0.setSenha(encoder.encode(medico0.getSenha()));
        }

        System.out.println("\n\n" +
                medico0.getEmail() + "\n" +
                medico0.getSenha() + "\n" +
                medico0.getCrm() + "\n" +
                medico0.getEspecialidade() + "\n"
                + "\n\n");

        medico.salvar(medico0);
        attr.addFlashAttribute("sucess", "Cliente editado com sucesso.");
        return "redirect:/admin/listarMedico";
    }

    @GetMapping("/excluirMedico/{id}")
    public String excluirMedicoPorId(@PathVariable("id") UUID id, RedirectAttributes attr) {
        medico.excluirPorID(id);
        attr.addFlashAttribute("sucess", "Locação excluída com sucesso.");
        return "redirect:/admin/listarmMedico";
    }

}
