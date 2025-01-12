package com.alura.foro.domain.usuario;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String email;
	private String password;

	public Usuario(DatosRegistroUsuario datosRegistroUsuario) {
		this.nombre=datosRegistroUsuario.nombre();
		this.email= datosRegistroUsuario.email();
		this.password= datosRegistroUsuario.password();
	}
}
