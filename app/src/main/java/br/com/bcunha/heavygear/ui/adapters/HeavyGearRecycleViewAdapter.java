package br.com.bcunha.heavygear.ui.adapters;

import android.animation.ValueAnimator;
import android.content.Context;
import android.preference.PreferenceManager;
import android.support.v7.util.DiffUtil;
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
import java.util.Random;

import br.com.bcunha.heavygear.R;
import br.com.bcunha.heavygear.model.pojo.Acao;
import br.com.bcunha.heavygear.model.pojo.AcaoDiffCallBack;
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
        final TextView minimaAno;
        final TextView maximaAno;
        Acao acao;
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
            minimaAno = (TextView) view.findViewById(R.id.minimaAno);
            maximaAno = (TextView) view.findViewById(R.id.maximaAno);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    if (acao.getOriginalHeight() == 0) {
                        if (acao.isViewExpanded()) {
                            acao.setOriginalHeight((int) (view.getHeight() / 2.5));
                        } else {
                            acao.setOriginalHeight(view.getHeight());
                        }
                    }

                    ValueAnimator valueAnimator;
                    if (acao.isViewExpanded()) {
                        relativeSecundario.setVisibility(View.GONE);
                        relativeSecundario.setEnabled(false);
                        acao.setViewExpanded(false);
                        valueAnimator = ValueAnimator.ofInt(acao.getOriginalHeight() + (int) (acao.getOriginalHeight() * 1.5), acao.getOriginalHeight());
                    } else {
                        relativeSecundario.setVisibility(View.VISIBLE);
                        relativeSecundario.setEnabled(true);
                        acao.setViewExpanded(true);
                        valueAnimator = ValueAnimator.ofInt(acao.getOriginalHeight(), acao.getOriginalHeight() + (int) (acao.getOriginalHeight() * 1.5));
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
        }
    }

    private Context context;
    public boolean prefExibeVaricao;
    public List<Acao> watchList;
    private Animation animation;

    public HeavyGearRecycleViewAdapter(Context context, List<Acao> watchList) {
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
        List<Acao> acoes = new ArrayList<Acao>();

        for (Quote quote : quoteAcoes) {
            acoes.add(new Acao(quote.getsymbol(),
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
        heavyGearRecycleViewHolder.acao = watchList.get(position);
        heavyGearRecycleViewHolder.logo.setImageResource(heavyGearRecycleViewHolder.acao.getImgId(context));
        heavyGearRecycleViewHolder.codigo.setText(heavyGearRecycleViewHolder.acao.getCodigo());
        heavyGearRecycleViewHolder.empresa.setText(heavyGearRecycleViewHolder.acao.getEmpresa());
        heavyGearRecycleViewHolder.moeda.setTextColor(heavyGearRecycleViewHolder.acao.getCor(context));
        heavyGearRecycleViewHolder.cotacao.setText(String.format("%.2f", heavyGearRecycleViewHolder.acao.getCotacao()));
        heavyGearRecycleViewHolder.cotacao.setTextColor(heavyGearRecycleViewHolder.acao.getCor(context));
        heavyGearRecycleViewHolder.variacao.setText(heavyGearRecycleViewHolder.acao.getVariacaoFormat());
        heavyGearRecycleViewHolder.variacao.setTextColor(heavyGearRecycleViewHolder.acao.getCor(context));
        heavyGearRecycleViewHolder.minimaDia.setText(String.format("%.2f", heavyGearRecycleViewHolder.acao.getMinimaDia()));
        heavyGearRecycleViewHolder.maximaDia.setText(String.format("%.2f", heavyGearRecycleViewHolder.acao.getMaximaDia()));
        heavyGearRecycleViewHolder.minimaAno.setText(String.format("%.2f", heavyGearRecycleViewHolder.acao.getMinimaAno()));
        heavyGearRecycleViewHolder.maximaAno.setText(String.format("%.2f", heavyGearRecycleViewHolder.acao.getMaximaAno()));

        if (prefExibeVaricao) {
            heavyGearRecycleViewHolder.variacao.setVisibility(View.VISIBLE);
        } else {
            heavyGearRecycleViewHolder.variacao.setVisibility(View.GONE);
        }

        if (heavyGearRecycleViewHolder.acao.isViewExpanded()) {
            heavyGearRecycleViewHolder.relativeSecundario.setVisibility(View.VISIBLE);
            heavyGearRecycleViewHolder.relativeSecundario.setEnabled(true);
        } else {
            heavyGearRecycleViewHolder.relativeSecundario.setVisibility(View.GONE);
            heavyGearRecycleViewHolder.relativeSecundario.setEnabled(false);
            if (heavyGearRecycleViewHolder.acao.getOriginalHeight() > 0) {
                heavyGearRecycleViewHolder.cardView.getLayoutParams().height = heavyGearRecycleViewHolder.acao.getOriginalHeight();
            }
        }
        heavyGearRecycleViewHolder.moeda.startAnimation(animation);
        heavyGearRecycleViewHolder.cotacao.startAnimation(animation);
        heavyGearRecycleViewHolder.variacao.startAnimation(animation);
        heavyGearRecycleViewHolder.cardView.requestLayout();
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
        if (novasAcoes.size() != watchList.size()){
            return;
        }

        List<Acao> oldAcoes = new ArrayList<Acao>();
        oldAcoes.addAll(watchList);
        watchList.clear();
        watchList.addAll(novasAcoes);
        for(int contador = 0; contador < novasAcoes.size(); contador++) {
            if (novasAcoes.get(contador).getCotacao() != oldAcoes.get(contador).getCotacao()) {
                notifyItemChanged(contador);
            }
        }
    }

    public void updateAll(List<Acao> novasAcoes){
        watchList.clear();
        watchList.addAll(novasAcoes);
        notifyDataSetChanged();
    }

    public void updateItem(Acao acao, Integer index){
        if(acao != null) {
            watchList.set(index, acao);
            notifyItemChanged(index);
        }
    }

    public void updateExibeVariacao(Boolean prefExibeVaricao) {
        this.prefExibeVaricao = prefExibeVaricao;
        notifyDataSetChanged();
    }
}
