package com.duongnv.bikerental.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duongnv.bikerental.HeartActivity;
import com.duongnv.bikerental.R;
import com.duongnv.bikerental.databases.CartDatabase;
import com.duongnv.bikerental.model.Cart;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HeartAdapter extends RecyclerView.Adapter<HeartAdapter.ViewHolderCart> {
    private CartDatabase cartDatabase;
    private HeartActivity context;
    List<Cart> cartList;
    OnClickIteam onClickIteam;

    public HeartAdapter(Context context, List<Cart> cartList) {
        this.context = (HeartActivity) context;
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public ViewHolderCart onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.bikeheart, viewGroup, false);
        return new HeartAdapter.ViewHolderCart(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCart viewHolderCart, int i) {
        viewHolderCart.setData(cartList.get(i));
        viewHolderCart.contenerh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickIteam!= null){
                    onClickIteam.getPosition(i);
                }
            }
        });

    }

    public interface OnClickIteam{
        void  getPosition(int position);
    }


    public void OnclickItem (OnClickIteam onClickIteam) {
        this.onClickIteam = onClickIteam;
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }


    public class ViewHolderCart extends RecyclerView.ViewHolder {
        private TextView txtBikeName, txtBikePrice;
        private ImageView btnRemoveItem;
        private ImageView imageBike;
        private LinearLayout contenerh;


        public ViewHolderCart(@NonNull View itemView) {
            super((itemView));
            contenerh = itemView.findViewById(R.id.contenerh);
            txtBikeName = itemView.findViewById(R.id.txtName);
            txtBikePrice = itemView.findViewById(R.id.textPrice);
            imageBike = itemView.findViewById(R.id.imageBike);
            btnRemoveItem = itemView.findViewById(R.id.btnunheart);

        }

        void setData(final Cart cart) {

            txtBikeName.setText(cart.bikename);
            txtBikePrice.setText(cart.price + "$");
            Picasso.get().load(cart.getImage()).into(imageBike);

            btnRemoveItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDialogDeleteItemfromCart(cart);
                }
            });
        }
    }

    private void deleteItem(final Cart cart) {
        cartDatabase = CartDatabase.getInstance(context);
        class DeleteItem extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                cartDatabase.cartDAO().delete(cart);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if (aVoid == null) {
                    cartList.remove(cart);
                    notifyDataSetChanged();
                }
            }
        }
        DeleteItem dt = new DeleteItem();
        dt.execute();
    }

    public void showDialogDeleteItemfromCart(final Cart cart) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setTitle("Delete from favorite list");
        builder1.setMessage("Are you sure you want to delete this bike?");

        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        deleteItem(cart);
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }


}
