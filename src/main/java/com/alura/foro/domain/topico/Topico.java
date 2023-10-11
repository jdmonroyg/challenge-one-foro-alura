package com.alura.foro.domain.topico;

import com.alura.foro.domain.curso.Curso;
import com.alura.foro.domain.curso.CursoRepository;
import com.alura.foro.domain.respuesta.Respuesta;
import com.alura.foro.domain.usuario.Usuario;
import com.alura.foro.domain.usuario.UsuarioRepository;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "topicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String mensaje;
	// para evitar que hibernate haga su estrategia de nomenclatura y me agrege _
	private LocalDateTime fechacreacion;
	@Enumerated(EnumType.STRING)
	private StatusTopico status;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "curso_id")
	private Curso curso;
	private Boolean activo;
	@OneToMany(mappedBy = "topico", cascade = CascadeType.ALL,
			orphanRemoval = true)
	private List<Respuesta> respuestas;

	// la inyeccion de depencias de un repositorio no se debe hacer en una entidad,
	//no es comun y genera problemas
	public Topico(DatosRegistroTopico datosRegistroTopico,
				  UsuarioRepository usuarioRepository,
				  CursoRepository cursoRepository) {
		this.titulo=datosRegistroTopico.titulo();
		this.mensaje=datosRegistroTopico.mensaje();
		this.usuario= usuarioRepository.getReferenceById(datosRegistroTopico.usuario_id());
		this.curso= cursoRepository.getReferenceById(datosRegistroTopico.curso_id());
		this.fechacreacion =LocalDateTime.now();
		this.status=StatusTopico.NO_RESPONDIDO;
		this.activo=true;
		this.respuestas=new ArrayList<>();
	}

	public void actualizarTopico(DatosActualizarTopico datosActualizarTopico) {
		if(datosActualizarTopico.titulo()!=null){
			this.titulo= datosActualizarTopico.titulo();
		}
		if(datosActualizarTopico.mensaje()!=null){
			this.mensaje= datosActualizarTopico.mensaje();
		}
		if(datosActualizarTopico.status()!=null){
			this.status= datosActualizarTopico.status();
		}
	}

	public void desactivarTopico() {
		this.activo=false;
	}
}
