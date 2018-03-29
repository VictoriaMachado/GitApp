package br.com.victoriamachado.gitapp;

import android.app.ProgressDialog;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    private EditText etUser;
    private TextView tvName;
    private String avatar_url;
    private ImageView ivAvatar;

    private GithubModel user;

    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUser = (EditText)findViewById(R.id.etUser);
        tvName = (TextView)findViewById(R.id.tvName);
        ivAvatar = (ImageView)findViewById(R.id.ivAvatar);
    }

    public void search(View v){
        exibirProgress();

        final RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("https://api.github.com").build();

        GitAPI api = restAdapter.create(GitAPI.class);

        api.getGithubModel(etUser.getText().toString(), new Callback<GithubModel>() {
            @Override
            public void success(GithubModel githubModel, Response response) {
                user = githubModel;
                tvName.setText(user.getName());
                avatar_url = user.getAvatar_url();
                Picasso.with(MainActivity.this).load(avatar_url).into(ivAvatar);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

        fecharProgress();

    }


    private void exibirProgress(){
        progress = new ProgressDialog(this);
        progress.setMessage("Pesquisando...");
        progress.setIndeterminate(true);
        progress.show();
    }

    private void fecharProgress(){
        if(progress != null){
            if(progress.isShowing()){
                progress.dismiss();
            }
        }
    }
}
