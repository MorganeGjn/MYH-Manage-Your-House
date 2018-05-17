package manageyourhouse.myh_manageyourhouse;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import static java.lang.Thread.sleep;

public class MyAdapterEclairage extends RecyclerView.Adapter<MyAdapterEclairage.MyViewHolder> {

    List<Pieces> Pieces = Arrays.asList(MainActivity.Salon, MainActivity.Toilette, MainActivity.Chambre);

    private final List<String> characters = Arrays.asList(
            MainActivity.Salon.getName(),
            MainActivity.Toilette.getName(),
            MainActivity.Chambre.getName()
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
        private Pieces piece = Pieces.get(1);
        private String namePiece;


        public MyViewHolder(final View itemView) {
            super(itemView);
            if (piece.getEtat() == true) {
                itemView.setBackgroundColor(Color.YELLOW);
            } else {
                itemView.setBackgroundColor(0xc8f274);
            }
            name = ((TextView) itemView.findViewById(R.id.name));
            namePiece = piece.getName();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (int i = 0; i <Pieces.size(); i++){
                        if (currentPair == Pieces.get(i).getName()){
                            piece = Pieces.get(i);
                            namePiece = piece.getName();
                        }
                    }
                    if(ServiceSocket.client.socket.isConnected()) {
                        try {
                            ServiceSocket.client.SendSetStateLight(namePiece);
                            sleep(500);
                            if(piece.getEtat() == true && piece.getNotifiation() == true) {
                                ServiceSocketSonnette.scheduleNotification(itemView.getContext(), piece.getMinutes() * 60000);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        Toast toast = Toast.makeText(itemView.getContext(), "Non Connecté", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    if (piece.getEtat() == true) {
                        itemView.setBackgroundColor(Color.YELLOW);
                    } else {
                        itemView.setBackgroundColor(0xc8f274);
                    ;}
                }
            });
        }

        public void display(String pair) {
            currentPair = pair;
            name.setText(pair);
        }
    }
}