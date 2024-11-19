package resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.bo.UsuarioBO;
import model.vo.UsuarioVO;

import java.sql.SQLException;

@Path("/usuario")
public class UsuarioResource {
    private final UsuarioBO usuarioBO;
    public UsuarioResource() throws  ClassNotFoundException, SQLException{
        this.usuarioBO = new UsuarioBO();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarUsuario(@PathParam("id") int idUsuario) throws SQLException, ClassNotFoundException {
        try {
            UsuarioVO usuario = usuarioBO.consultarUsuarioBO(idUsuario);
            if (usuario == null){
                return Response.status(Response.Status.NOT_FOUND).entity("Usuário com o ID " + idUsuario + "não encontrado").build();
            }
            return Response.ok(usuario).build();
        } catch (Exception err){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao consultar usuário: " + err.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarUsuario(UsuarioVO usuario) {
        try {
            int idGerado = usuarioBO.cadastrarUsuarioBO(usuario);
            usuario.setIdUsuario(idGerado);
            return Response.status(Response.Status.CREATED).entity(usuario).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar usuário: " + e.getMessage()).build();
        }
    }
}
