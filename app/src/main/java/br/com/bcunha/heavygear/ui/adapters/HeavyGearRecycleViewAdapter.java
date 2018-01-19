package br.com.bcunha.heavygear.ui.adapters;

import android.animation.ValueAnimator;
import android.content.Context;
import android.preference.PreferenceManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.bcunha.heavygear.R;
import br.com.bcunha.heavygear.model.pojo.Ativo;
import br.com.bcunha.heavygear.model.pojo.Quote;
import br.com.bcunha.heavygear.ui.activities.ConfiguracaoActivity;

/**
 * Created by BRUNO on 18/10/2016.
 */

public class HeavyGearRecycleViewAdapter extends RecyclerView.Adapter<HeavyGearRecycleViewAdapter.HeavyGearRecycleViewHolder> {

    public static class HeavyGearRecycleViewHolder extends RecyclerView.ViewHolder {
        final CardView cardView;
        final ImageView logo;
        final TextView codigo;
        final TextView empresa;
        final TextView moeda;
        final TextView cotacao;
        final TextView variacao;
        final TextView minimaDia;
        final TextView maximaDia;
        final TextView abertura;
        final TextView volume;
        Ativo ativo;
        private RelativeLayout relativeSecundario;

        public HeavyGearRecycleViewHolder(final View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.cardView_heavy_gear);
            logo = (ImageView) view.findViewById(R.id.imgAcao);
            codigo = (TextView) view.findViewById(R.id.codigo);
            empresa = (TextView) view.findViewById(R.id.empresa);
            moeda = (TextView) view.findViewById(R.id.moeda);
            cotacao = (TextView) view.findViewById(R.id.cotacao);
            variacao = (TextView) view.findViewById(R.id.variacao);
            relativeSecundario = (RelativeLayout) view.findViewById(R.id.relativeSecundario);
            minimaDia = (TextView) view.findViewById(R.id.minimaDia);
            maximaDia = (TextView) view.findViewById(R.id.maximaDia);
            abertura = (TextView) view.findViewById(R.id.abertura);
            volume = (TextView) view.findViewById(R.id.volume);

