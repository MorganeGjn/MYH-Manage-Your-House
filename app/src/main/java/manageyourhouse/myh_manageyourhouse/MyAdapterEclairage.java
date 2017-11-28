package manageyourhouse.myh_manageyourhouse;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MyAdapterEclairage extends RecyclerView.Adapter<MyAdapterEclairage.MyViewHolder> {

    Pieces salon = new Pieces("Salon", false, false, 0);
    Pieces toilette = new Pieces("Toilette", false, false, 0);
    Pieces chambre = new Pieces("Chambre", false, false, 0);
    Pieces cuisine = new Pieces("Cuisine", false, false, 0);

    List<Pieces> Pieces = Arrays.asList(salon, toilette, chambre, cuisine);

    private final List<String> characters = Arrays.asList(
            salon.getName(),
            toilette.getName(),
            chambre.getName(),
            cuisine.getName()
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
        private Pieces piece;
        private String namePiece;



        public MyViewHolder(final View itemView) {
            super(itemView);
            name = ((TextView) itemView.findViewById(R.id.name));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (int i = 0; i <Pieces.size(); i++){
                        if (currentPair == Pieces.get(i).getName()){
                            piece = Pieces.get(i);
                            namePiece = piece.getName();
                        }
                    }
                    try {
                        MainActivity.client.SendSetStateLight(namePiece);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        public void display(String pair) {
            currentPair = pair;
            name.setText(pair);
        }
    }
}