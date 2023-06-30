package br.senac.constructor.security.token;

import br.senac.constructor.usuario.UsuarioRepresentation;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenStructure {
    private String sub;
    private UsuarioRepresentation.Resumo usuario;
    private String roles;
    private Long exp;
    private Long iat;
    private String iss;
}