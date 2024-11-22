package model.bo;

import model.dao.QuizDAO;
import model.vo.QuizVO;

import java.sql.SQLException;

public class QuizBO {
    private QuizDAO quizDAO;
    public QuizBO() throws ClassNotFoundException, SQLException{
        this.quizDAO = new QuizDAO();
    }

    public QuizVO cadastrarQuizBO(QuizVO quiz) throws ClassNotFoundException, SQLException {
        return quizDAO.cadastrarQuiz(quiz);
    }

    public QuizVO consultarQuizBO(int idQuiz) throws ClassNotFoundException, SQLException {
        return quizDAO.consultarQuiz(idQuiz);
    }


    public void excluirQuizBO(int idQuiz) throws ClassNotFoundException, SQLException {
        quizDAO.deletarQuiz(idQuiz);
    }

    public boolean atualizarQuizBO(int idQuiz, int idPergunta) throws ClassNotFoundException, SQLException{
        return quizDAO.atualizarQuiz(idQuiz, idPergunta);
    }
}