            /*cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    if (ativo.getOriginalHeight() == 0) {
                        if (ativo.isViewExpanded()) {
                            ativo.setOriginalHeight((int) (view.getHeight() / 2.5));
                        } else {
                            ativo.setOriginalHeight(view.getHeight());
                        }
                    }

                    ValueAnimator valueAnimator;
                    if (ativo.isViewExpanded()) {
                        relativeSecundario.setVisibility(View.GONE);
                        relativeSecundario.setEnabled(false);
                        ativo.setViewExpanded(false);
                        valueAnimator = ValueAnimator.ofInt(ativo.getOriginalHeight() + (int) (ativo.getOriginalHeight() * 1.5), ativo.getOriginalHeight());
                    } else {
                        relativeSecundario.setVisibility(View.VISIBLE);
                        relativeSecundario.setEnabled(true);
                        ativo.setViewExpanded(true);
                        valueAnimator = ValueAnimator.ofInt(ativo.getOriginalHeight(), ativo.getOriginalHeight() + (int) (ativo.getOriginalHeight() * 1.5));
                    }
                    valueAnimator.setDuration(200);
                    valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        public void onAnimationUpdate(ValueAnimator animation) {
                            Integer value = (Integer) animation.getAnimatedValue();
                            view.getLayoutParams().height = value.intValue();
                            view.requestLayout();
                        }
                    });
                    valueAnimator.start();
                }
            });*/
        }
    }

    private Context context;
    public boolean prefExibeVaricao;
    public List<Ativo> watchList;
    private Animation animation;

    public HeavyGearRecycleViewAdapter(Context context, List<Ativo> watchList) {
        this.context = context;
        this.watchList = watchList;
        this.prefExibeVaricao = PreferenceManager.getDefaultSharedPreferences(context).getBoolean(ConfiguracaoActivity.PREF_EXIBE_VARIACAO, false);

        animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(100);
        animation.setStartOffset(20);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setRepeatCount(5);
    }

    public static HeavyGearRecycleViewAdapter createFromQuote(Context context, List<Quote> quoteAcoes) {
        List<Ativo> acoes = new ArrayList<Ativo>();

        for (Quote quote : quoteAcoes) {
            acoes.add(new Ativo(quote.getsymbol(),
            quote.getName(),
            "",
            Double.parseDouble(quote.getLastTradePriceOnly())));
        }

        return new HeavyGearRecycleViewAdapter(context, acoes);
    }

    @Override
    public HeavyGearRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_heavy_gear, parent, false);
        return new HeavyGearRecycleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(HeavyGearRecycleViewHolder heavyGearRecycleViewHolder, int position) {
        heavyGearRecycleViewHolder.ativo = watchList.get(position);
        heavyGearRecycleViewHolder.logo.setImageResource(heavyGearRecycleViewHolder.ativo.getImgId(context));
        heavyGearRecycleViewHolder.codigo.setText(heavyGearRecycleViewHolder.ativo.getCodigo());
        heavyGearRecycleViewHolder.empresa.setText(heavyGearRecycleViewHolder.ativo.getEmpresa());
        heavyGearRecycleViewHolder.moeda.setTextColor(heavyGearRecycleViewHolder.ativo.getCor(context));
        heavyGearRecycleViewHolder.cotacao.setText(String.format("%.2f", heavyGearRecycleViewHolder.ativo.getCotacao()));
        heavyGearRecycleViewHolder.cotacao.setTextColor(heavyGearRecycleViewHolder.ativo.getCor(context));
        if(!heavyGearRecycleViewHolder.ativo.getTipo().equals("MOEDA")) {
            heavyGearRecycleViewHolder.variacao.setText(heavyGearRecycleViewHolder.ativo.getVariacaoFormat());
        } else {
            heavyGearRecycleViewHolder.variacao.setText("$ " + String.format("%.2f", heavyGearRecycleViewHolder.ativo.getCotaocaoDolar()));
        }
        heavyGearRecycleViewHolder.variacao.setTextColor(heavyGearRecycleViewHolder.ativo.getCor(context));
        heavyGearRecycleViewHolder.minimaDia.setText(String.format("%.2f", heavyGearRecycleViewHolder.ativo.getMinimaDia()));
        heavyGearRecycleViewHolder.maximaDia.setText(String.format("%.2f", heavyGearRecycleViewHolder.ativo.getMaximaDia()));
        heavyGearRecycleViewHolder.abertura.setText(String.format("%.2f", heavyGearRecycleViewHolder.ativo.getAbertura()));
        heavyGearRecycleViewHolder.volume.setText(String.format("%d", heavyGearRecycleViewHolder.ativo.getVolumeNegociacao()));

        if (prefExibeVaricao) {
            heavyGearRecycleViewHolder.variacao.setVisibility(View.VISIBLE);
        } else {
            heavyGearRecycleViewHolder.variacao.setVisibility(View.GONE);
        }

        /*if (heavyGearRecycleViewHolder.ativo.isViewExpanded()) {
            heavyGearRecycleViewHolder.relativeSecundario.setVisibility(View.VISIBLE);
            heavyGearRecycleViewHolder.relativeSecundario.setEnabled(true);
        } else {
            heavyGearRecycleViewHolder.relativeSecundario.setVisibility(View.GONE);
            heavyGearRecycleViewHolder.relativeSecundario.setEnabled(false);
            if (heavyGearRecycleViewHolder.ativo.getOriginalHeight() > 0) {
                heavyGearRecycleViewHolder.cardView.getLayoutParams().height = heavyGearRecycleViewHolder.ativo.getOriginalHeight();
            }
        }*/
        if (heavyGearRecycleViewHolder.ativo.isRefresh()) {
            heavyGearRecycleViewHolder.moeda.startAnimation(animation);
            heavyGearRecycleViewHolder.cotacao.startAnimation(animation);
            heavyGearRecycleViewHolder.variacao.startAnimation(animation);
        }
        heavyGearRecycleViewHolder.cardView.requestLayout();
    }

    @Override
    public int getItemCount() {
        if (watchList == null) {
            return 0;
        }
        return watchList.size();
    }

    public void add(Ativo ativo) {
        watchList.add(ativo);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        watchList.remove(position);

        notifyItemRemoved(position);
    }

    public void update(List<Ativo> novasAcoes) {
        if (novasAcoes.size() != watchList.size()){
            return;
        }

        List<Ativo> oldAcoes = new ArrayList<Ativo>();
        oldAcoes.addAll(watchList);
        watchList.clear();
        watchList.addAll(novasAcoes);
        for(int contador = 0; contador < novasAcoes.size(); contador++) {
            if (novasAcoes.get(contador).getCotacao() != oldAcoes.get(contador).getCotacao()) {
                notifyItemChanged(contador);
            }
        }
    }

    public void updateAll(List<Ativo> novasAcoes){
        watchList.clear();
        watchList.addAll(novasAcoes);
        notifyDataSetChanged();
    }

    public void updateItem(Ativo ativo, Integer index){
        if(ativo != null && index <= watchList.size()-1) {
            watchList.set(index, ativo);
            notifyItemChanged(index);
        }
    }

    public void updateExibeVariacao(Boolean prefExibeVaricao) {
        this.prefExibeVaricao = prefExibeVaricao;
        notifyDataSetChanged();
    }
}
