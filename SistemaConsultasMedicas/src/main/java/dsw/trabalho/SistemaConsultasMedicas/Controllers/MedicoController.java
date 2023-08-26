package dsw.trabalho.SistemaConsultasMedicas.Controllers;


import dsw.trabalho.SistemaConsultasMedicas.Dtos.MedicoRecordDto;
import dsw.trabalho.SistemaConsultasMedicas.Models.Entities.MedicoModel;
import dsw.trabalho.SistemaConsultasMedicas.Repositories.MedicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class MedicoController {

    @Autowired
    MedicoRepository medicoRepository;//ponto de injecaom

    @PostMapping("/medicos") //create
    public ResponseEntity<MedicoModel> saveMedico(@RequestBody  @Valid MedicoRecordDto medicoRecordDto){
        var medicoModel = new MedicoModel();
        BeanUtils.copyProperties(medicoRecordDto,medicoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoRepository.save(medicoModel));//uso do http 201
    }

    @GetMapping("/medicos")
    public ResponseEntity<List<MedicoModel>> getAllMedicos(){
        return ResponseEntity.status(HttpStatus.OK).body(medicoRepository.findAll());
    }

    @GetMapping("/medicos/{id}")
    public ResponseEntity<Object> getOneMedico(@PathVariable(value= "id") UUID id){

        Optional<MedicoModel> medico0 = medicoRepository.findById(id);
        if(medico0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medico nao encontrado.");//todo criar mensagem
        }
        return ResponseEntity.status(HttpStatus.OK).body(medico0.get());
    }

    @PutMapping("/medicos/{id}")//upddating
    public ResponseEntity<Object> updateMedico(@PathVariable(value= "id") UUID id, @RequestBody @Valid MedicoRecordDto medicoRecordDto) {

        Optional<MedicoModel> medico0 = medicoRepository.findById(id);
        if (medico0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medico nao encontrado.");//todo criar mensagem
        }
        var medicoModel = medico0.get();//pega o antigfo
        BeanUtils.copyProperties(medicoRecordDto, medicoModel);//converte pro novo
        return ResponseEntity.status(HttpStatus.OK).body(medicoRepository.save(medicoModel));//salva
    }

    @DeleteMapping("/medicos/{id}")//deleting
    public ResponseEntity<Object> updateMedico(@PathVariable(value= "id") UUID id) {

        Optional<MedicoModel> medico0 = medicoRepository.findById(id);
        if (medico0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medico nao encontrado.");//todo criar mensagem
        }
        medicoRepository.delete(medico0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Deletado corretamente");//salva
    }

}
