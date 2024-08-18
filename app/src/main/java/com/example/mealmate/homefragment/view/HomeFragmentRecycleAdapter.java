//package com.example.mealmate;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.RatingBar;
//import android.widget.TextView;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.List;
//
////public class HomeFragmentRecycleAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{
//    List<Product> produ;
//    // RecyclerView recyclerView;
//    public MyListAdapter(List<Product>  products) {
//        this.produ = products;
//    }
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        View listItem= layoutInflater.inflate(R.layout.recycle_row, parent, false);
//        ViewHolder viewHolder = new ViewHolder(listItem);
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.titlee.setText(produ.get(position).getTitle());
//        holder.pricee.setText(String.valueOf(produ.get(position).getPrice()));
//        holder.brande.setText(produ.get(position).getBrand());
//        holder.descriptione.setText(produ.get(position).getDescription());
//        holder.ratingBaree.setRating(Float.parseFloat(String.valueOf(produ.get(position).getRating())));
//
//        Glide.with(holder.itemView.getContext())
//                .load(produ.get(position).getImages().get(0))
//                .into(holder.imagee);
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return produ!=null?produ.size():0;
//    }
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        public ImageView imagee;
//        public TextView titlee,pricee,brande,descriptione;
//        public RatingBar ratingBaree;
//        public ViewHolder(View itemView) {
//            super(itemView);
//            this.titlee=(TextView) itemView.findViewById(R.id.titleView);
//            this.pricee=(TextView) itemView.findViewById(R.id.priceView);
//            this.brande=(TextView) itemView.findViewById(R.id.brandView);
//            this.descriptione=(TextView) itemView.findViewById(R.id.descriptionView);
//            this.ratingBaree=(RatingBar) itemView.findViewById(R.id.ratingBar);
//            this.imagee = (ImageView) itemView.findViewById(R.id.imageView);
//        }
//    }
//
//}
