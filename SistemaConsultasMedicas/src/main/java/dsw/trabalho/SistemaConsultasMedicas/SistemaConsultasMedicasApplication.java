package dsw.trabalho.SistemaConsultasMedicas;

import dsw.trabalho.SistemaConsultasMedicas.Models.Entities.AdminModel;
import dsw.trabalho.SistemaConsultasMedicas.Models.Entities.MedicoModel;
import dsw.trabalho.SistemaConsultasMedicas.Models.Entities.PacienteModel;
import dsw.trabalho.SistemaConsultasMedicas.Models.Entities.UsuarioModel;
import dsw.trabalho.SistemaConsultasMedicas.Models.ValueObjects.Cpf;
import dsw.trabalho.SistemaConsultasMedicas.Models.ValueObjects.Crm;
import dsw.trabalho.SistemaConsultasMedicas.Models.ValueObjects.Email;
import dsw.trabalho.SistemaConsultasMedicas.Models.ValueObjects.Telefone;
import dsw.trabalho.SistemaConsultasMedicas.Repositories.MedicoRepository;
import dsw.trabalho.SistemaConsultasMedicas.Repositories.PacienteRepository;
import dsw.trabalho.SistemaConsultasMedicas.Repositories.UsuarioRepository;
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
	public CommandLineRunner demo(UsuarioRepository usuarioRepository, MedicoRepository medicoRepository, PacienteRepository pacienteRepository, BCryptPasswordEncoder encoder){
		return args -> {

			PacienteModel p1 = new PacienteModel();
			p1.setNome("Lucas Maciel Balieiro");
			p1.setEmail(new Email("lucas.macbali@gmail.com"));
			p1.setSenha(encoder.encode("senhalucas"));
			p1.setCpf(new Cpf("14059211613"));
			p1.setTelefone(new Telefone("992391803"));
			p1.setDataNascimento("18-03-2003");
			p1.setSexo("M");
			pacienteRepository.save(p1);

			PacienteModel p2 = new PacienteModel();
			p2.setNome("Julio Silva Pereira");
			p2.setEmail(new Email("julio.spereira@gmail.com"));
			p2.setSenha(encoder.encode("senhajulio"));
			p2.setCpf(new Cpf("31611295041"));
			p2.setTelefone(new Telefone("998563227"));
			p2.setDataNascimento("27-08-1998");
			p2.setSexo("M");
			pacienteRepository.save(p2);

			PacienteModel p3 = new PacienteModel();
			p3.setNome("Gabriela Andrade Santos");
			p3.setEmail(new Email("gabi.santos@gmail.com"));
			p3.setSenha(encoder.encode("senhagabi"));
			p3.setCpf(new Cpf("52778370862"));
			p3.setTelefone(new Telefone("987323339"));
			p3.setDataNascimento("16-05-2001");
			p3.setSexo("F");
			pacienteRepository.save(p3);

			MedicoModel m1 = new MedicoModel();
			m1.setNome("Dr. Alan Demetrius");
			m1.setEmail(new Email("alan.demetrius@gmail.com"));
			m1.setSenha(encoder.encode("senhaalan"));
			m1.setCrm(new Crm("987654CRM-BR"));
			m1.setEspecialidade("Cardiologista");
			medicoRepository.save(m1);

			MedicoModel m2 = new MedicoModel();
			m2.setNome("Dr. Murilo Naldi");
			m2.setEmail(new Email("murilo.naldi@gmail.com"));
			m2.setSenha(encoder.encode("senhamurilo"));
			m2.setCrm(new Crm("845017CRM-BR"));
			m2.setEspecialidade("Cardiologista");
			medicoRepository.save(m2);

			MedicoModel m3 = new MedicoModel();
			m3.setNome("Dr. Jander Moreira");
			m3.setEmail(new Email("jander.moreira@gmail.com"));
			m3.setSenha(encoder.encode("senhajander"));
			m3.setCrm(new Crm("732122CRM-BR"));
			m3.setEspecialidade("Oltalmologista");
			medicoRepository.save(m3);

			AdminModel u1 = new AdminModel();
			u1.setNome("Ademir");
			u1.setEmail(new Email("admin@gmail.com"));
			u1.setSenha(encoder.encode("senhaadmin"));
			
			usuarioRepository.save(u1);
		};
	}

}
