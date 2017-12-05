package manageyourhouse.myh_manageyourhouse;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static manageyourhouse.myh_manageyourhouse.R.color.GreenBack;

public class MyAdapterEclairage extends RecyclerView.Adapter<MyAdapterEclairage.MyViewHolder> {

    List<Pieces> Pieces = Arrays.asList(MainActivity.Salon, MainActivity.Toilette, MainActivity.Chambre, MainActivity.Cuisine);

    private final List<String> characters = Arrays.asList(
            MainActivity.Salon.getName(),
            MainActivity.Toilette.getName(),
            MainActivity.Chambre.getName(),
            MainActivity.Cuisine.getName()
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
        Pieces piece = Pieces.get(1);
        for (int i = 0; i< Pieces.size(); i++){
            if (pair == Pieces.get(i).getName()) {
                piece = Pieces.get(i);
                if (piece.getEtat() == true) {
                    holder.itemView.setBackgroundColor(Color.YELLOW);
                } else {
                    holder.itemView.setBackgroundColor(0xc8f274);
                }
            }
        }
        holder.display(pair);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private String currentPair;
        private Pieces piece;
        private String namePiece;
        private String Reponse;



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
                        Reponse = MainActivity.client.SendSetStateLight(namePiece);
                        if (Reponse.equals(1)){
                            for (int i = 0; i <Pieces.size(); i++){
                                if (currentPair == Pieces.get(i).getName()){
                                    Pieces.get(i).setEtat(true);

                                }
                            }
                        }
                        else if(Reponse.equals(0)){
                            for (int i = 0; i <Pieces.size(); i++){
                                if (currentPair == Pieces.get(i).getName()){
                                    Pieces.get(i).setEtat(false);

                                }
                            }
                        }
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