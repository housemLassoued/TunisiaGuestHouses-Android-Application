package com.example.myapplication;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Guest;
import com.example.myapplication.R;

import java.util.List;

public class GuestAdapter extends RecyclerView.Adapter<GuestAdapter.GuestViewHolder> {

    private List<Guest> guestList;
    private Context context;
    private OnItemClickListener onItemClickListener;
    private boolean isAdminPage;

    public interface OnItemClickListener {
        void onItemClick(Guest guest);
    }

    public GuestAdapter(Context context, List<Guest> guestList, boolean isAdminPage) {
        this.context = context;
        this.guestList = guestList;
        this.isAdminPage = isAdminPage;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public GuestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return new GuestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GuestViewHolder holder, int position) {
        Guest guest = guestList.get(position);

        // Définir les textes
        holder.nameTextView.setText(guest.getName());
        holder.regionTextView.setText(guest.getRegion());
        holder.cityTextView.setText(guest.getCity());
        holder.priceTextView.setText("Prix : " + guest.getPrice());

        // Charger l'image
        int imageResId = context.getResources().getIdentifier(guest.getImage(), "drawable", context.getPackageName());
        if (imageResId != 0) {
            holder.imageView.setImageResource(imageResId);
        } else {
            holder.imageView.setImageResource(R.drawable.default_image);
        }

        // Ajouter un gestionnaire de clic
        holder.itemView.setOnClickListener(v -> {
            if (isAdminPage){
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(guest);
            } }
            else{
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.maisonsdhotesentunisie.com/"));
                context.startActivity(browserIntent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return guestList.size();
    }

    public void updateGuests(List<Guest> newGuestList) {
        guestList = newGuestList;
        notifyDataSetChanged();
    }

    public static class GuestViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView regionTextView;
        TextView cityTextView;
        TextView priceTextView;
        ImageView imageView;

        public GuestViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            regionTextView = itemView.findViewById(R.id.regionTextView);
            cityTextView = itemView.findViewById(R.id.cityTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}

