package resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.bo.RespostaBO;
import model.vo.RespostaVO;

import java.sql.SQLException;

@Path("/resposta")
public class RespostaResource {
    private final RespostaBO respostaBO;

    public RespostaResource() throws ClassNotFoundException, SQLException {
        this.respostaBO = new RespostaBO();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarResposta(@PathParam("id") int idResposta) throws SQLException, ClassNotFoundException {
        try {
            RespostaVO resposta = respostaBO.consultarRespostaBO(idResposta);
            if (resposta == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Resposta com o ID " + idResposta + " não encontrada").build();
            }
            return Response.ok(resposta).build();
        } catch (Exception err) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao consultar resposta: " + err.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarResposta(RespostaVO resposta) {
        try {
            RespostaVO respostaGerada = respostaBO.cadastrarRespostaBO(resposta);
            return Response.status(Response.Status.CREATED).entity(respostaGerada).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao cadastrar resposta: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarResposta(@PathParam("id") int id, RespostaVO respostaAtualizada) {
        try {
            boolean atualizado = respostaBO.atualizarRespostaBO(id, respostaAtualizada);
            if (atualizado) {
                return Response.ok(respostaAtualizada).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Resposta não encontrada ou não atualizada.").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao atualizar a resposta.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluirResposta(@PathParam("id") int idResposta) {
        try {
            respostaBO.excluirRespostaBO(idResposta);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao excluir resposta: " + e.getMessage()).build();
        }
    }
}
