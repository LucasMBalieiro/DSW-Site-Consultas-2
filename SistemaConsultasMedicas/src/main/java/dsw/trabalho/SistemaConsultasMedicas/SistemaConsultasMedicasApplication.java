package dsw.trabalho.SistemaConsultasMedicas;

import dsw.trabalho.SistemaConsultasMedicas.Models.Entities.*;
import dsw.trabalho.SistemaConsultasMedicas.Models.ValueObjects.*;
import dsw.trabalho.SistemaConsultasMedicas.Repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class SistemaConsultasMedicasApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaConsultasMedicasApplication.class, args);
	}


	@Bean
	public CommandLineRunner demo(AdminRepository adminRepository, MedicoRepository medicoRepository, PacienteRepository pacienteRepository, BCryptPasswordEncoder encoder, ConsultaRepository consultaRepository){
		return args -> {

			PacienteModel p1 = new PacienteModel();
			if(pacienteRepository.findByCpf("14059211613") == null){
				p1.setNome("Lucas Maciel Balieiro");
				p1.setEmail(new Email("lucas.macbali@gmail.com"));
				p1.setSenha(encoder.encode("senhalucas"));
				p1.setCpf(new Cpf("14059211613"));
				p1.setTelefone(new Telefone("992391803"));
				p1.setDataNascimento("18-03-2003");
				p1.setSexo("M");
				pacienteRepository.save(p1);
			}

			PacienteModel p2 = new PacienteModel();
			if(pacienteRepository.findByCpf("31611295041") == null) {
				p2.setNome("Julio Silva Pereira");
				p2.setEmail(new Email("julio.spereira@gmail.com"));
				p2.setSenha(encoder.encode("senhajulio"));
				p2.setCpf(new Cpf("31611295041"));
				p2.setTelefone(new Telefone("998563227"));
				p2.setDataNascimento("27-08-1998");
				p2.setSexo("M");
				pacienteRepository.save(p2);
			}

			if(pacienteRepository.findByCpf("52778370862") == null) {
				PacienteModel p3 = new PacienteModel();
				p3.setNome("Gabriela Andrade Santos");
				p3.setEmail(new Email("gabi.santos@gmail.com"));
				p3.setSenha(encoder.encode("senhagabi"));
				p3.setCpf(new Cpf("52778370862"));
				p3.setTelefone(new Telefone("987323339"));
				p3.setDataNascimento("16-05-2001");
				p3.setSexo("F");
				pacienteRepository.save(p3);
			}

			MedicoModel m1 = new MedicoModel();
			if(medicoRepository.findByCrm("987654CRM-BR") == null) {
				m1.setNome("Dr. Alan Demetrius");
				m1.setEmail(new Email("alan.demetrius@gmail.com"));
				m1.setSenha(encoder.encode("senhaalan"));
				m1.setCrm(new Crm("987654CRM-BR"));
				m1.setEspecialidade("Cardiologista");
				medicoRepository.save(m1);
			}

			MedicoModel m2 = new MedicoModel();
			if(medicoRepository.findByCrm("845017CRM-BR") == null) {
				m2.setNome("Dr. Murilo Naldi");
				m2.setEmail(new Email("murilo.naldi@gmail.com"));
				m2.setSenha(encoder.encode("senhamurilo"));
				m2.setCrm(new Crm("845017CRM-BR"));
				m2.setEspecialidade("Cardiologista");
				medicoRepository.save(m2);
			}

			if(medicoRepository.findByCrm("732122CRM-BR") == null) {
				MedicoModel m3 = new MedicoModel();
				m3.setNome("Dr. Jander Moreira");
				m3.setEmail(new Email("jander.moreira@gmail.com"));
				m3.setSenha(encoder.encode("senhajander"));
				m3.setCrm(new Crm("732122CRM-BR"));
				m3.setEspecialidade("Oltalmologista");
				medicoRepository.save(m3);
			}

			if(adminRepository.findByEmail("admin@gmail.com") == null) {
				AdminModel u1 = new AdminModel();
				u1.setNome("Ademir");
				u1.setEmail(new Email("admin@gmail.com"));
				u1.setSenha(encoder.encode("senhaadmin"));
				adminRepository.save(u1);
			}

//			ConsultaModel c1 = new ConsultaModel();
//			c1.setMedico(m1);
//			c1.setPaciente(p1);
//			c1.setDataConsulta("06-09-2023");
//			c1.setHorarioConsulta(new HorarioConsulta("14:00"));
//			consultaRepository.save(c1);
//
//			ConsultaModel c2 = new ConsultaModel();
//			c2.setMedico(m1);
//			c2.setPaciente(p2);
//			c2.setDataConsulta("07-09-2023");
//			c2.setHorarioConsulta(new HorarioConsulta("15:00"));
//			consultaRepository.save(c2);
//
//			ConsultaModel c3 = new ConsultaModel();
//			c3.setMedico(m2);
//			c3.setPaciente(p1);
//			c3.setDataConsulta("14-09-2023");
//			c3.setHorarioConsulta(new HorarioConsulta("09:30"));
//			consultaRepository.save(c3);
		};
	}

}
