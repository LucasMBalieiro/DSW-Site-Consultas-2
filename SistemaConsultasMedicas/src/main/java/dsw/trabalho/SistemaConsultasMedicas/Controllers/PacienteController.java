package dsw.trabalho.SistemaConsultasMedicas.Controllers;

import dsw.trabalho.SistemaConsultasMedicas.Dtos.PacienteRecordDto;
import dsw.trabalho.SistemaConsultasMedicas.Models.Entities.PacienteModel;
import dsw.trabalho.SistemaConsultasMedicas.Models.ValueObjects.Cpf;
import dsw.trabalho.SistemaConsultasMedicas.Repositories.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Controller
public class PacienteController {

    @Autowired
    PacienteRepository pacienteRepository;

    private final PasswordEncoder encoder;

    public PacienteController(PasswordEncoder encoder, PacienteRepository pacienteRepository) {
        this.encoder = encoder;
        this.pacienteRepository = pacienteRepository;
    }


    @PostMapping("/clientes") //create
    public ResponseEntity<PacienteModel> savePaciente(@RequestBody  @Valid PacienteRecordDto pacienteRecordDto){
        var pacienteModel = new PacienteModel();
        BeanUtils.copyProperties(pacienteRecordDto,pacienteModel);
        pacienteModel.setSenha(encoder.encode(pacienteModel.getSenha()));
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteRepository.save(pacienteModel));//uso do http 201
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<PacienteModel>> getAllPacientes(){
        return ResponseEntity.status(HttpStatus.OK).body(pacienteRepository.findAll());
    }

    @GetMapping("/clientes/{cpf}")
    public ResponseEntity<Object> getOnePaciente(@PathVariable(value= "cpf") Cpf cpf){

        Optional<PacienteModel> paciente0 = Optional.ofNullable(pacienteRepository.findByCpf(cpf));
        if(paciente0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{paciente.notfound}");
        }
        paciente0.get().add(linkTo(methodOn(PacienteController.class).getAllPacientes()).withRel("{paciente.lista}"));
        return ResponseEntity.status(HttpStatus.OK).body(paciente0.get());
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<Object> updatePaciente(@PathVariable(value= "id") UUID id, @RequestBody @Valid PacienteRecordDto pacienteRecordDto) {

        Optional<PacienteModel> paciente0 = pacienteRepository.findById(id);

        if (paciente0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{paciente.notfound}");
        }

        var pacienteModel = paciente0.get();
        BeanUtils.copyProperties(pacienteRecordDto, pacienteModel);
        return ResponseEntity.status(HttpStatus.OK).body(pacienteRepository.save(pacienteModel));
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Object> deletePaciente(@PathVariable(value= "id") UUID id) {

        Optional<PacienteModel> paciente0 = pacienteRepository.findById(id);

        if (paciente0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{paciente.notfound}");
        }

        pacienteRepository.delete(paciente0.get());
        return ResponseEntity.status(HttpStatus.OK).body("{paciente.delete}");
    }
}
