package com.alura.foro.domain.respuesta;

import com.alura.foro.domain.topico.StatusTopico;
import com.alura.foro.domain.topico.Topico;
import com.alura.foro.domain.topico.TopicoRespository;
import com.alura.foro.domain.usuario.Usuario;
import com.alura.foro.domain.usuario.UsuarioRepository;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "respuestas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String mensaje;
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	@ManyToOne
	@JoinColumn(name = "topico_id")
	private Topico topico;
	private LocalDateTime fechacreacion;
	private Boolean solucion;
	private Boolean activo;


	public Respuesta(DatosRegistroRespuesta datosRegistroRespuesta,
					 UsuarioRepository usuarioRepository,
					 TopicoRespository topicoRespository) {
		this.mensaje= datosRegistroRespuesta.mensaje();
		this.usuario=usuarioRepository.getReferenceById(
				datosRegistroRespuesta.usuario_id()
		);
		this.topico=topicoRespository.getReferenceById(
				datosRegistroRespuesta.topico_id()
		);
		//agregar una respuesta cambia de estado
		this.topico.setStatus(StatusTopico.NO_SOLUCIONADO);
		this.fechacreacion = LocalDateTime.now();
		this.solucion=false;
		this.activo=true;

	}
	public void desactivarRespuesta() {
		this.activo=false;
	}
}
