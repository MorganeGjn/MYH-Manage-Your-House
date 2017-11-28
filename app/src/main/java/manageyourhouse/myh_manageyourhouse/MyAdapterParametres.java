package manageyourhouse.myh_manageyourhouse;

import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.app.AlertDialog;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static manageyourhouse.myh_manageyourhouse.R.id.action_context_bar;
import static manageyourhouse.myh_manageyourhouse.R.id.none;
import static manageyourhouse.myh_manageyourhouse.R.layout.inputminutes;

public class MyAdapterParametres extends RecyclerView.Adapter<MyAdapterParametres.MyViewHolder> {

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
        String piece = characters.get(position);
        holder.display(piece);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        final private TextView name;
        private String currentPair;


        public MyViewHolder(final View itemView) {
            super(itemView);
            Pieces piece = Pieces.get(1);

            name = itemView.findViewById(R.id.name);
            for (int i = 0; i <Pieces.size(); i++){
                if (currentPair == Pieces.get(i).getName()){
                    piece = Pieces.get(i);
                }
            }
            final boolean[] Notif = new boolean[]{piece.getNotifiation()};
            final CharSequence[] items = {"Notification"};
            final ArrayList seletedItems = new ArrayList();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   AlertDialog dialog = new AlertDialog.Builder(itemView.getContext())
                            .setTitle(currentPair)
                            .setMultiChoiceItems(items, Notif, new DialogInterface.OnMultiChoiceClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int indexSelected, boolean isChecked) {
                                    if (isChecked) {
                                        // If the user checked the item, add it to the selected items
                                        seletedItems.add(indexSelected);
                                    }
                                    else if (seletedItems.contains(indexSelected)) {
                                        // Else, if the item is already in the array, remove it
                                        seletedItems.remove(Integer.valueOf(indexSelected));
                                    }
                                }
                            })
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int id) {
                                            //  Your code when user clicked on OK
                                            //  You can write the code  to save the selected item here
                                        }
                                    })
                            .create();
                    final EditText min = (EditText)itemView.findViewById(R.id.minutes);

                    dialog.setView(min);
                    dialog.show();
                }
            });
            if (seletedItems.size() != 0) {
                piece.setNotification(true);
            }
            else{
                piece.setNotification(false);
            }
        }

        public void display(String pair) {
            currentPair = pair;
            name.setText(pair);
        }
    }
}
