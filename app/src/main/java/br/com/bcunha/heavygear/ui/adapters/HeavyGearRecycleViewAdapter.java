package br.com.bcunha.heavygear.ui.adapters;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.v4.content.ContextCompat;
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
import br.com.bcunha.heavygear.model.pojo.Acao;
import br.com.bcunha.heavygear.model.pojo.Quote;

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
        final TextView minimaDia;
        final TextView maximaDia;
        final TextView minimaAno;
        final TextView maximaAno;
        Acao acao;

        private int originalHeight = 0;
        private boolean isViewExpanded = false;
        private RelativeLayout relativeSecundario;

        public HeavyGearRecycleViewHolder(final View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.cardView_heavy_gear);
            logo = (ImageView) view.findViewById(R.id.imgAcao);
            codigo = (TextView) view.findViewById(R.id.codigo);
            empresa = (TextView) view.findViewById(R.id.empresa);
            moeda = (TextView) view.findViewById(R.id.moeda);
            cotacao = (TextView) view.findViewById(R.id.cotacao);
            relativeSecundario = (RelativeLayout) view.findViewById(R.id.relativeSecundario);
            minimaDia = (TextView) view.findViewById(R.id.MinimaDia);
            maximaDia = (TextView) view.findViewById(R.id.MaximaDia);
            minimaAno = (TextView) view.findViewById(R.id.MinimaAno);
            maximaAno = (TextView) view.findViewById(R.id.MaximaAno);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    if (originalHeight == 0) {
                        originalHeight = view.getHeight();
                    }

                    ValueAnimator valueAnimator;
                    if (!isViewExpanded) {
                        relativeSecundario.setVisibility(View.VISIBLE);
                        relativeSecundario.setEnabled(true);
                        isViewExpanded = true;
                        valueAnimator = ValueAnimator.ofInt(originalHeight, originalHeight + (int) (originalHeight * 1.5));
                    } else {
                        isViewExpanded = false;
                        valueAnimator = ValueAnimator.ofInt(originalHeight + (int) (originalHeight * 1.5), originalHeight);

                        Animation animation = new AlphaAnimation(1.00f, 0.00f);
                        animation.setDuration(200);
                        animation.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                relativeSecundario.setVisibility(View.INVISIBLE);
                                relativeSecundario.setEnabled(false);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });

                        relativeSecundario.startAnimation(animation);
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
            });

            if (isViewExpanded == false) {
                relativeSecundario.setVisibility(View.GONE);
                relativeSecundario.setEnabled(false);
            }
        }
    }

    public List<Acao> watchList;

    public HeavyGearRecycleViewAdapter(List<Acao> watchList) {
        this.watchList = watchList;
    }

    public static HeavyGearRecycleViewAdapter createFromQuote(List<Quote> quoteAcoes) {
        List<Acao> acoes = new ArrayList<Acao>();

        for (Quote quote : quoteAcoes) {
            acoes.add(new Acao(quote.getsymbol(),
            quote.getName(),
            "",
            Double.parseDouble(quote.getLastTradePriceOnly())));
        }

        return new HeavyGearRecycleViewAdapter(acoes);
    }

    @Override
    public HeavyGearRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_heavy_gear, parent, false);
        return new HeavyGearRecycleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(HeavyGearRecycleViewHolder heavyGearRecycleViewHolder, int position) {
        Context context = heavyGearRecycleViewHolder.itemView.getContext();
        heavyGearRecycleViewHolder.acao = watchList.get(position);

        heavyGearRecycleViewHolder.logo.setImageResource(heavyGearRecycleViewHolder.acao.getImgId(context));
        heavyGearRecycleViewHolder.codigo.setText(heavyGearRecycleViewHolder.acao.getCodigo());
        heavyGearRecycleViewHolder.empresa.setText(heavyGearRecycleViewHolder.acao.getEmpresa());
        heavyGearRecycleViewHolder.moeda.setTextColor(heavyGearRecycleViewHolder.acao.getCor(context));
        heavyGearRecycleViewHolder.cotacao.setText(String.format("%.2f", heavyGearRecycleViewHolder.acao.getCotacao()));
        heavyGearRecycleViewHolder.cotacao.setTextColor(heavyGearRecycleViewHolder.acao.getCor(context));
        heavyGearRecycleViewHolder.minimaDia.setText(String.format("%.2f", heavyGearRecycleViewHolder.acao.getMinimaDia()));
        heavyGearRecycleViewHolder.maximaDia.setText(String.format("%.2f", heavyGearRecycleViewHolder.acao.getMaximaDia()));
        heavyGearRecycleViewHolder.minimaAno.setText(String.format("%.2f", heavyGearRecycleViewHolder.acao.getMinimaAno()));
        heavyGearRecycleViewHolder.maximaAno.setText(String.format("%.2f", heavyGearRecycleViewHolder.acao.getMaximaAno()));
    }

    @Override
    public int getItemCount() {
        if (watchList == null) {
            return 0;
        }
        return watchList.size();
    }

    public void add(Acao acao) {
        watchList.add(acao);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        watchList.remove(position);
        notifyItemRemoved(position);
    }

    public void update(List<Acao> novasAcoes) {
        watchList = novasAcoes;
        notifyDataSetChanged();
    }
}
