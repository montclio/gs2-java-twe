package resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.bo.QuizBO;
import model.bo.QuizBO;
import model.vo.QuizVO;
import model.vo.QuizVO;
import model.vo.UsuarioVO;


import java.sql.SQLException;

@Path("/quiz")
public class QuizResource {
    private final QuizBO quizBO;
    public QuizResource() throws  ClassNotFoundException, SQLException {
        this.quizBO = new QuizBO();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarQuiz(@PathParam("id") int idQuiz) throws SQLException, ClassNotFoundException {
        try {
            QuizVO quiz = quizBO.consultarQuizBO(idQuiz);
            if (quiz == null){
                return Response.status(Response.Status.NOT_FOUND).entity("Quiz com o ID " + idQuiz + "não encontrado").build();
            }
            return Response.ok(quiz).build();
        } catch (Exception err){
            err.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao consultar usuário: " + err.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarQuiz(QuizVO quiz) {
        try {
            QuizVO quizGerado = quizBO.cadastrarQuizBO(quiz);
            return Response.status(Response.Status.CREATED).entity(quiz).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar Quiz: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarQuiz(@PathParam("id") int idQuiz, int idPergunta) {
        try {
            QuizBO QuizBO = new QuizBO();

            boolean atualizado = QuizBO.atualizarQuizBO(idQuiz, idPergunta);

            if (atualizado) {
                return Response.ok(idPergunta).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Quiz não encontrado ou não atualizado.")
                        .build();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar o Quiz.")
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluirQuiz(@PathParam("id") int idQuiz) {
        try {
            quizBO.excluirQuizBO(idQuiz);

            return Response.status(Response.Status.NO_CONTENT).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao excluir Quiz: " + e.getMessage())
                    .build();
        }
    }
    
}
