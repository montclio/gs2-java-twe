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
            err.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao consultar usuário: " + err.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarUsuario(UsuarioVO usuario) {
        try {
            UsuarioVO usuarioGerado = usuarioBO.cadastrarUsuarioBO(usuario);
            return Response.status(Response.Status.CREATED).entity(usuario).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar usuário: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarUsuario(@PathParam("id") Long id, UsuarioVO usuarioAtualizado) {
        try {
            UsuarioBO usuarioBO = new UsuarioBO();

            boolean atualizado = usuarioBO.atualizarUsuarioBO(id, usuarioAtualizado);

            if (atualizado) {
                return Response.ok(usuarioAtualizado).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Usuário não encontrado ou não atualizado.")
                        .build();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar o usuário.")
                    .build();
        }
    }


}
