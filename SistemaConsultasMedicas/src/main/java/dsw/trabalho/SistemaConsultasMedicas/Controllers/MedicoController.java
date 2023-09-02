package dsw.trabalho.SistemaConsultasMedicas.Controllers;


import dsw.trabalho.SistemaConsultasMedicas.Dtos.MedicoRecordDto;
import dsw.trabalho.SistemaConsultasMedicas.Models.Entities.ConsultaModel;
import dsw.trabalho.SistemaConsultasMedicas.Models.Entities.MedicoModel;
import dsw.trabalho.SistemaConsultasMedicas.Models.ValueObjects.Crm;
import dsw.trabalho.SistemaConsultasMedicas.Repositories.ConsultaRepository;
import dsw.trabalho.SistemaConsultasMedicas.Repositories.MedicoRepository;
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
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    MedicoRepository medicoRepository;//ponto de injecaom
    @Autowired
    ConsultaRepository consultaRepository;

    private final PasswordEncoder encoder;

    public MedicoController(PasswordEncoder encoder, MedicoRepository medicoRepository) {
        this.encoder = encoder;
        this.medicoRepository = medicoRepository;
    }

    @GetMapping("/listarConsultas")
    public List<ConsultaModel> ListarConsultas(@RequestBody  @Valid MedicoRecordDto medicoRecordDto)
    {
        List<ConsultaModel> ConsultasDoMedico;
        ConsultasDoMedico =consultaRepository.findByMedico(medicoRecordDto.crm());

        return ConsultasDoMedico;
    }



    /*
    @PostMapping("/profissionais") //create
    public ResponseEntity<MedicoModel> saveMedico(@RequestBody  @Valid MedicoRecordDto medicoRecordDto){
        var medicoModel = new MedicoModel();
        BeanUtils.copyProperties(medicoRecordDto,medicoModel);
        medicoModel.setSenha(encoder.encode(medicoModel.getSenha()));
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoRepository.save(medicoModel));//uso do http 201
    }

    @GetMapping("/profissionais")
    public ResponseEntity<List<MedicoModel>> getAllMedicos(){
        //List<MedicoModel> medicoModelList = medicoRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(medicoRepository.findAll());
    }

    @GetMapping("/profissionais/{id}")
    public ResponseEntity<Object> getOneMedico(@PathVariable(value= "id") UUID id){

        Optional<MedicoModel> medico0 = medicoRepository.findById(id);
        if(medico0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medico nao encontrado.");//todo criar mensagem
        }
        medico0.get().add(linkTo(methodOn(MedicoController.class).getAllMedicos()).withRel("Medicos List"));
        return ResponseEntity.status(HttpStatus.OK).body(medico0.get());
    }

    @GetMapping("/profissionais/nome/{nome}")
    public ResponseEntity<Object> getMedicoByEspecialidade(@PathVariable(value= "nome") String nome){
        List<MedicoModel> medicoModelList = medicoRepository.findByNome(nome);
        //pra cada produto, obtem o id, .add pra construir link, basicamente usa o getOneMedico
        for(MedicoModel medico : medicoModelList){
            UUID id = medico.getIdMedico();
            medico.add(linkTo(methodOn(MedicoController.class).getOneMedico(id)).withSelfRel());
        }
        return ResponseEntity.status(HttpStatus.OK).body(medicoModelList);
    }

    @GetMapping("/profissionais/crm/{crm}")
    public ResponseEntity<Object> getMedicoByCrm(@PathVariable(value= "crm") Crm crm){
        MedicoModel medicoModel = medicoRepository.findByCrm(crm);
        UUID id = medicoModel.getIdMedico();
        medicoModel.add(linkTo(methodOn(MedicoController.class).getOneMedico(id)).withSelfRel());

        return ResponseEntity.status(HttpStatus.OK).body(medicoModel);
    }


    @PutMapping("/profissionais/{id}")//upddating
    public ResponseEntity<Object> updateMedico(@PathVariable(value= "id") UUID id, @RequestBody @Valid MedicoRecordDto medicoRecordDto) {

        Optional<MedicoModel> medico0 = medicoRepository.findById(id);
        if (medico0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medico nao encontrado.");//todo criar mensagem
        }
        var medicoModel = medico0.get();//pega o antigfo
        BeanUtils.copyProperties(medicoRecordDto, medicoModel);//converte pro novo
        return ResponseEntity.status(HttpStatus.OK).body(medicoRepository.save(medicoModel));//salva
    }

    @DeleteMapping("/profissionais/{id}")//deleting
    public ResponseEntity<Object> deleteMedico(@PathVariable(value= "id") UUID id) {

        Optional<MedicoModel> medico0 = medicoRepository.findById(id);
        if (medico0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medico nao encontrado.");//todo criar mensagem
        }
        medicoRepository.delete(medico0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Deletado corretamente");//salva
    }*/



}
