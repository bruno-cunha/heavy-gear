package br.com.bcunha.heavygear.ui.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonObject;

import br.com.bcunha.heavygear.R;
import br.com.bcunha.heavygear.model.api.Api;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HeavyGearActivity extends Activity {

    private Api apiService;

    @BindView(R.id.edtCodigo)
    EditText edtCotacao;

    @BindView(R.id.txtCotacao)
    TextView txtCotacao;

    @BindView(R.id.btnCotacao)
    Button   btnCotacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heavy_gear);

        apiService = Api.getInstance();
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnCotacao)
    public void buscar(View view) {

        apiService.getCotacao(edtCotacao.getText().toString()).enqueue(new Callback<JsonObject>(){
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                int statusCode = response.code();
                txtCotacao.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                // Log error here since request failed
            }
        });
    }
}
