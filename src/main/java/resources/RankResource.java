package resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.bo.RankBO;
import model.bo.UsuarioBO;
import model.vo.RankVO;
import model.vo.UsuarioVO;

import java.sql.SQLException;

@Path("/rank")
public class RankResource {

    private final RankBO rankBO;
    public RankResource() throws  ClassNotFoundException, SQLException {
        this.rankBO = new RankBO();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarRank(@PathParam("id") int idRank) throws SQLException, ClassNotFoundException {
        try {
            RankVO rank = rankBO.consultarRankBO(idRank);
            if (rank == null){
                return Response.status(Response.Status.NOT_FOUND).entity("Rank com o ID " + idRank + "não encontrado").build();
            }
            return Response.ok(rank).build();
        } catch (Exception err){
            err.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao consultar Rank: " + err.getMessage()).build();
        }
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarRank(RankVO rank) {
        try {
            RankVO usuarioGerado = rankBO.cadastrarRankBO(rank);
            return Response.status(Response.Status.CREATED).entity(rank).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar Rank: " + e.getMessage()).build();
        }
    }
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarRank(@PathParam("id") int idRank, RankVO rankAtualizado) {
        try {
            RankBO rankBO = new RankBO();

            boolean atualizado = rankBO.atualizarRankBO(idRank, rankAtualizado);

            if (atualizado) {
                return Response.ok(rankAtualizado).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Rank não encontrado ou não atualizado.")
                        .build();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar o Rank.")
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluirRank(@PathParam("id") int idRank) {
        try {
            rankBO.excluirRankBO(idRank);

            return Response.status(Response.Status.NO_CONTENT).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao excluir Rank: " + e.getMessage())
                    .build();
        }
    }


}
