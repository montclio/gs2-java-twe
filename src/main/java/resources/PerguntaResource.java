package resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.bo.PerguntaBO;
import model.vo.PerguntaVO;

import java.sql.Connection;
import java.sql.SQLException;

@Path("/pergunta")
public class PerguntaResource {
    private PerguntaBO perguntaBO;

    public PerguntaResource() throws ClassNotFoundException, SQLException{
        this.perguntaBO = new PerguntaBO();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarPergunta(@PathParam("id") int idPergunta) {
        try {
            PerguntaVO pergunta = perguntaBO.consultarPerguntaBO(idPergunta);
            if (pergunta == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Pergunta com ID " + idPergunta + " não encontrada.")
                        .build();
            }
            return Response.ok(pergunta).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao consultar pergunta: " + e.getMessage())
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarPergunta(PerguntaVO pergunta) {
        try {
            PerguntaVO perguntaCriada = perguntaBO.cadastrarPerguntaBO(pergunta);
            return Response.status(Response.Status.CREATED).entity(perguntaCriada).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar pergunta: " + e.getMessage())
                    .build();
        }
    }


    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarPergunta(@PathParam("id") int idPergunta, PerguntaVO perguntaAtualizada) {
        try {
            perguntaAtualizada.setIdPergunta(idPergunta);
            boolean atualizado = perguntaBO.atualizarPerguntaBO(perguntaAtualizada);
            if (atualizado) {
                return Response.ok(perguntaAtualizada).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Pergunta com ID " + idPergunta + " não encontrada para atualização.")
                        .build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar pergunta: " + e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluirPergunta(@PathParam("id") int idPergunta) {
        try {
            boolean excluido = perguntaBO.excluirPerguntaBO(idPergunta);
            if (excluido) {
                return Response.status(Response.Status.NO_CONTENT).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Pergunta com ID " + idPergunta + " não encontrada para exclusão.")
                        .build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao excluir pergunta: " + e.getMessage())
                    .build();
        }
    }
}
