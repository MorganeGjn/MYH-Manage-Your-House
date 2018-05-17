package manageyourhouse.myh_manageyourhouse;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


public class Eclairage extends AppCompatActivity {

    private String Pieces[];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eclairage);
        final RecyclerView rv = (RecyclerView) findViewById(R.id.ListePiece);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new MyAdapterEclairage());

    }
}
