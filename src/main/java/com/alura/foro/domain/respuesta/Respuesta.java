package com.alura.foro.domain.respuesta;

import com.alura.foro.domain.topico.StatusTopico;
import com.alura.foro.domain.topico.Topico;
import com.alura.foro.domain.usuario.Usuario;
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
					 Usuario usuario,
					 Topico topico) {

		this.mensaje= datosRegistroRespuesta.mensaje();
		this.usuario=usuario;
		this.topico=topico;
		//agregar una respuesta cambia de estado
		if(this.topico.getStatus().equals(StatusTopico.NO_RESPONDIDO)){
			this.topico.setStatus(StatusTopico.NO_SOLUCIONADO);
		}
		this.fechacreacion = LocalDateTime.now();
		this.solucion=false;
		this.activo=true;
		//agregando las respuestas a la lita de respuestas en topico
		this.topico.getRespuestas().add(this);
	}
	public void desactivarRespuesta() {
		this.activo=false;
	}

	public void actualizarRespuesta(DatosActualizarRespuesta datosActualizarRespuesta) {
		if (datosActualizarRespuesta.mensaje()!=null){
			this.mensaje= datosActualizarRespuesta.mensaje();
		}
		if (datosActualizarRespuesta.solucion()!=null){
			this.solucion=datosActualizarRespuesta.solucion();
			if (this.solucion){
				this.topico.setStatus(StatusTopico.SOLUCIONADO);
			}
		}
	}
}
