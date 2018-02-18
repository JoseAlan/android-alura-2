package br.com.alura.agenda;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

import br.com.alura.agenda.converter.AlunoConverter;
import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.modelo.Aluno;

/**
 * Created by Alan on 18/02/2018.
 */

public class EnviarAlunoTask extends AsyncTask<Object, Object, String> {
    private Context context;

    public EnviarAlunoTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Object... objects) {
        AlunoDAO dao = new AlunoDAO(context);
        List<Aluno> alunos = dao.buscaAlunos();
        dao.close();

        AlunoConverter conversor = new AlunoConverter();
        String json = conversor.converteParaJSON(alunos);

        WebClient client = new WebClient();
        String resposta = client.post(json);

        return resposta;
    }

    @Override
    protected void onPostExecute(String resposta) {
        Toast.makeText(context, resposta, Toast.LENGTH_LONG).show();
    }
}
