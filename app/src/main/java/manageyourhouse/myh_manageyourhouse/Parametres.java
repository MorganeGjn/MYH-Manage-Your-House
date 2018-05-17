package manageyourhouse.myh_manageyourhouse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class Parametres extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parametres);
        final RecyclerView rv = (RecyclerView) findViewById(R.id.ListePiece);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new MyAdapterParametres());
    };
}




