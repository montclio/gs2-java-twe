package resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.bo.AlternativaBO;
import model.vo.AlternativaVO;
import model.vo.AlternativaVO;


import java.sql.SQLException;

@Path("/alternativa")
public class AlternativaResource {
    private final AlternativaBO alternativaBO;
    public AlternativaResource() throws  ClassNotFoundException, SQLException {
        this.alternativaBO = new AlternativaBO();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarAlternativa(@PathParam("id") int idAlternativa) throws SQLException, ClassNotFoundException {
        try {
            AlternativaVO alternativa = alternativaBO.consultarAlternativaBO(idAlternativa);
            if (alternativa == null){
                return Response.status(Response.Status.NOT_FOUND).entity("Alternativa com o ID " + idAlternativa + "não encontrada").build();
            }
            return Response.ok(alternativa).build();
        } catch (Exception err){
            err.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao consultar Alternativa: " + err.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarAlternativa(AlternativaVO alternativa) {
        try {
            AlternativaVO alternativaGerado = alternativaBO.cadastrarAlternativaBO(alternativa);
            return Response.status(Response.Status.CREATED).entity(alternativa).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar Alternativa: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluirAlternativa(@PathParam("id") int idAlternativa) {
        try {
            alternativaBO.excluirAlternativaBO(idAlternativa);

            return Response.status(Response.Status.NO_CONTENT).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao excluir Alternativa: " + e.getMessage())
                    .build();
        }
    }
}
