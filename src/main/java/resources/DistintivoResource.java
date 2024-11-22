package resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.bo.DistintivoBO;
import model.bo.UsuarioBO;
import model.vo.DistintivoVO;
import model.vo.UsuarioVO;


import java.sql.SQLException;

@Path("/distintivo")
public class DistintivoResource {
    private final DistintivoBO distintivoBO;
    public DistintivoResource() throws  ClassNotFoundException, SQLException{
        this.distintivoBO = new DistintivoBO();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarDistintivo(@PathParam("id") int idDistintivo) throws SQLException, ClassNotFoundException {
        try {
            DistintivoVO distintivo = distintivoBO.consultarDistintivoBO(idDistintivo);
            if (distintivo == null){
                return Response.status(Response.Status.NOT_FOUND).entity("Distintivo com o ID " + idDistintivo + "não encontrado").build();
            }
            return Response.ok(distintivo).build();
        } catch (Exception err){
            err.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao consultar Distintivo: " + err.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarDistintivo(DistintivoVO distintivo) {
        try {
            DistintivoVO distintivoGerado = distintivoBO.cadastrarDistintivoBO(distintivo);
            return Response.status(Response.Status.CREATED).entity(distintivo).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar Distintivo: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarDistintivo(@PathParam("id") int id, DistintivoVO distintivoAtualizado) {
        try {
            DistintivoBO distintivoBO = new DistintivoBO();

            boolean atualizado = distintivoBO.atualizarDistintivoBO(id, distintivoAtualizado);

            if (atualizado) {
                return Response.ok(distintivoAtualizado).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Usuário não encontrado ou não Distintivo.")
                        .build();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar o Distintivo.")
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluirDistintivo(@PathParam("id") int idDistintivo) {
        try {
            distintivoBO.excluirDistintivoBO(idDistintivo);

            return Response.status(Response.Status.NO_CONTENT).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao excluir Distintivo: " + e.getMessage())
                    .build();
        }
    }
    
}
