package admin.sigmastock.sigmastock.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;


/**
 * Created by NGUYEN DUC LINH on 25/03/2016.
 */
public class NewsAdapter extends ArrayAdapter<String> {
    public NewsAdapter(Context context, int resource) {
        super(context, resource);
    }
    /*private Context context;
    private List<RssItem> items;

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    // Add a list of items
    public void addAll(List<RssItem> item) {
        items.addAll(item);
        notifyDataSetChanged();
    }
    public NewsAdapter(Context context,List<RssItem> items) {
        this.items = items;
        this.context = context;
    }

    public void updateAdapter(List<RssItem> item, Context context2)
    {
        items = item;
        context = context2;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.list_title_bai_bao, parent, false);
        return new RecyclerViewHolder(itemView);
    }

    public void onClickItem()
    {

    }
    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {

        if (items.get(position).getDescription().length() < 5) {
            holder.tv.setText(items.get(position).getTitel());
            holder.iv.setImageResource(R.drawable.icon_bai_bao);
        } else {
            Picasso.with(context).load(items.get(position).getDescription()).transform(new CircleTransform())
                    .placeholder(R.drawable.icon_bai_bao).error(R.drawable.icon_bai_bao).into(holder.iv);

            holder.tv.setText(items.get(position).getTitel().trim());
            holder.txtPubdate.setText(items.get(position).getDate().trim());
        }
        holder.lnNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.lnNews.getContext(), DisplayItemNewsPaper.class);
                intent.putExtra(Variables.LINK,items.get(position).getLink());
                holder.lnNews.getContext().startActivity(intent);
            }
        });
    }
        @Override
        public int getItemCount () {
            return items.size();
        }
        public class RecyclerViewHolder extends RecyclerView.ViewHolder
        {
            ImageView iv ;
            TextView tv ;
            TextView txtPubdate ;
            LinearLayout lnNews;
            public RecyclerViewHolder(View itemView) {
                super(itemView);
                 iv = (ImageView) itemView.findViewById(R.id.imgIcon);
                 tv = (TextView) itemView.findViewById(R.id.title);
                 txtPubdate = (TextView) itemView.findViewById(R.id.pubDate);
                 lnNews = (LinearLayout) itemView.findViewById(R.id.lnNews);
            }
        }
*/
}
