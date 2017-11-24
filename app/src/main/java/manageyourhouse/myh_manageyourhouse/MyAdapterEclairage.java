package manageyourhouse.myh_manageyourhouse;

import android.support.v7.widget.RecyclerView;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import manageyourhouse.myh_manageyourhouse.MainActivity;

public class MyAdapterEclairage extends RecyclerView.Adapter<MyAdapterEclairage.MyViewHolder> {

    Pieces Salon = new Pieces("Salon", false, false, 0);
    Pieces Toilette = new Pieces("Toilette", false, false, 0);
    Pieces Chambre = new Pieces("Chambre", false, false, 0);
    Pieces Cuisine = new Pieces("Cuisine", false, false, 0);

    List<Pieces> Pieces = Arrays.asList(Salon, Toilette, Chambre, Cuisine);

    private final List<String> characters = Arrays.asList(
            Salon.getName(),
            Toilette.getName(),
            Chambre.getName(),
            Cuisine.getName()
    );

    @Override
    public int getItemCount() {
        return characters.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_cell, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String pair = characters.get(position);
        holder.display(pair);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private String currentPair;

        public MyViewHolder(final View itemView) {
            super(itemView);
            name = ((TextView) itemView.findViewById(R.id.name));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        MainActivity.client.SendSetStateLight(1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    new AlertDialog.Builder(itemView.getContext())
                            .setTitle(currentPair)
                            .show();
                }
            });
        }

        public void display(String pair) {
            currentPair = pair;
            name.setText(pair);
        }
    }
}